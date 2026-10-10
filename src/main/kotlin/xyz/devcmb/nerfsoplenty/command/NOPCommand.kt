package xyz.devcmb.nerfsoplenty.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.commands.SharedSuggestionProvider
import net.minecraft.commands.arguments.IdentifierArgument
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import xyz.devcmb.nerfsoplenty.NerfManager

object NOPCommand {
    fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        Command("nerfsoplenty") {
            literal("nerf") {
                argument("id", NerfArgument) {
                    executes { ctx ->
                        val id = ctx.getArgument("id", Identifier::class.java)
                        NerfManager.activateNerf(id)
                        ctx.source.sendFeedback(Component.literal("Activated nerf successfully"))
                    }
                }
            }
        }.register(dispatcher)
    }
}