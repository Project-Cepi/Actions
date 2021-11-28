package world.cepi.actions.list

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import world.cepi.actions.Action

@Serializable
class DebugAction : Action() {

    override fun invoke(source: Entity, target: Entity?) {

        if (target == null) return

        if (source == target) {
            (source as? Player)?.sendMessage(
                Component.text("You", NamedTextColor.RED)
                    .append(Component.text(" are the target.", NamedTextColor.GRAY))
            )
            return
        }

        (source as? Player)?.sendMessage(Component.text("Target found ", NamedTextColor.GRAY)
            .append(Component.text(source.getDistance(target).toInt(), NamedTextColor.RED))
            .append(Component.text("m away. UUID: ", NamedTextColor.GRAY))
            .append(Component.text(target.uuid.toString(), NamedTextColor.RED))
        )
    }

}