package world.cepi.actions.list

import kotlinx.serialization.Serializable
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Entity
import net.minestom.server.sound.SoundEvent
import net.minestom.server.utils.math.FloatRange
import world.cepi.actions.Action
import world.cepi.kstom.command.arguments.generation.annotations.GenerationConstructor
import world.cepi.kstom.serializer.SoundSerializer
import world.cepi.kstom.util.playSound
import world.cepi.kstom.util.random

@Serializable
data class NearbySoundAction(
    @Serializable(with = SoundSerializer::class)
    val sound: Sound
) : Action() {

    @GenerationConstructor
    constructor(
        effect: SoundEvent,
        category: Sound.Source,
        volume: FloatRange,
        pitch: FloatRange
    ): this(Sound.sound(effect, category, volume.random(), pitch.random()))

    override fun invoke(source: Entity, target: Entity?) {
        source.instance!!.playSound(sound, source.position)
    }

}