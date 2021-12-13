package world.cepi.actions.list

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.utils.location.RelativeVec
import world.cepi.actions.Action
import world.cepi.kstom.serializer.RelativeVecSerializer

@Serializable
class TeleportAction(
    @Serializable(with = RelativeVecSerializer::class)
    val position: RelativeVec
) : Action() {

    override fun invoke(source: Entity, target: Entity?) {
        source.teleport(position.from(source.position).asPosition())
    }

}