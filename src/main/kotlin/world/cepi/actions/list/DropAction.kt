package world.cepi.actions.list

import net.minestom.server.entity.Entity
import world.cepi.actions.Action
import world.cepi.drops.Drops

@kotlinx.serialization.Serializable
class DropAction(val drops: Drops): Action() {

    override fun invoke(source: Entity, target: Entity?) {
        drops.dropItems(source.instance!!, source.position)
    }

}