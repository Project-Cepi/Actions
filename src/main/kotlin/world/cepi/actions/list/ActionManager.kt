package world.cepi.actions.list

import world.cepi.actions.Action
import kotlin.reflect.KClass

object ActionManager : Iterable<KClass<out Action>> {

    override fun iterator() = list.iterator()

    private val list = mutableListOf(
        SoundAction::class,
        NearbySoundAction::class,
        FlingAction::class,
        RemoveAction::class,
        MessageAction::class
    )

    fun add(vararg classes: KClass<out Action>) = list.addAll(classes)
    fun remove(clazz: KClass<out Action>) = list.remove(clazz)

    inline fun <reified T: Action> add() = add(T::class)
}