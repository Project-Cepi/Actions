package world.cepi.actions

import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import world.cepi.actions.list.*
import kotlin.reflect.KClass

object ActionSerializer {

    val actions = mutableListOf<KClass<out Action>>()

    val module get() = SerializersModule {
        polymorphic(Action::class) {
            subclass(FlingAction::class)
            subclass(MessageAction::class)
            subclass(NearbySoundAction::class)
            subclass(RemoveAction::class)
            subclass(SoundAction::class)
        }
    }
}