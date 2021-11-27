package world.cepi.actions.command.subcommands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.actions.TargetSystemType
import world.cepi.actions.actionItem
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object TargetSystemSubcommand : Kommand({

    syntax("normal".literal()) {
        player.itemInMainHand = (player.itemInMainHand.actionItem ?: return@syntax)
            .copy(targetSystem = TargetSystemType.Normal).renderItem()
    }

    val distance = ArgumentType.Double("distance")

    syntax("near".literal(), distance) {
        player.itemInMainHand = (player.itemInMainHand.actionItem ?: return@syntax)
            .copy(targetSystem = TargetSystemType.Nearby(!distance)).renderItem()
    }

}, "target")