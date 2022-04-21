package dev.rodrick.tntwars.utils

import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack

class InfiniteInventory(vararg items: ItemStack?) : SimpleInventory(*items) {
    constructor(size: Int, items: Map<Int, ItemStack>) : this(
        *Array<ItemStack>(size) {
            items.getOrElse(it) { ItemStack.EMPTY }
        }
    )

    override fun getStack(slot: Int): ItemStack = super.getStack(slot).apply {
        this.count = this.maxCount
    }
}