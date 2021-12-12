package world.cepi.actions.list

import net.minestom.server.entity.Entity
import net.minestom.server.utils.location.RelativeVec
import world.cepi.actions.Action

class TeleportAction(val position: RelativeVec) : Action() {

    override fun invoke(source: Entity, target: Entity?) {
        source.teleport(position.from(source.position).asPosition())
    }

}