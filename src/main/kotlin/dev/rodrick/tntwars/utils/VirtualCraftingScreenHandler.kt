package dev.rodrick.tntwars.utils

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.CraftingScreenHandler
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.BaseText
import net.minecraft.text.LiteralText
import net.minecraft.text.TranslatableText
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class VirtualCraftingScreenHandler(syncId: Int, playerInventory: PlayerInventory?, context: ScreenHandlerContext?) :
    CraftingScreenHandler(syncId, playerInventory, context) {

    override fun canUse(player: PlayerEntity): Boolean = true

    companion object {
        private fun createScreenHandlerFactory(
            world: World, pos: BlockPos, name: BaseText
        ): NamedScreenHandlerFactory {
            return SimpleNamedScreenHandlerFactory({ syncId: Int, inventory: PlayerInventory?, _: PlayerEntity? ->
                VirtualCraftingScreenHandler(
                    syncId, inventory, ScreenHandlerContext.create(world, pos)
                )
            }, name)
        }

        fun createScreenHandlerFactory(
            world: World, pos: BlockPos, name: String? = null
        ): NamedScreenHandlerFactory = this.createScreenHandlerFactory(
            world, pos, if (name != null) LiteralText(name) else TranslatableText("container.crafting")
        )
    }
}