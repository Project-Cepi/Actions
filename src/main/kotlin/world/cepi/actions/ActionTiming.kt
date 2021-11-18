package world.cepi.actions

import kotlinx.serialization.Serializable
import world.cepi.kstom.Manager
import world.cepi.kstom.serializer.DurationSerializer
import java.time.Duration

@Serializable
data class ActionTiming(
    @Serializable(with = DurationSerializer::class)
    val repeatEvery: Duration,
    val repeatAmount: Int?
) {
    fun execute(lambda: () -> Unit) {
        Manager.scheduler.buildTask(lambda).repeat(repeatEvery).schedule()
    }
}