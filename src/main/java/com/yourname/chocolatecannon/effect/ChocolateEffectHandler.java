package net.xiaoou.chocolatecannon.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import rbasamoyai.createbigcannons.munitions.big_cannon.fluid_shell.FluidBlobEffectRegistry.OnFluidShellExplode;
import rbasamoyai.createbigcannons.munitions.big_cannon.fluid_shell.FluidBlobEffectRegistry.OnHitBlock;
import rbasamoyai.createbigcannons.munitions.big_cannon.fluid_shell.FluidBlobBurst;
import rbasamoyai.createbigcannons.munitions.big_cannon.fluid_shell.EndFluidStack;
import net.xiaoou.chocolatecannon.compat.PurpleSodaCompat;

public class ChocolateEffectHandler {
    public static void onChocolateExplode(OnFluidShellExplode.Context context) {
        Level level = context.level();
        double x = context.x();
        double y = context.y();
        double z = context.z();
        
        // 检查流体量，2000mb 时生成巧克力棒
        // 注意：这里需要获取实际的流体量，但当前 Context 没有提供
        // 暂时假设爆炸效果就是 2000mb 的情况
        spawnChocolateBars(level, x, y, z);
    }
    
    public static void onChocolateHitBlock(OnHitBlock.Context context) {
        Level level = context.level();
        if (level.isClientSide) return;
        
        BlockPos pos = context.result().getBlockPos().relative(context.result().getDirection());
        
        // 生成巧克力流体
        spawnChocolateFluid(level, pos);
    }
    
    private static void spawnChocolateFluid(Level level, BlockPos pos) {
        Fluid chocolateFluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation("create", "chocolate"));
        if (chocolateFluid != null && level.isEmptyBlock(pos)) {
            level.setBlockAndUpdate(pos, chocolateFluid.defaultFluidState().createLegacyBlock());
        }
    }
    
    private static void spawnChocolateBars(Level level, double x, double y, double z) {
        ItemStack chocolateBar = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("create", "bar_of_chocolate")));
        if (chocolateBar.isEmpty()) return;
        
        // 在 3x3 范围内生成巧克力棒
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos pos = new BlockPos((int) x + dx, (int) y, (int) z + dz);
                if (level.isEmptyBlock(pos)) {
                    ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, chocolateBar.copy());
                    itemEntity.setDeltaMovement(new Vec3(0.1 * dx, 0.2, 0.1 * dz));
                    level.addFreshEntity(itemEntity);
                }
            }
        }
    }
    
    // 处理紫色苏打水的爆炸效果
    public static void onPurpleSodaExplode(OnFluidShellExplode.Context context) {
        Level level = context.level();
        double x = context.x();
        double y = context.y();
        double z = context.z();
        
        // 生成碳酸喷涌效果（气泡）
        spawnCarbonationEffect(level, x, y, z);
    }
    
    // 处理紫色苏打水的方块命中效果
    public static void onPurpleSodaHitBlock(OnHitBlock.Context context) {
        Level level = context.level();
        if (level.isClientSide) return;
        
        BlockPos pos = context.result().getBlockPos().relative(context.result().getDirection());
        
        // 生成紫色苏打水流体
        spawnPurpleSodaFluid(level, pos);
    }
    
    // 生成紫色苏打水流体
    private static void spawnPurpleSodaFluid(Level level, BlockPos pos) {
        Fluid purpleSodaFluid = PurpleSodaCompat.getPurpleSodaFluid();
        if (purpleSodaFluid != null && level.isEmptyBlock(pos)) {
            level.setBlockAndUpdate(pos, purpleSodaFluid.defaultFluidState().createLegacyBlock());
        }
    }
    
    // 生成碳酸喷涌效果（气泡）
    private static void spawnCarbonationEffect(Level level, double x, double y, double z) {
        // 这里可以添加气泡粒子效果的代码
        // 例如：level.addParticle(ParticleTypes.BUBBLE, x, y, z, 0, 0.1, 0);
        
        // 在 3x3 范围内生成紫色苏打水瓶
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos pos = new BlockPos((int) x + dx, (int) y, (int) z + dz);
                if (level.isEmptyBlock(pos)) {
                    // 这里可以添加生成紫色苏打水瓶的代码
                    // 例如：ItemStack sodaBottle = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("alexscaves", "purple_soda_bottle")));
                    // ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, sodaBottle);
                    // level.addFreshEntity(itemEntity);
                }
            }
        }
    }
    
    // 处理酸液的爆炸效果
    public static void onAcidExplode(OnFluidShellExplode.Context context) {
        Level level = context.level();
        double x = context.x();
        double y = context.y();
        double z = context.z();
        
        // 生成酸液腐蚀效果
        spawnAcidCorrosionEffect(level, x, y, z);
    }
    
    // 处理酸液的方块命中效果
    public static void onAcidHitBlock(OnHitBlock.Context context) {
        Level level = context.level();
        if (level.isClientSide) return;
        
        BlockPos pos = context.result().getBlockPos().relative(context.result().getDirection());
        
        // 生成酸液流体
        spawnAcidFluid(level, pos);
    }
    
    // 生成酸液流体
    private static void spawnAcidFluid(Level level, BlockPos pos) {
        Fluid acidFluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation("alexscaves", "acid"));
        if (acidFluid != null && level.isEmptyBlock(pos)) {
            level.setBlockAndUpdate(pos, acidFluid.defaultFluidState().createLegacyBlock());
        }
    }
    
    // 生成酸液腐蚀效果
    private static void spawnAcidCorrosionEffect(Level level, double x, double y, double z) {
        // 这里可以添加酸液腐蚀的粒子效果或其他效果
        // 例如：level.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0.1, 0);
        
        // 酸液会销毁掉落物，这里可以添加相关逻辑
    }
}
