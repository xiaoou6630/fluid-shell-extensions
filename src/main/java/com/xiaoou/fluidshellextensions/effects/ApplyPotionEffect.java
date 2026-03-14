package com.xiaoou.fluidshellextensions.effects;

import com.xiaoou.fluidshellextensions.FluidShellExtensionsMod;
import com.xiaoou.fluidshellextensions.config.EffectConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ApplyPotionEffect {
    public static void execute(Level level, Vec3 pos, EffectConfig.ApplyPotionEffect config) {
        String effectId = config.getEffect();
        if (effectId == null) return;

        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(effectId));
        if (effect == null) {
            FluidShellExtensionsMod.LOGGER.warn("Potion effect not found: {}", effectId);
            return;
        }

        int duration = config.getDuration();
        int amplifier = config.getAmplifier();
        int radius = config.getRadius();

        AABB area = new AABB(
                pos.x() - radius,
                pos.y() - radius,
                pos.z() - radius,
                pos.x() + radius,
                pos.y() + radius,
                pos.z() + radius
        );

        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);
        for (LivingEntity entity : entities) {
            entity.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }
    }
}