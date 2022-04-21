package dev.rodrick.tntwars.commands

import com.mojang.brigadier.context.CommandContext
import dev.rodrick.tntwars.utils.InfiniteContainerScreenHandler
import dev.rodrick.tntwars.utils.InfiniteInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.Generic3x3ContainerScreenHandler
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText

object ItemsCommand : BaseCommand() {
    override val command = CommandManager.literal("items")
        .requires { it.player != null }
        .executes { this.execute(it) }!!

    private fun execute(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source.player

        val inventory = InfiniteInventory(
            9, mapOf(
                4 to ItemStack(Items.TNT, 1)
            )
        )

        player.openHandledScreen(
            SimpleNamedScreenHandlerFactory({ syncId, playerInventory, _ ->
                InfiniteContainerScreenHandler.create3x3(syncId, playerInventory, SimpleInventory(3 * 3))
            }, LiteralText("Items"))
        )

        return 0
    }
}