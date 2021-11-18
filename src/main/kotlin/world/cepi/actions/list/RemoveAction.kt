package world.cepi.actions.list

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import world.cepi.actions.Action

@Serializable
class RemoveAction : Action() {

    override fun invoke(source: Entity, target: Entity?) {
        target?.remove()
    }

}