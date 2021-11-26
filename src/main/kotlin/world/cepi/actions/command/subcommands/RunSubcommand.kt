package world.cepi.actions.command.subcommands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.actions.actionItem
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object RunSubcommand : Kommand({
    syntax {
        val action = player.itemInMainHand.actionItem ?: return@syntax

        action(player, player)
    }

    val target = ArgumentType.Entity("target").singleEntity(true)

    syntax(target) {
        val action = player.itemInMainHand.actionItem ?: return@syntax

        action(player, (!target).findFirstEntity(player) ?: return@syntax)
    }

    val source = ArgumentType.Entity("source").singleEntity(true)
    syntax(source, target) {
        val action = player.itemInMainHand.actionItem ?: return@syntax

        action(
            (!source).findFirstEntity(player) ?: return@syntax,
            (!target).findFirstEntity(player) ?: return@syntax
        )
    }

}, "run")