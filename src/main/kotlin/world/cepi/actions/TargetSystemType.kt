package world.cepi.actions

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.entity.Entity

@Serializable
sealed class TargetSystemType(@Transient val lambda: (Entity, Entity) -> List<Pair<Entity, Entity>> = { source, target -> listOf(source to target)}) {

    @Serializable
    object Normal : TargetSystemType({ source, target -> listOf(source to target)})

    @Serializable
    class Nearby(val radius: Double) : TargetSystemType({ source, _ ->
        source.instance!!.entities
            .filter { it.getDistance(source) <= radius }
            .map { source to it }
    })

}