package dev.rodrick.tntwars.mixin;

import dev.rodrick.tntwars.commands.TntMovementCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class TntWaterMovementMixin {
    @Inject(at = @At("HEAD"), method = "updateWaterState()Z", cancellable = true)
    protected void cancelTntWaterUpdate(CallbackInfoReturnable<Boolean> callbackInfo) {
        //noinspection ConstantConditions
        if (!TntMovementCommand.INSTANCE.isTntMovementEnabled() && ((Object)this) instanceof TntEntity) {
            callbackInfo.setReturnValue(false);
            callbackInfo.cancel();
        }
    }
}
