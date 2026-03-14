package com.xiaoou.fluidshellextensions;

import com.xiaoou.fluidshellextensions.config.EffectConfigLoader;
import com.xiaoou.fluidshellextensions.handler.FluidShellHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(FluidShellExtensionsMod.MOD_ID)
public class FluidShellExtensionsMod {
    public static final String MOD_ID = "fluid_shell_extensions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public FluidShellExtensionsMod() {
        // 加载配置
        EffectConfigLoader.loadConfigs();
        
        // 注册事件处理器
        FMLJavaModLoadingContext.get().getModEventBus().register(FluidShellHandler.class);
    }
}