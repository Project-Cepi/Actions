package world.cepi.actions.command.subcommands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.actions.Action
import world.cepi.actions.ActionItem
import world.cepi.actions.TargetArgumentType
import world.cepi.actions.list.ActionManager
import world.cepi.kepi.item.AddCreationalItem
import world.cepi.kstom.command.arguments.generation.ClassArgumentGenerator.Companion.syntaxesFrom
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object CreateSubcommand : Kommand({
    ActionManager.forEach { clazz ->
        syntaxesFrom(clazz, clazz.simpleName!!
            .dropLast("Action".length)
            .replaceFirstChar { it.lowercase() }.literal()
        ) { action ->
            AddCreationalItem.put(player, ActionItem(action).renderItem())
        }
    }
}, "create")