package com.xiaoou.fluidshellextensions.effects;

import com.xiaoou.fluidshellextensions.FluidShellExtensionsMod;
import com.xiaoou.fluidshellextensions.config.EffectConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class SpawnItemsEffect {
    private static final Random RANDOM = new Random();

    public static void execute(Level level, Vec3 pos, EffectConfig.SpawnItemsEffect config) {
        if (level.isClientSide) return;

        String itemId = config.getItem();
        if (itemId == null) return;

        net.minecraft.world.item.Item item = ForgeRegistries.ITEMS.getValue(new net.minecraft.resources.ResourceLocation(itemId));
        if (item == null) {
            FluidShellExtensionsMod.LOGGER.warn("Item not found: {}", itemId);
            return;
        }

        double chance = config.getChance();
        if (RANDOM.nextDouble() > chance) return;

        int minCount = config.getMin_count();
        int maxCount = config.getMax_count();
        int count = minCount + RANDOM.nextInt(maxCount - minCount + 1);

        ItemStack stack = new ItemStack(item, count);
        ItemEntity itemEntity = new ItemEntity(
                level,
                pos.x(),
                pos.y(),
                pos.z(),
                stack
        );

        itemEntity.setDeltaMovement(
                (RANDOM.nextDouble() - 0.5) * 0.2,
                0.2,
                (RANDOM.nextDouble() - 0.5) * 0.2
        );

        level.addFreshEntity(itemEntity);
    }
}