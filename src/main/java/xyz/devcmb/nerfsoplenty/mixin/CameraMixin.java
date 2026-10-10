package xyz.devcmb.nerfsoplenty.mixin;

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
}
