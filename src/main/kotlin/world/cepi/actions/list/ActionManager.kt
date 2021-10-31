package world.cepi.actions.list

import world.cepi.actions.Action
import kotlin.reflect.KClass

object ActionManager : MutableList<KClass<out Action>> by mutableListOf(
    SoundAction::class,
    NearbySoundAction::class,
    FlingAction::class,
    RemoveAction::class,
    MessageAction::class
)