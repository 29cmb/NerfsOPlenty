package xyz.devcmb.nerfsoplenty.command

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.ChatFormatting
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

                        ctx.source.sendFeedback(
                            Component.literal("Activated nerf successfully")
                                .withStyle(ChatFormatting.GREEN)
                        )
                    }
                }
            }

            literal("clear") {
                executes { ctx ->
                    NerfManager.clearNerf()
                    ctx.source.sendFeedback(
                        Component.literal("Cleared nerf successfully")
                            .withStyle(ChatFormatting.GREEN)
                    )
                }
            }
        }.register(dispatcher)
    }
}