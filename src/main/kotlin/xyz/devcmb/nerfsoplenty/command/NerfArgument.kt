package xyz.devcmb.nerfsoplenty.command

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.commands.SharedSuggestionProvider
import net.minecraft.commands.arguments.IdentifierArgument
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import xyz.devcmb.nerfsoplenty.NerfManager
import xyz.devcmb.nerfsoplenty.NerfsOPlenty
import java.util.concurrent.CompletableFuture

object NerfArgument : ArgumentType<Identifier> {
    private val UNKNOWN_NERF = DynamicCommandExceptionType { id ->
        Component.literal("Unknown nerf: $id")
    }

    override fun parse(reader: StringReader): Identifier {
        val start = reader.cursor
        val id = IdentifierArgument.id().parse(reader)
        if (!NerfManager.REGISTRY.containsKey(id)) {
            reader.cursor = start
            throw UNKNOWN_NERF.createWithContext(reader, id)
        }
        return id
    }

    override fun <S> listSuggestions(
        context: CommandContext<S>,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions> =
        SharedSuggestionProvider.suggestResource(NerfManager.REGISTRY.keySet(), builder)

    override fun getExamples(): Collection<String> = NerfManager.nerfs.take(3).map { "${NerfsOPlenty.MOD_ID}:${it.id}" }
}