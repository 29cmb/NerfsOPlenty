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
            literal("random") {
                executes { ctx ->
                    val chosenNerf = NerfManager.REGISTRY.entrySet().filter { it.value.listed }.random()
                    val activatedNerf = NerfManager.activateNerf(chosenNerf.key.identifier())
                    ctx.source.sendFeedback(
                        Component.empty()
                            .append(Component.literal("Activated nerf "))
                            .append(Component.literal(activatedNerf.name).withStyle(ChatFormatting.WHITE))
                            .append(Component.literal("!"))
                            .withStyle(ChatFormatting.GREEN)
                    )
                }
            }

            literal("activate") {
                argument("id", NerfArgument) {
                    executes { ctx ->
                        val id = ctx.getArgument("id", Identifier::class.java)
                        val activatedNerf = NerfManager.activateNerf(id)

                        ctx.source.sendFeedback(
                            Component.empty()
                                .append(Component.literal("Activated nerf "))
                                .append(Component.literal(activatedNerf.name).withStyle(ChatFormatting.WHITE))
                                .append(Component.literal(" successfully!"))
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