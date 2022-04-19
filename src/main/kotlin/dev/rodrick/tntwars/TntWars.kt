package dev.rodrick.tntwars

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object TntWars {
    const val MOD_NAME: String = "TNT Wars"
    const val MOD_ID: String = "tntwars"

    val logger: Logger
        get() = LogManager.getLogger(MOD_NAME)
}

