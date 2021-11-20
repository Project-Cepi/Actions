package world.cepi.actions.command.subcommands

import net.minestom.server.entity.Entity
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import world.cepi.actions.actionItem
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

class EventNodeTargetMap<T : Event>(val node: EventNode<T>, val mapper: T.() -> Pair<Entity, Entity>)

class EventSubcommand(
    name: String = "event",
    val eventNodes: List<EventNodeTargetMap<out Event>>,
    /** The amount to drop from the beginning of the string. Ex mobUUIDInitialize, drop the length of mobUUID*/
    val eventNodeDrop: Int
) : Kommand({

    eventNodes.forEach { target ->
        syntax(target.node.name.drop(eventNodeDrop).literal()) {
            val item = player.itemInMainHand.actionItem ?: return@syntax

            target as EventNodeTargetMap<Event>

            target.node.addListener(Event::class.java) {
                item(target.mapper(it).first, target.mapper(it).second)
            }
        }
    }

}, name)