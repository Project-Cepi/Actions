package world.cepi.actions.command

import world.cepi.actions.list.actions
import world.cepi.kstom.command.arguments.generation.GeneratedArguments.Companion.createSyntaxesFrom
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object ActionCommand : Kommand({
    actions.forEach { clazz ->
        createSyntaxesFrom(clazz, clazz.simpleName!!.dropLast("Action".length).literal()) { action ->
            action.invoke(player, player)
        }
    }
}, "action")