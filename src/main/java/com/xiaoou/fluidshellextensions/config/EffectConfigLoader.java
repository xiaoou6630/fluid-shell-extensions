package com.xiaoou.fluidshellextensions.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiaoou.fluidshellextensions.FluidShellExtensionsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mod.EventBusSubscriber(modid = FluidShellExtensionsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EffectConfigLoader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve("fluid_shell_extensions/effects");
    private static final Map<ResourceLocation, EffectConfig> CONFIGS = new HashMap<>();

    public static void loadConfigs() {
        try {
            // 创建配置目录
            Files.createDirectories(CONFIG_DIR);
            
            // 加载默认配置
            loadDefaultConfigs();
            
            // 加载用户配置
            loadUserConfigs();
        } catch (IOException e) {
            FluidShellExtensionsMod.LOGGER.error("Failed to load fluid shell effect configs: {}", e.getMessage());
        }
    }

    private static void loadDefaultConfigs() {
        try {
            // 从 assets 中复制默认配置文件
            net.minecraft.server.packs.resources.ResourceManager resourceManager = net.minecraftforge.fml.ModList.get().getModFileById(FluidShellExtensionsMod.MOD_ID).getFile().getPack();
            net.minecraft.server.packs.resources.Resource resource = resourceManager.getResource(new net.minecraft.resources.ResourceLocation(FluidShellExtensionsMod.MOD_ID, "config/create_chocolate.json"));
            if (resource != null) {
                Path targetPath = CONFIG_DIR.resolve("create_chocolate.json");
                if (!Files.exists(targetPath)) {
                    Files.copy(resource.getInputStream(), targetPath);
                }
            }
            
            resource = resourceManager.getResource(new net.minecraft.resources.ResourceLocation(FluidShellExtensionsMod.MOD_ID, "config/alexscaves_acid.json"));
            if (resource != null) {
                Path targetPath = CONFIG_DIR.resolve("alexscaves_acid.json");
                if (!Files.exists(targetPath)) {
                    Files.copy(resource.getInputStream(), targetPath);
                }
            }
            
            resource = resourceManager.getResource(new net.minecraft.resources.ResourceLocation(FluidShellExtensionsMod.MOD_ID, "config/alexscaves_purple_soda.json"));
            if (resource != null) {
                Path targetPath = CONFIG_DIR.resolve("alexscaves_purple_soda.json");
                if (!Files.exists(targetPath)) {
                    Files.copy(resource.getInputStream(), targetPath);
                }
            }
        } catch (Exception e) {
            FluidShellExtensionsMod.LOGGER.warn("Failed to load default configs: {}", e.getMessage());
        }
    }

    private static void loadUserConfigs() throws IOException {
        try (var paths = Files.walk(CONFIG_DIR)) {
            paths.filter(Files::isRegularFile)
                 .filter(p -> p.toString().endsWith(".json"))
                 .forEach(path -> {
                     try {
                         String content = Files.readString(path);
                         EffectConfig config = GSON.fromJson(content, EffectConfig.class);
                         if (config.isEnabled() && config.getFluid() != null) {
                             CONFIGS.put(config.getFluid(), config);
                         }
                     } catch (IOException e) {
                         FluidShellExtensionsMod.LOGGER.warn("Failed to load config file {}: {}", path, e.getMessage());
                     }
                 });
        }
    }

    public static EffectConfig getConfig(ResourceLocation fluid) {
        return CONFIGS.get(fluid);
    }

    @SubscribeEvent
    public static void onAddReloadListener(AddReloadListenerEvent event) {
        event.addListener(new ResourceManagerReloadListener() {
            @Override
            public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, Executor backgroundExecutor, Executor gameExecutor) {
                return CompletableFuture.runAsync(() -> {
                    CONFIGS.clear();
                    loadConfigs();
                }, backgroundExecutor).thenCompose(preparationBarrier::wait);
            }
        });
    }
}