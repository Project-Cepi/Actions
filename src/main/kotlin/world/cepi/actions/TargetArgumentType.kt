package world.cepi.actions

import net.minestom.server.entity.Entity

enum class TargetArgumentType(val lambda: (Entity, Entity) -> Pair<Entity, Entity>, val demonstrate: String) {

    SELF_SOURCE({ a, _ -> a to a}, "source, source"),
    SELF_TARGET({ _, b -> b to b }, "target, target"),
    SWAP({ a, b -> b to a}, "target, source"),
    NORMAL({ a, b -> a to b }, "source, target")

}