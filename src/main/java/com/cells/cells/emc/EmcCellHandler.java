package com.cells.cells.emc;

import net.minecraft.item.ItemStack;

import appeng.api.AEApi;
import appeng.api.storage.ICellHandler;
import appeng.api.storage.ICellInventoryHandler;
import appeng.api.storage.ISaveProvider;
import appeng.api.storage.IStorageChannel;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEStack;

import com.cells.ItemRegistry;


/**
 * AE2 cell handler surface for the EMC cell.
 */
public class EmcCellHandler implements ICellHandler {

    public static final EmcCellHandler INSTANCE = new EmcCellHandler();

    private EmcCellHandler() {}

    @Override
    public boolean isCell(ItemStack is) {
        if (is.isEmpty()) return false;
        if (ItemRegistry.EMC_CELL == null) return false;

        return is.getItem() == ItemRegistry.EMC_CELL;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IAEStack<T>> ICellInventoryHandler<T> getCellInventory(
            ItemStack is, ISaveProvider container, IStorageChannel<T> channel) {
        if (!isCell(is)) return null;

        IItemStorageChannel itemChannel = AEApi.instance().storage().getStorageChannel(IItemStorageChannel.class);
        if (channel != itemChannel) return null;

        EmcCellInventory inventory = new EmcCellInventory(is, container);
        return (ICellInventoryHandler<T>) new EmcCellInventoryHandler(inventory);
    }
}