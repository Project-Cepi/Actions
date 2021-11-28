package world.cepi.actions.list

import net.minestom.server.entity.Entity
import world.cepi.actions.Action

class DashAction(val power: Double) : Action() {

    override fun invoke(source: Entity, target: Entity?) {
        source.velocity = source.position.direction().normalize().mul(power)
    }

}