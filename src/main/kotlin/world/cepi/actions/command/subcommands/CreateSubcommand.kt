package world.cepi.actions.command.subcommands

import world.cepi.actions.list.ActionManager
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object CreateSubcommand : Kommand({
    ActionManager.forEach { clazz ->
        val syntaxes = generateSyntaxes(clazz)
        val actionLiteral = clazz.simpleName!!
            .dropLast("Action".length)
            .replaceFirstChar { it.lowercase() }.literal()

        syntaxes.applySyntax(this, actionLiteral) { action ->
            action.invoke(player, player)
        }
    }
}, "create")