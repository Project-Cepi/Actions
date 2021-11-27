package world.cepi.actions.command.subcommands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.actions.TargetArgumentType
import world.cepi.actions.actionItem
import world.cepi.kstom.command.kommand.Kommand

object SwapSubcommand : Kommand({
    val swapType = ArgumentType.Enum("swapType", TargetArgumentType::class.java)
        .setDefaultValue(TargetArgumentType.NORMAL)

    syntax(swapType) {
        val action = player.itemInMainHand.actionItem ?: return@syntax

        player.itemInMainHand = action.copy(targetArgType = !swapType).renderItem()
    }
}, "swap")