package world.cepi.actions

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.item.get
import world.cepi.kstom.item.item
import world.cepi.kstom.item.set

@Serializable
data class ActionItem(
    val action: Action,
    val timing: ActionTiming? = null
) {
    fun renderItem() = item(Material.RED_DYE) {
        this["action", ActionSerializer.module] = this@ActionItem
    }

    operator fun invoke(source: Entity, target: Entity) {
        action.invoke(source, target)
    }

}

val ItemStack.actionItem: ActionItem? get() = this.get("action")