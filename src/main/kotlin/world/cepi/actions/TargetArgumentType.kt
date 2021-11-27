package world.cepi.actions

import net.minestom.server.entity.Entity

enum class TargetArgumentType(val lambda: (Entity, Entity) -> Pair<Entity, Entity>) {

    SELF_SOURCE({ a, _ -> a to a}),
    SELF_TARGET({ _, b -> b to b }),
    SWAP({ a, b -> b to a}),
    NORMAL({ a, b -> a to b })

}