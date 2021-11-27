package world.cepi.actions.command

import world.cepi.actions.command.subcommands.*
import world.cepi.kstom.command.kommand.Kommand

object ActionCommand : Kommand({

    addSubcommands(
        QuickSubcommand,
        CreateSubcommand,
        RunSubcommand,
        SwapSubcommand,
        TargetSystemSubcommand
    )
}, "action")