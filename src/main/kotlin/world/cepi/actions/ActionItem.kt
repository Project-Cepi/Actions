package world.cepi.actions

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.entity.Entity
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.item.get
import world.cepi.kstom.item.item
import world.cepi.kstom.item.set
import world.cepi.kstom.util.sendMessage

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

        lore(
            Component.empty(),
            timing?.display() ?: Component.text("Timing: ", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
                .append(Component.text("None", NamedTextColor.RED)),
            Component.text("Swap Type: ", NamedTextColor.GRAY).append(Component.text(targetArgType.demonstrate, NamedTextColor.RED)).decoration(TextDecoration.ITALIC, false),
            Component.text("Targeting: ", NamedTextColor.GRAY).append(Component.text(targetSystem.display, NamedTextColor.RED)).decoration(TextDecoration.ITALIC, false)
        )

        this["action", ActionSerializer.module] = this@ActionItem
    }

    operator fun invoke(source: Entity, target: Entity?) {
        targetSystem.lambda(source, target).forEach { targetSystemPair ->
            targetArgType.lambda(targetSystemPair.first, targetSystemPair.second).let {
                if (it.first == null) return@let
                action.invoke(it.first!!, it.second)
            }
        }
    }

}

val ItemStack.actionItem: ActionItem? get() = this.get("action", ActionSerializer.module)