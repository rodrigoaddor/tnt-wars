package dev.rodrick.tntwars.commands

import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText

object TntMovementCommand : BaseCommand() {
    override val command = CommandManager.literal("tnt-movement")
        .then(CommandManager.argument("status", BoolArgumentType.bool()).requires { it.hasPermissionLevel(2) }
            .executes { this.execute(it) })!!

    var isTntMovementEnabled: Boolean = true

    private fun execute(context: CommandContext<ServerCommandSource>): Int {
        val enable = BoolArgumentType.getBool(context, "status")
        this.isTntMovementEnabled = enable

        context.source.sendFeedback(LiteralText("${if (enable) "Enabled" else "Disabled"} TNT Movement"), true)

        return if (!enable) 0 else 1
    }
}