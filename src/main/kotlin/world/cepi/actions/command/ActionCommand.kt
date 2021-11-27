package world.cepi.actions.command

import world.cepi.actions.command.subcommands.CreateSubcommand
import world.cepi.actions.command.subcommands.QuickSubcommand
import world.cepi.actions.command.subcommands.RunSubcommand
import world.cepi.actions.command.subcommands.SwapSubcommand
import world.cepi.kstom.command.kommand.Kommand

object ActionCommand : Kommand({

    addSubcommands(
        QuickSubcommand,
        CreateSubcommand,
        RunSubcommand,
        SwapSubcommand
    )
}, "action")