package world.cepi.actions.list

import net.minestom.server.entity.Entity
import world.cepi.actions.Action

class FlingAction(val up: Double) : Action() {

    override fun invoke(entity: Entity, target: Entity?) {
        target?.let {
            it.velocity = it.velocity.add(.0, up, .0)
        }
    }

}