package dev.rodrick.tntwars

import dev.rodrick.tntwars.callbacks.TntDisarm
import dev.rodrick.tntwars.commands.CraftingCommand
import dev.rodrick.tntwars.commands.ItemsCommand
import dev.rodrick.tntwars.commands.TntMovementCommand
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback

@Suppress("unused")
class TntWarsServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        TntWars.logger.info("Mod initialized")

        TntDisarm.register()

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, dedicated ->
            if (dedicated) {
                CraftingCommand.register(dispatcher)
                TntMovementCommand.register(dispatcher)
                ItemsCommand.register(dispatcher)
            }
        })
    }
}