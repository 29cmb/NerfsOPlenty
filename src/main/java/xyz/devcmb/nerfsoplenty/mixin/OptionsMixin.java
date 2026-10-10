package xyz.devcmb.nerfsoplenty.mixin;

import net.minecraft.client.CameraType;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.devcmb.nerfsoplenty.NerfManager;

@Mixin(Options.class)
public class OptionsMixin {
    @Inject(method = "setCameraType", at = @At("HEAD"), cancellable = true)
    void setCameraType(CameraType cameraType, CallbackInfo ci) {
        if(
            NerfManager.INSTANCE.isActive(NerfManager.FORCED_THIRD_PERSON)
            || NerfManager.INSTANCE.isActive(NerfManager.FORCED_REVERSE_THIRD_PERSON)
        ) ci.cancel();
    }
}
