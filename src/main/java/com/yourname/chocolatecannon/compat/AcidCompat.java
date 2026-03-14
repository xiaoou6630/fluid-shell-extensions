package net.xiaoou.chocolatecannon.compat;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fml.ModList;

public class AcidCompat {
    private static final String ALEXS_CAVES_MOD_ID = "alexscaves";
    private static boolean isAlexCavesLoaded = false;
    private static Fluid acidFluid = null;

    public static void init() {
        isAlexCavesLoaded = ModList.get().isLoaded(ALEXS_CAVES_MOD_ID);
        if (isAlexCavesLoaded) {
            try {
                // 软联动：通过反射获取酸液流体
                Class<?> fluidRegistryClass = Class.forName("com.alexscaves.core.registry.ACFluidRegistry");
                acidFluid = (Fluid) fluidRegistryClass.getField("ACID").get(null);
            } catch (Exception e) {
                // 如果反射失败，酸液流体保持为 null
                e.printStackTrace();
            }
        }
    }

    public static boolean isAlexCavesLoaded() {
        return isAlexCavesLoaded;
    }

    public static Fluid getAcidFluid() {
        return acidFluid;
    }

    public static boolean isAcidFluid(Fluid fluid) {
        return acidFluid != null && fluid == acidFluid;
    }

    public static boolean isAcidFluid(String fluidName) {
        return acidFluid != null && acidFluid.getRegistryName() != null && acidFluid.getRegistryName().toString().equals(fluidName);
    }
}