package world.cepi.actions.list

import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.Player
import net.minestom.server.entity.damage.DamageType
import world.cepi.actions.Action
import world.cepi.particle.Particle
import world.cepi.particle.ParticleType
import world.cepi.particle.data.OffsetAndSpeed
import world.cepi.particle.extra.Dust
import world.cepi.particle.renderer.Renderer
import world.cepi.particle.renderer.render

@Serializable
class VampireFixedAction(val amount: Float) : Action() {

    companion object {
        val particle = Particle.particle(
            ParticleType.DUST,
            1,
            OffsetAndSpeed(),
            Dust(255f, 0f,0f, 1f)
        )
    }

    override fun invoke(source: Entity, target: Entity?) {
        val damageToTake = ((target as? LivingEntity)?.health ?: return).let { remainingHealth ->
            if (amount > remainingHealth) remainingHealth
            else amount
        }

        val livingSource = (source as? LivingEntity) ?: return

        val renderer = Renderer.fixedLine(source.position.asVec().withY { it + 1 }, target.position.asVec().withY { it + 1 }, .5)

        (source as? Player)?.let { renderer.render(particle, it) }
        (target as? Player)?.let { renderer.render(particle, it) }

        livingSource.health += amount
        target.damage(DamageType.fromEntity(source), damageToTake)
    }

}