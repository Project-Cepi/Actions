package world.cepi.actions.list

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import world.cepi.actions.Action

@Serializable
data class FlingAction(val up: Double) : Action() {

    override fun invoke(source: Entity, target: Entity?) {
        target?.let {
            it.velocity = it.velocity.add(.0, up, .0)
        }
    }

}