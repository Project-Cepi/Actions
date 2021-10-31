package world.cepi.actions.command

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.actions.list.ActionManager
import world.cepi.kstom.command.arguments.generation.GeneratedArguments.Companion.createSyntaxesFrom
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object ActionCommand : Kommand({

    val self by literal

    val target by literal
    val entityToTarget = ArgumentType.Entity("target").singleEntity(true)

    val autoTarget by literal

    ActionManager.forEach { clazz ->
        val syntaxes = generateSyntaxes(clazz)
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
}, "action")