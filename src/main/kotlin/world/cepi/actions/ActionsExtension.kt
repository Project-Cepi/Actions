package world.cepi.actions

import net.minestom.server.extensions.Extension;
import world.cepi.actions.command.ActionCommand

class ActionsExtension : Extension() {

    override fun initialize() {
        logger.info("[Actions] has been enabled!")
    }

    override fun terminate() {
        ActionCommand.unregister()

        logger.info("[Actions] has been disabled!")
    }

    override fun postInitialize() {
        ActionCommand.register()
    }

}
