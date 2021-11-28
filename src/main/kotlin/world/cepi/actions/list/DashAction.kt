package world.cepi.actions.list

import kotlinx.serialization.Serializable
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent
import world.cepi.actions.Action
import world.cepi.kstom.command.arguments.generation.annotations.DefaultNumber

@Serializable
data class DashAction(
    val power: Double = 15.0
) : Action() {

    override fun invoke(source: Entity, target: Entity?) {
        source.velocity = source.position.direction().normalize().mul(power)
        (source as? Player)?.playSound(Sound.sound(SoundEvent.ENTITY_PLAYER_ATTACK_SWEEP, Sound.Source.MASTER, 1f, 1f))
    }

}