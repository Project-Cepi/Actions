package world.cepi.actions

import net.minestom.server.entity.Entity

@FunctionalInterface
abstract class Action {

    abstract operator fun invoke(entity: Entity, target: Entity?)

}