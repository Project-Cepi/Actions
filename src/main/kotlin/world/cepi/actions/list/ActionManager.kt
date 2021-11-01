package world.cepi.actions.list

import world.cepi.actions.Action
import kotlin.reflect.KClass

object ActionManager : MutableList<KClass<out Action>> by mutableListOf(
    SoundAction::class,
    NearbySoundAction::class,
    FlingAction::class,
    RemoveAction::class,
    MessageAction::class
) {
    fun add(vararg classes: KClass<out Action>) = addAll(classes)

    inline fun <reified T: Action> add() = add(T::class)
}