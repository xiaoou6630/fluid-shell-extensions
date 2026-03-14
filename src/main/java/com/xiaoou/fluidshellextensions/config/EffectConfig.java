package com.xiaoou.fluidshellextensions.config;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class EffectConfig {
    private boolean enabled = true;
    private String fluid;
    private List<EffectStage> stages;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ResourceLocation getFluid() {
        return fluid != null ? new ResourceLocation(fluid) : null;
    }

    public void setFluid(String fluid) {
        this.fluid = fluid;
    }

    public List<EffectStage> getStages() {
        return stages;
    }

    public void setStages(List<EffectStage> stages) {
        this.stages = stages;
    }

    public static class EffectStage {
        private int min_amount;
        private int max_amount = -1;
        private List<Effect> effects;

        public int getMinAmount() {
            return min_amount;
        }

        public void setMinAmount(int min_amount) {
            this.min_amount = min_amount;
        }

        public int getMaxAmount() {
            return max_amount;
        }

        public void setMaxAmount(int max_amount) {
            this.max_amount = max_amount;
        }

        public List<Effect> getEffects() {
            return effects;
        }

        public void setEffects(List<Effect> effects) {
            this.effects = effects;
        }

        public boolean matchesAmount(int amount) {
            return amount >= min_amount && (max_amount == -1 || amount <= max_amount);
        }
    }

    public static class Effect {
        private String type;
        private PlaceFluidEffect place_fluid;
        private SpawnItemsEffect spawn_items;
        private ApplyPotionEffect apply_potion;
        private ExplosionEffect explosion;
        private ParticleEffect particle;
        private Condition condition;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public PlaceFluidEffect getPlaceFluid() {
            return place_fluid;
        }

        public void setPlace_fluid(PlaceFluidEffect place_fluid) {
            this.place_fluid = place_fluid;
        }

        public SpawnItemsEffect getSpawn_items() {
            return spawn_items;
        }

        public void setSpawn_items(SpawnItemsEffect spawn_items) {
            this.spawn_items = spawn_items;
        }

        public ApplyPotionEffect getApply_potion() {
            return apply_potion;
        }

        public void setApply_potion(ApplyPotionEffect apply_potion) {
            this.apply_potion = apply_potion;
        }

        public ExplosionEffect getExplosion() {
            return explosion;
        }

        public void setExplosion(ExplosionEffect explosion) {
            this.explosion = explosion;
        }

        public ParticleEffect getParticle() {
            return particle;
        }

        public void setParticle(ParticleEffect particle) {
            this.particle = particle;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }
    }

    public static class PlaceFluidEffect {
        private int radius = 0;
        private boolean check_air = true;

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public boolean isCheck_air() {
            return check_air;
        }

        public void setCheck_air(boolean check_air) {
            this.check_air = check_air;
        }
    }

    public static class SpawnItemsEffect {
        private String item;
        private int min_count = 1;
        private int max_count = 1;
        private double chance = 1.0;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public int getMin_count() {
            return min_count;
        }

        public void setMin_count(int min_count) {
            this.min_count = min_count;
        }

        public int getMax_count() {
            return max_count;
        }

        public void setMax_count(int max_count) {
            this.max_count = max_count;
        }

        public double getChance() {
            return chance;
        }

        public void setChance(double chance) {
            this.chance = chance;
        }
    }

    public static class ApplyPotionEffect {
        private String effect;
        private int duration = 200;
        private int amplifier = 0;
        private int radius = 3;

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getAmplifier() {
            return amplifier;
        }

        public void setAmplifier(int amplifier) {
            this.amplifier = amplifier;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }
    }

    public static class ExplosionEffect {
        private float power = 1.0f;
        private boolean break_blocks = false;

        public float getPower() {
            return power;
        }

        public void setPower(float power) {
            this.power = power;
        }

        public boolean isBreak_blocks() {
            return break_blocks;
        }

        public void setBreak_blocks(boolean break_blocks) {
            this.break_blocks = break_blocks;
        }
    }

    public static class ParticleEffect {
        private String type;
        private int count = 10;
        private double spread = 1.0;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getSpread() {
            return spread;
        }

        public void setSpread(double spread) {
            this.spread = spread;
        }
    }

    public static class Condition {
        private String mod;

        public String getMod() {
            return mod;
        }

        public void setMod(String mod) {
            this.mod = mod;
        }
    }
}