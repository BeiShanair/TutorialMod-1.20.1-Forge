package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.tag.ModBlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;
import java.util.function.Supplier;

public enum ModToolTiers implements Tier {
    FIRE_ETHER(5, 2031, 12.0f, 5.0f, 30, () -> Ingredient.of(ModItems.FIRE_ETHER.get()));

    public static final Tier ICE_ETHER = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1400, 11f, 3f, 26,
                    ModBlockTags.ORE_TAGS, () -> Ingredient.of(ModItems.ICE_ETHER.get())),
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "ice_ether"), List.of(Tiers.WOOD), List.of());

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ModToolTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
