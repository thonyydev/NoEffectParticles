package net.thonyy.noeffectparticles.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.thonyy.noeffectparticles.config.NoEffectParticlesConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(
            method = "tickEffects",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
            ),
            cancellable = true
    )
    private void noeffectparticles$beforeEffectParticle(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!entity.level().isClientSide()) {
            return;
        }

        if (shouldRemoveParticles(entity)) {
            ci.cancel();
        }
    }

    private boolean shouldRemoveParticles(LivingEntity entity) {
        if (entity instanceof Player player) {
            if (player.isLocalPlayer()) {
                return NoEffectParticlesConfig.removeOwnParticles();
            }

            return NoEffectParticlesConfig.removeOtherPlayerParticles();
        }

        return NoEffectParticlesConfig.removeMobParticles();
    }
}