package world.cepi.actions.list

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity
import world.cepi.actions.Action

@Serializable
class VampirePercentageAction(val percentage: Float) : Action() {

    override fun invoke(source: Entity, target: Entity?) {
        VampireFixedAction(((target as? LivingEntity)?.health ?: return) * percentage).invoke(source, target)
    }

}