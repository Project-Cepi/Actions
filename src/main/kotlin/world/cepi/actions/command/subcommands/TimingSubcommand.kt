package world.cepi.actions.command.subcommands

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.actions.ActionTiming
import world.cepi.actions.actionItem
import world.cepi.kstom.command.kommand.Kommand
import java.time.Duration

object TimingSubcommand : Kommand({
    val repeatDuration = ArgumentType.Time("repeatDuration")
    val repeatAmount = ArgumentType.Integer("repeatAmount").setDefaultValue(-1)
    val delay = ArgumentType.Time("delay").setDefaultValue(Duration.ofSeconds(0))

    syntax(repeatDuration, repeatAmount, delay) {
        player.itemInMainHand = player.itemInMainHand.actionItem?.copy(timing = ActionTiming(
            !repeatDuration,
            if (!repeatAmount == -1) null else !repeatAmount,
            !delay
        ))?.renderItem() ?: return@syntax
    }
}, "timing")