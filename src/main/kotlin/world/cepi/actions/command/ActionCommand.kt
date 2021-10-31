package world.cepi.actions.command

import world.cepi.actions.list.actions
import world.cepi.kstom.command.arguments.generation.GeneratedArguments.Companion.createSyntaxesFrom
import world.cepi.kstom.command.kommand.Kommand

object ActionCommand : Kommand({
    actions.forEach { clazz ->
        createSyntaxesFrom(clazz) { action ->
            action.invoke(player, player)
        }
    }
}, "action")