package world.cepi.actions.list

import net.kyori.adventure.identity.Identity
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import world.cepi.actions.Action
import world.cepi.kstom.adventure.asMini
import world.cepi.kstom.command.arguments.generation.annotations.GenerationConstructor

class MessageAction(val message: Component): Action() {

    @GenerationConstructor
    constructor(message: String): this(message.asMini())

    override fun invoke(source: Entity, target: Entity?) {
        (target as? Player)?.sendMessage(Identity.identity(source.uuid), message)
    }

}