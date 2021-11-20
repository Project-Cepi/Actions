package world.cepi.actions.command.subcommands

import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import world.cepi.actions.actionItem
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

class EventNodeTargetMap<T : Event>(val node: EventNode<T>, val mapper: T.() -> Pair<Entity, Entity>)

open class EventSubcommand(
    name: String = "event",
    val eventCondition: Kommand.SyntaxContext.() -> Boolean = { true },
    val eventNodes: List<Pair<(Player) -> EventNodeTargetMap<out Event>?, String>>
) : Kommand({

    eventNodes.forEach { targetLambdaPair ->

        syntax(targetLambdaPair.second.literal()) {

            if (!eventCondition(this)) return@syntax

            val target = targetLambdaPair.first(this.player) ?: return@syntax

            val item = player.itemInMainHand.actionItem ?: return@syntax

            target as EventNodeTargetMap<Event>

            target.node.addListener(Event::class.java) {
                item(target.mapper(it).first, target.mapper(it).second)
            }
        }
    }

}, name)