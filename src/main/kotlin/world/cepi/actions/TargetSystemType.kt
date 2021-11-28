package world.cepi.actions

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.minestom.server.entity.Entity

@Serializable
sealed class TargetSystemType {

    @Transient open val lambda: ((Entity, Entity?) -> List<Pair<Entity, Entity?>>) = { source, target -> listOf(source to target)}
    abstract val display: String

    @Serializable
    object Normal : TargetSystemType() {
        override val display = "Normal"
    }

    @Serializable
    data class Nearby(
        val radius: Double,
        val keepPlayer: Boolean = false
    ) : TargetSystemType() {

        @Transient
        override val lambda: ((Entity, Entity?) -> List<Pair<Entity, Entity?>>) = { source, _ ->
            source.instance!!.entities
                .filter { keepPlayer || it != source }
                .filter { it.getDistance(source) <= radius }
                .map { source to it }
        }

        override val display = "Nearby (${radius}m)"
    }

}