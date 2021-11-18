package world.cepi.actions.command

import world.cepi.actions.command.subcommands.QuickSubcommand
import world.cepi.kstom.command.kommand.Kommand

object ActionCommand : Kommand({
    addSubcommands(
        QuickSubcommand
    )
}, "action")