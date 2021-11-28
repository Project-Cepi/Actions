package world.cepi.actions

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import world.cepi.kstom.Manager
import world.cepi.kstom.serializer.DurationSerializer
import java.time.Duration

@Serializable
data class ActionTiming(
    @Serializable(with = DurationSerializer::class)
    val repeatEvery: Duration,
    val repeatAmount: Int?,
    @Serializable(with = DurationSerializer::class)
    val delay: Duration?
) {
    fun execute(lambda: () -> Unit) {
        Manager.scheduler.buildTask(lambda).repeat(repeatEvery).let { if (delay != null) it.delay(delay) else it }.schedule()
    }

    fun display() = Component.text("Timing: Repeats every ", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
        .append(Component.text(repeatEvery.seconds, NamedTextColor.RED))
        .append(Component.text("s ", NamedTextColor.GRAY))
        .let {
            if (repeatAmount != null) it.append(Component.text(repeatAmount, NamedTextColor.RED))
                .append(Component.text(" times"))
            else it
        }
        .let {
            if (delay != null) it.append(Component.text("After "))
                .append(Component.text(delay.seconds, NamedTextColor.RED))
                .append(Component.text("s", NamedTextColor.GRAY))
            else it
        }
}