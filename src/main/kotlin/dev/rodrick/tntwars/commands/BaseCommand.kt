package dev.rodrick.tntwars.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

abstract class BaseCommand {
    protected abstract val command: LiteralArgumentBuilder<ServerCommandSource>
    protected open val aliases = listOf<String>()

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        val commandNode = dispatcher.register(this.command)
        aliases.forEach {
            dispatcher.register(
                CommandManager.literal(it).redirect(commandNode)
            )
        }
    }
}