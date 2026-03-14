package com.xiaoou.fluidshellextensions.effects;

import com.xiaoou.fluidshellextensions.FluidShellExtensionsMod;
import com.xiaoou.fluidshellextensions.config.EffectConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;

import java.util.List;

public class EffectExecutor {
    public static void executeEffects(Level level, Vec3 pos, ResourceLocation fluid, int amount) {
        EffectConfig config = com.xiaoou.fluidshellextensions.config.EffectConfigLoader.getConfig(fluid);
        if (config == null) return;

        List<EffectConfig.EffectStage> stages = config.getStages();
        if (stages == null) return;

        for (EffectConfig.EffectStage stage : stages) {
            if (stage.matchesAmount(amount)) {
                List<EffectConfig.Effect> effects = stage.getEffects();
                if (effects != null) {
                    for (EffectConfig.Effect effect : effects) {
                        if (shouldExecute(effect)) {
                            executeEffect(level, pos, effect, fluid, amount);
                        }
                    }
                }
            }
        }
    }

    private static boolean shouldExecute(EffectConfig.Effect effect) {
        if (effect.getCondition() == null) return true;
        
        String modId = effect.getCondition().getMod();
        return modId != null && ModList.get().isLoaded(modId);
    }

    private static void executeEffect(Level level, Vec3 pos, EffectConfig.Effect effect, ResourceLocation fluid, int amount) {
        try {
            switch (effect.getType()) {
                case "place_fluid":
                    PlaceFluidEffect.execute(level, pos, effect.getPlaceFluid(), fluid);
                    break;
                case "spawn_items":
                    SpawnItemsEffect.execute(level, pos, effect.getSpawn_items());
                    break;
                case "apply_potion":
                    ApplyPotionEffect.execute(level, pos, effect.getApply_potion());
                    break;
                case "explosion":
                    ExplosionEffect.execute(level, pos, effect.getExplosion());
                    break;
                case "particle":
                    ParticleEffect.execute(level, pos, effect.getParticle());
                    break;
                default:
                    FluidShellExtensionsMod.LOGGER.warn("Unknown effect type: {}", effect.getType());
            }
        } catch (Exception e) {
            FluidShellExtensionsMod.LOGGER.warn("Failed to execute effect: {}", e.getMessage());
        }
    }
}