package world.cepi.actions.list

import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent
import net.minestom.server.utils.math.FloatRange
import world.cepi.actions.Action
import world.cepi.kstom.command.arguments.generation.annotations.GenerationConstructor
import world.cepi.kstom.util.random

class SoundAction(val sound: Sound) : Action() {

    @GenerationConstructor constructor(
        effect: SoundEvent,
        category: Sound.Source,
        volume: FloatRange,
        pitch: FloatRange
    ): this(Sound.sound(effect, category, volume.random(), pitch.random()))

    override fun invoke(entity: Entity, target: Entity?) {
        (entity as? Player)?.playSound(sound)
        (target as? Player)?.playSound(sound)
    }

}