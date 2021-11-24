package world.cepi.actions.command.subcommands

import net.minestom.server.entity.Player
import world.cepi.actions.Action
import world.cepi.actions.ActionItem
import world.cepi.actions.actionItem
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

data class ActionEventHandler<T>(
    val name: String,
    val grabList: Player.() -> List<ActionItem>,
    val listToItem: Player.(List<ActionItem>) -> T
)

open class EventSubcommand<T>(
    name: String = "event",
    val eventCondition: Kommand.SyntaxContext.() -> Boolean = { true },
    val eventNodes: List<ActionEventHandler<T>>
) : Kommand({

    eventNodes.forEach { targetLambdaPair ->

        syntax(targetLambdaPair.name.literal()) {

            if (!eventCondition(this)) return@syntax

            targetLambdaPair.listToItem(player, targetLambdaPair.grabList(player) + (player.itemInMainHand.actionItem ?: return@syntax))
        }
    }

}, name)