package com.cells.cells.emc;

import net.minecraft.item.ItemStack;

import appeng.api.config.Upgrades;
import appeng.parts.automation.StackUpgradeInventory;
import appeng.util.Platform;
import appeng.util.inv.filter.IAEItemFilter;
import net.minecraftforge.items.IItemHandler;

import com.cells.ItemRegistry;


/**
 * Single-slot upgrade inventory that only accepts the EMC capacity card.
 */
public class EmcCellUpgradeInventory extends StackUpgradeInventory {

    private final ItemStack cellStack;

    public EmcCellUpgradeInventory(ItemStack cellStack) {
        super(cellStack, null, 1);
        this.cellStack = cellStack;
        refresh();
        this.setFilter(new EmcCapacityOnlyFilter());
    }

    public void refresh() {
        this.readFromNBT(Platform.openNbtData(this.cellStack), "upgrades");
    }

    @Override
    public int getMaxInstalled(Upgrades upgrades) {
        return upgrades == Upgrades.CAPACITY ? 1 : 0;
    }

    public int getInstalledTier() {
        refresh();

        ItemStack installed = getStackInSlot(0);
        if (installed.isEmpty()) return -1;
        if (ItemRegistry.EMC_CAPACITY_CARD == null) return -1;
        if (installed.getItem() != ItemRegistry.EMC_CAPACITY_CARD) return -1;

        return installed.getMetadata();
    }

    @Override
    protected void onContentsChanged(int slot) {
        this.writeToNBT(Platform.openNbtData(this.cellStack), "upgrades");
        super.onContentsChanged(slot);
    }

    private static final class EmcCapacityOnlyFilter implements IAEItemFilter {

        @Override
        public boolean allowExtract(IItemHandler inv, int slot, int amount) {
            return true;
        }

        @Override
        public boolean allowInsert(IItemHandler inv, int slot, ItemStack stack) {
            if (stack.isEmpty()) return false;
            if (ItemRegistry.EMC_CAPACITY_CARD == null) return false;

            return stack.getItem() == ItemRegistry.EMC_CAPACITY_CARD;
        }
    }
}