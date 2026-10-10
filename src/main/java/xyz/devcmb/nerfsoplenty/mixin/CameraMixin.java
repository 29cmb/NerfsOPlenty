package xyz.devcmb.nerfsoplenty.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.devcmb.nerfsoplenty.NerfManager;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "calculateFov", at = @At("HEAD"), cancellable = true)
    void getFov(CallbackInfoReturnable<Float> cir) {
        if(NerfManager.INSTANCE.isActive(NerfManager.THIRTY_FOV)) cir.setReturnValue(30.0f);
    }

    @ModifyExpressionValue(method = "tickFov", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;"))
    Object tickFov(Object original) {
        if(NerfManager.INSTANCE.isActive(NerfManager.TWO_HUNDRED_PERCENT_FOV_EFFECTS)) return 2.0;
        return original;
    }
}
