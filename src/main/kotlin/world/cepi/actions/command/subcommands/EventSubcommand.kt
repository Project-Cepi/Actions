package world.cepi.actions.command.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import world.cepi.actions.Action
import world.cepi.actions.ActionItem
import world.cepi.actions.actionItem
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
import world.cepi.kstom.util.pascalToTitle

data class ActionEventHandler(
    val name: String,
    val grabList: Player.() -> List<ActionItem>,
    val listToItem: Player.(List<ActionItem>) -> ItemStack
)

open class EventSubcommand(
    name: String = "event",
    val eventCondition: Kommand.SyntaxContext.() -> Boolean = { true },
    val eventNodes: List<ActionEventHandler>
) : Kommand({

    val add by literal

    val remove by literal
    val index = ArgumentType.Integer("index").min(0)

    val list by literal

    eventNodes.forEach { targetLambdaPair ->

        val targetLambdaName = targetLambdaPair.name.literal()

        syntax(add, targetLambdaName) {

            if (!eventCondition(this)) {
                sender.sendMessage("Condition not passed!")
                return@syntax
            }

            player.itemInMainHand = targetLambdaPair.listToItem(
                player,
                targetLambdaPair.grabList(player) + (player.itemInOffHand.actionItem ?: run {
                    sender.sendMessage("Could not find action item in off hand!")
                    return@syntax
                })
            )
        }

        syntax(remove, index, targetLambdaName) {
            if (!eventCondition(this)) {
                sender.sendMessage("Condition not passed!")
                return@syntax
            }

            player.itemInMainHand = targetLambdaPair.listToItem(
                player,
                targetLambdaPair.grabList(player).filterIndexed { i, _ -> i != !index }
            )

        }

        syntax(list, targetLambdaName) {
            if (!eventCondition(this)) {
                sender.sendMessage("Condition not passed!")
                return@syntax
            }

            targetLambdaPair.grabList(player).forEach {
                sender.sendMessage(
                    Component.text("| ", NamedTextColor.DARK_GRAY)
                        .append(Component.text(it.action::class.simpleName!!.pascalToTitle(), NamedTextColor.RED))
                )
            }
        }
    }

}, name)