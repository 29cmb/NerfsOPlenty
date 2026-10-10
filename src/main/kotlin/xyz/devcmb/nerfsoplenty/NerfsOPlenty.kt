package xyz.devcmb.nerfsoplenty

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.minecraft.resources.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import xyz.devcmb.nerfsoplenty.command.NOPCommand

object NerfsOPlenty : ModInitializer {
	const val MOD_ID: String = "nop"
	val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		NerfManager.init()

		ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
			NOPCommand.register(dispatcher)
		}
	}

	fun id(path: String): Identifier
		= Identifier.fromNamespaceAndPath(MOD_ID, path)
}
