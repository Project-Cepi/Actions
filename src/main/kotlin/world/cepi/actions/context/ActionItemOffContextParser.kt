package world.cepi.actions.context

import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import world.cepi.actions.ActionItem
import world.cepi.actions.actionItem
import world.cepi.kstom.command.arguments.context.ContextParser

object ActionItemOffContextParser : ContextParser<ActionItem> {

    override fun parse(sender: CommandSender): ActionItem? =
        (sender as? Player)?.itemInOffHand?.actionItem

    override val callbackMessage = Component.text("No action item found in off hand")

}