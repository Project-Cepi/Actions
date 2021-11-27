package world.cepi.actions

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.entity.Entity
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.item.get
import world.cepi.kstom.item.item
import world.cepi.kstom.item.set

@Serializable
data class ActionItem(
    val action: Action,
    val timing: ActionTiming? = null,
    val targetArgType: TargetArgumentType = TargetArgumentType.NORMAL,
    val targetSystem: TargetSystemType = TargetSystemType.Normal
) {
    fun renderItem() = item(Material.RED_DYE) {

        displayName(
            Component.text(Regex("[A-Z][a-z]+")
                .findAll(action::class.simpleName!!)
                .map { it.value }.joinToString(" "), NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
        )

        if (timing != null) lore(
            Component.empty(),
            Component.text("Repeats every ${timing.repeatEvery.seconds}s, ${timing.repeatAmount} times")
        )

        this["action", ActionSerializer.module] = this@ActionItem
    }

    operator fun invoke(source: Entity, target: Entity) {
        targetSystem.lambda(source, target).forEach { targetSystemPair ->
            targetArgType.lambda(targetSystemPair.first, targetSystemPair.second).let { action.invoke(it.first, it.second) }
        }
    }

}

val ItemStack.actionItem: ActionItem? get() = this.get("action", ActionSerializer.module)