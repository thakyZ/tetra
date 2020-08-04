package se.mickelus.tetra.module.schematic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import se.mickelus.tetra.items.modular.ModularItem;

public abstract class BaseSchematic implements UpgradeSchematic {
    @Override
    public boolean canApplyUpgrade(PlayerEntity player, ItemStack itemStack, ItemStack[] materials, String slot, int[] availableCapabilities) {
        return isMaterialsValid(itemStack, slot, materials)
                && !isIntegrityViolation(player, itemStack, materials, slot)
                && checkCapabilities(itemStack, materials, availableCapabilities)
                && (player.isCreative() || player.experienceLevel >= getExperienceCost(itemStack, materials, slot));
    }

    @Override
    public boolean isIntegrityViolation(PlayerEntity player, ItemStack itemStack, final ItemStack[] materials, String slot) {
        ItemStack upgradedStack = applyUpgrade(itemStack, materials, false, slot, null);
        return ModularItem.getIntegrityGain(upgradedStack) + ModularItem.getIntegrityCost(upgradedStack) < 0;
    }

    @Override
    public boolean checkCapabilities(final ItemStack targetStack, final ItemStack[] materials, int[] availableCapabilities) {
        return getRequiredCapabilities(targetStack, materials).stream()
                .allMatch(capability -> availableCapabilities[capability.ordinal()] >= getRequiredCapabilityLevel(targetStack, materials, capability));
    }

    @Override
    public OutcomePreview[] getPreviews(ItemStack targetStack, String slot) {
        return new OutcomePreview[0];
    }
}