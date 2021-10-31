package world.cepi.actions.list

import net.minestom.server.entity.Entity
import world.cepi.actions.Action

class RemoveAction : Action() {

    override fun invoke(source: Entity, target: Entity?) {
        target?.remove()
    }

}