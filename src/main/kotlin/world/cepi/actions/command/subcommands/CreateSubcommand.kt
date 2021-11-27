package world.cepi.actions.command.subcommands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.actions.Action
import world.cepi.actions.ActionItem
import world.cepi.actions.TargetArgumentType
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

        val swapType = ArgumentType.Enum("swapType", TargetArgumentType::class.java)
            .setDefaultValue(TargetArgumentType.NORMAL)

        syntaxes.applySyntax(this, actionLiteral, swapType) { action ->
            player.inventory.addItemStack(ActionItem(action, targetArgType = !swapType).renderItem())
        }
    }
}, "create")