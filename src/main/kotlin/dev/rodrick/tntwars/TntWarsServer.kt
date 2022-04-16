package dev.rodrick.tntwars

import dev.rodrick.tntwars.callbacks.TntDisarm
import net.fabricmc.api.DedicatedServerModInitializer

@Suppress("unused")
class TntWarsServer: DedicatedServerModInitializer {
    override fun onInitializeServer() {
        TntWars.logger.info("Mod initialized")

        TntDisarm.register()
    }
}