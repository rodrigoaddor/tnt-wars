package dev.rodrick.tntwars.mixin;

import dev.rodrick.tntwars.TntWars;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class TntWaterMovementMixin {
    @Inject(at = @At("HEAD"), method = "updateWaterState()Z", cancellable = true)
    protected boolean cancelTntWaterUpdate(CallbackInfoReturnable<Boolean> callbackInfo) {
        //noinspection ConstantConditions
        if (((Object)this) instanceof TntEntity) {
            callbackInfo.setReturnValue(false);
            callbackInfo.cancel();
        }
        return false;
    }
}
