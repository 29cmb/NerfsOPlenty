package xyz.devcmb.nerfsoplenty.mixin;

import com.mojang.blaze3d.platform.FramerateLimitTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.devcmb.nerfsoplenty.NerfManager;

@Mixin(FramerateLimitTracker.class)
public class FramerateLimitTrackerMixin {
    @Inject(method = "getFramerateLimit", at = @At("HEAD"), cancellable = true)
    void getFramerateLimit(CallbackInfoReturnable<Integer> cir) {
        if(NerfManager.INSTANCE.isActive(NerfManager.TEN_FPS)) cir.setReturnValue(10);
    }
}
