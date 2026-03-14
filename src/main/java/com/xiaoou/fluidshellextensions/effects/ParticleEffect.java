package com.xiaoou.fluidshellextensions.effects;

import com.xiaoou.fluidshellextensions.FluidShellExtensionsMod;
import com.xiaoou.fluidshellextensions.config.EffectConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ParticleEffect {
    private static final Random RANDOM = new Random();

    public static void execute(Level level, Vec3 pos, EffectConfig.ParticleEffect config) {
        if (!level.isClientSide) return;

        String particleType = config.getType();
        if (particleType == null) return;

        int count = config.getCount();
        double spread = config.getSpread();

        for (int i = 0; i < count; i++) {
            double dx = (RANDOM.nextDouble() - 0.5) * spread;
            double dy = (RANDOM.nextDouble() - 0.5) * spread;
            double dz = (RANDOM.nextDouble() - 0.5) * spread;

            switch (particleType) {
                case "bubble":
                    level.addParticle(ParticleTypes.BUBBLE, pos.x(), pos.y(), pos.z(), dx, dy, dz);
                    break;
                case "smoke":
                    level.addParticle(ParticleTypes.SMOKE, pos.x(), pos.y(), pos.z(), dx, dy, dz);
                    break;
                case "effect":
                    level.addParticle(ParticleTypes.EFFECT, pos.x(), pos.y(), pos.z(), dx, dy, dz);
                    break;
                case "witch":
                    level.addParticle(ParticleTypes.WITCH, pos.x(), pos.y(), pos.z(), dx, dy, dz);
                    break;
                case "bubble_pop":
                    level.addParticle(ParticleTypes.BUBBLE_POP, pos.x(), pos.y(), pos.z(), dx, dy, dz);
                    break;
                default:
                    FluidShellExtensionsMod.LOGGER.warn("Unknown particle type: {}", particleType);
            }
        }
    }
}