package world.cepi.actions

import net.minestom.server.extensions.Extension;
import net.minestom.server.utils.time.TimeUnit
import world.cepi.actions.command.ActionCommand
import world.cepi.kstom.Manager
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node

class ActionsExtension : Extension() {

    override fun initialize(): LoadStatus {
        // Register the action command after the server is loaded
        Manager.scheduler.buildTask {
            ActionCommand.register()
        }.delay(1, TimeUnit.SERVER_TICK).schedule()

        log.info("[Actions] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {
        ActionCommand.unregister()

        log.info("[Actions] has been disabled!")
    }

}
