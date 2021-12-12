package world.cepi.actions.list

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import world.cepi.actions.Action
import world.cepi.actions.ActionSerializer
import kotlin.reflect.KClass

object ActionManager : Iterable<KClass<out Action>> {

    override fun iterator() = list.iterator()

    private val list = mutableListOf(
        SoundAction::class,
        FlingAction::class,
        RemoveAction::class,
        DebugAction::class,
        TeleportAction::class,
        DashAction::class,
        MessageAction::class,
        VampireFixedAction::class,
        VampirePercentageAction::class
    )

    @OptIn(InternalSerializationApi::class)
    fun add(vararg classes: KClass<out Action>) {
        list.addAll(classes)
        ActionSerializer.actions.addAll(classes.map { ActionSerializer.ActionType(it, it.serializer()) })
    }

    fun remove(clazz: KClass<out Action>) = list.remove(clazz)

    @OptIn(InternalSerializationApi::class)
    inline fun <reified T: Action> add() {
        add(T::class)
        ActionSerializer.actions.add(ActionSerializer.ActionType(T::class, T::class.serializer()))
    }
}