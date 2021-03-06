package world.cepi.actions.command.subcommands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.actions.list.ActionManager
import world.cepi.kstom.command.arguments.generation.ChosenArgumentGeneration
import world.cepi.kstom.command.arguments.generation.ClassArgumentGenerator
import world.cepi.kstom.command.arguments.generation.ClassArgumentGenerator.Companion.syntaxesFrom
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object QuickSubcommand : Kommand({

    val self by literal

    val target by literal
    val entityToTarget = ArgumentType.Entity("target").singleEntity(true)

    ActionManager.forEach { clazz ->
        val syntaxes = ChosenArgumentGeneration(clazz)
        val actionLiteral = clazz.simpleName!!
            .dropLast("Action".length)
            .replaceFirstChar { it.lowercase() }.literal()

        syntaxes.applySyntax(this, arrayOf(self, actionLiteral), emptyArray()) { action ->
            action.invoke(player, player)
        }

        syntaxes.applySyntax(this, arrayOf(target, entityToTarget, actionLiteral), emptyArray()) { action ->
            action.invoke(player, (!entityToTarget).findFirstEntity(player))
        }
    }
}, "quick")