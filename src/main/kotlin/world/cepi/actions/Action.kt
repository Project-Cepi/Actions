package world.cepi.actions

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity

@FunctionalInterface
@Serializable
abstract class Action {

    abstract operator fun invoke(source: Entity, target: Entity?)

}