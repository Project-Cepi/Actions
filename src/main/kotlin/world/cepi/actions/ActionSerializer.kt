package world.cepi.actions

import kotlinx.serialization.*
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import world.cepi.actions.list.*
import kotlin.reflect.KClass

object ActionSerializer {

    class ActionType(val clazz: KClass<out Action>, val serializer: KSerializer<out Action>)

    val actions = mutableListOf<ActionType>()

    @OptIn(InternalSerializationApi::class)
    val module get() = SerializersModule {
        polymorphic(Action::class) {
            subclass(FlingAction::class)
            subclass(MessageAction::class)
            subclass(RemoveAction::class)
            subclass(TeleportAction::class)
            subclass(SoundAction::class)
            subclass(DebugAction::class)
            subclass(DashAction::class)
            subclass(VampireFixedAction::class)
            subclass(VampirePercentageAction::class)

            actions.forEach {
                subclass(it.clazz as KClass<Action>, it.serializer as KSerializer<Action>)
            }
        }
    }
}