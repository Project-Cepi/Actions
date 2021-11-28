package world.cepi.actions

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.entity.Entity
import world.cepi.kstom.util.sendMessage

@Serializable
sealed class TargetSystemType(
    @Transient val lambda: (Entity, Entity?) -> List<Pair<Entity, Entity?>> = { source, target -> listOf(source to target)},
    @Transient val display: String = "None"
) {

    @Serializable
    object Normal : TargetSystemType(display = "Normal")

    @Serializable
    data class Nearby(val radius: Double) : TargetSystemType({ source, _ ->
        source.instance!!.entities
            .filter { it != source }
            .filter { it.getDistance(source) <= radius }
            .map { source to it }
    }, "Nearby (${radius}m)")

}