package world.cepi.actions

import kotlinx.serialization.Serializable
import net.minestom.server.item.Material
import world.cepi.kstom.item.item
import world.cepi.kstom.item.set

@Serializable
data class ActionItem(
    val action: Action,
    val timing: ActionTiming
) {
    fun renderItem() = item(Material.RED_DYE) {
        this["action"] = this@ActionItem
    }

}