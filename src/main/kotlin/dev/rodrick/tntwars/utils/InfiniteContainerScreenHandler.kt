package dev.rodrick.tntwars.utils

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.text.LiteralText
import kotlin.math.floor

class InfiniteContainerScreenHandler(
    syncId: Int,
    size: ScreenHandlerType<*>,
    private val playerInventory: PlayerInventory
) :
    ScreenHandler(size, syncId) {

    companion object {
        private val SIZE_3X3 = ScreenHandlerType.GENERIC_3X3!!
        private val SIZE_9X3 = ScreenHandlerType.GENERIC_9X3!!
        private val SIZE_9X6 = ScreenHandlerType.GENERIC_9X6!!

        private fun rowsOf(size: ScreenHandlerType<*>): Int = when (size) {
            SIZE_3X3 -> 3
            SIZE_9X3 -> 3
            SIZE_9X6 -> 6
            else -> throw UnsupportedOperationException("Unsupported size $size")
        }

        private fun colsOf(size: ScreenHandlerType<*>): Int = when (size) {
            SIZE_3X3 -> 3
            SIZE_9X3 -> 9
            SIZE_9X6 -> 9
            else -> throw UnsupportedOperationException("Unsupported size $size")
        }

        private fun sizeOf(size: ScreenHandlerType<*>): Int = rowsOf(size) * colsOf(size)

        fun create3x3(syncId: Int, playerInventory: PlayerInventory, inventory: Inventory) =
            InfiniteContainerScreenHandler(syncId, SIZE_3X3, playerInventory)

        fun create9x3(syncId: Int, playerInventory: PlayerInventory, inventory: Inventory) =
            InfiniteContainerScreenHandler(syncId, SIZE_9X3, playerInventory)

        fun create9x6(syncId: Int, playerInventory: PlayerInventory, inventory: Inventory) =
            InfiniteContainerScreenHandler(syncId, SIZE_9X6, playerInventory)
    }

    private val inventory = SimpleInventory(sizeOf(size))
    private val columns = colsOf(size)
    private val rows = rowsOf(size)

    override fun canUse(player: PlayerEntity?): Boolean = true

    init {
        inventory.setStack(4, ItemStack(Items.TNT, 1))

        for (i in 0 until inventory.size()) {
            addSlot(Slot(inventory, i, i % columns, floor(i.toDouble() / columns).toInt()))
        }

        for (i in 9 until 9 * 4) {
            addSlot(Slot(playerInventory, i, 0, 0)) // TODO: fix coords
        }

        for (i in 0 until 9) {
            addSlot(Slot(playerInventory, i, 0, 0)) // TODO: fix coords
        }
    }

    override fun onSlotClick(slot: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
        player.sendMessage(LiteralText("slot: $slot   button: $button   action: $actionType"), false)

        val isInfinite = slot < inventory.size()

        if (actionType == SlotActionType.PICKUP && isInfinite) {
            cursorStack = inventory.getStack(slot).copy().apply { count = maxCount }
            return
        } else if (actionType == SlotActionType.QUICK_MOVE && isInfinite) {
//            if (slot < 0 || !slots[slot].canTakeItems(player)) {
//                return
//            }
//
//            itemStack = transferSlot(player, slotIndex)
//            while (!itemStack.isEmpty() && ItemStack.areItemsEqualIgnoreDamage(slot.getStack(), itemStack)) {
//                itemStack = transferSlot(player, slotIndex)
//            }
        }

        super.onSlotClick(slot, button, actionType, player)
    }
}