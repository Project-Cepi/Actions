package world.cepi.actions

import net.minestom.server.extensions.Extension;
import world.cepi.actions.command.ActionCommand
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node

class ActionsExtension : Extension() {

    override fun initialize(): LoadStatus {
        // TODO register actions command after initialization
        ActionCommand.register()

        log.info("[Actions] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {
        ActionCommand.unregister()

        log.info("[Actions] has been disabled!")
    }

}
