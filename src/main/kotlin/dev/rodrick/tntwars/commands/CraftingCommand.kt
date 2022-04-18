package dev.rodrick.tntwars.commands

import com.mojang.brigadier.context.CommandContext
import dev.rodrick.tntwars.utils.VirtualCraftingScreenHandler
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

object CraftingCommand: BaseCommand() {
    override val command = CommandManager.literal("crafting")
        .requires { it.player != null }
        .executes { this.execute(it) }!!

    override val aliases = listOf("craft")

    private fun execute(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source.player

        player.openHandledScreen(
            VirtualCraftingScreenHandler.createScreenHandlerFactory(
                player.world, player.blockPos
            )
        )

        return 0
    }
}