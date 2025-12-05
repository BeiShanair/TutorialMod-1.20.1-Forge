package com.besson.tutorial.item.custom;

import com.besson.tutorial.item.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, List<MobEffectInstance>> MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.ICE_ETHER,
                            Arrays.asList(
                                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1, 1, false, false, true),
                                    new MobEffectInstance(MobEffects.JUMP, 1, 1, false, false, true)
                            )).build();

    public CustomArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide()) {
            if (pEntity instanceof Player player && hasFullSuitableArmor(player)) {
                evaluateArmorEffects(player);
            }
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, List<MobEffectInstance>> entry : MAP.entrySet()) {
            ArmorMaterial material = entry.getKey();
            List<MobEffectInstance> effects = entry.getValue();

            if (hasCorrectMaterialArmorOn(material, player)) {
                for (MobEffectInstance effect : effects) {
                    MobEffect effects1 = effect.getEffect();
                    if (!player.hasEffect(effects1)) {
                        player.addEffect(effect);
                    }
                }
            }
        }
    }

    private boolean hasCorrectMaterialArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack stack : player.getInventory().armor) {
            if (!(stack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmor(1).getItem();
        ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem();

        return helmet.getMaterial() == material
                && chestplate.getMaterial() == material
                && leggings.getMaterial() == material
                && boots.getMaterial() == material;
    }

    private boolean hasFullSuitableArmor(Player player) {
        ItemStack helmet = player.getInventory().getArmor(3);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack boots = player.getInventory().getArmor(0);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }
}
