package net.xiaoou.chocolatecannon.compat;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fml.ModList;

public class PurpleSodaCompat {
    private static final String ALEXS_CAVES_MOD_ID = "alexscaves";
    private static boolean isAlexCavesLoaded = false;
    private static Fluid purpleSodaFluid = null;

    public static void init() {
        isAlexCavesLoaded = ModList.get().isLoaded(ALEXS_CAVES_MOD_ID);
        if (isAlexCavesLoaded) {
            try {
                // 软联动：通过反射获取紫色苏打水流体
                Class<?> fluidRegistryClass = Class.forName("com.alexscaves.core.registry.ACFluidRegistry");
                purpleSodaFluid = (Fluid) fluidRegistryClass.getField("PURPLE_SODA").get(null);
            } catch (Exception e) {
                // 如果反射失败，紫色苏打水流体保持为 null
                e.printStackTrace();
            }
        }
    }

    public static boolean isAlexCavesLoaded() {
        return isAlexCavesLoaded;
    }

    public static Fluid getPurpleSodaFluid() {
        return purpleSodaFluid;
    }

    public static boolean isPurpleSodaFluid(Fluid fluid) {
        return purpleSodaFluid != null && fluid == purpleSodaFluid;
    }

    public static boolean isPurpleSodaFluid(String fluidName) {
        return purpleSodaFluid != null && purpleSodaFluid.getRegistryName() != null && purpleSodaFluid.getRegistryName().toString().equals(fluidName);
    }

    // 碳酸喷涌效果：当紫色苏打水与某些物品或方块接触时产生气泡效果
    public static boolean shouldCreateCarbonationEffect(Fluid fluid) {
        return isPurpleSodaFluid(fluid);
    }

    // 苏打狂欢效果：当玩家在紫色苏打水中时获得特殊效果
    public static boolean shouldApplySodaRushEffect(Fluid fluid) {
        return isPurpleSodaFluid(fluid);
    }

    // 检查是否应该生成冰糖（接触水、熔岩或酸液时）
    public static boolean shouldCreateRockCandy(Fluid fluid1, Fluid fluid2) {
        if (!isPurpleSodaFluid(fluid1)) {
            return false;
        }
        // 这里可以根据另一种流体类型返回不同类型的冰糖
        return true;
    }

    // 检查紫色苏打水是否会在下界蒸发
    public static boolean shouldEvaporateInNether(Fluid fluid) {
        return isPurpleSodaFluid(fluid);
    }

    // 检查紫色苏打水是否会导致生物窒息
    public static boolean causesSuffocation(Fluid fluid) {
        return isPurpleSodaFluid(fluid);
    }

    // 检查紫色苏打水是否会影响生物的游泳能力
    public static boolean impairsSwimming(Fluid fluid) {
        return isPurpleSodaFluid(fluid);
    }
}