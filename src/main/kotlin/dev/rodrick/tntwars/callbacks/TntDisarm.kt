package dev.rodrick.tntwars.callbacks

import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.TntEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World

object TntDisarm {
    fun register() {
        AttackEntityCallback.EVENT.register(AttackEntityCallback { player: PlayerEntity, world: World, hand: Hand, entity: Entity, hitResult: EntityHitResult? ->
            if (!player.isSpectator && entity is TntEntity) {
                if (entity.fuse < 20) {
                    if (player is ServerPlayerEntity && world is ServerWorld) {
                        world.spawnParticles(
                            ParticleTypes.POOF,
                            entity.x,
                            entity.y + 1,
                            entity.z,
                            20,
                            0.0,
                            0.25,
                            0.0,
                            0.05
                        )
                    }
                    return@AttackEntityCallback ActionResult.CONSUME
                }

                entity.remove(Entity.RemovalReason.DISCARDED)

                val itemStack = ItemStack(Items.TNT, 1)
                val item = ItemEntity(world, entity.x, entity.y, entity.z, itemStack)
                world.spawnEntity(item)

                ActionResult.SUCCESS
            }

            ActionResult.PASS
        })
    }
}