package xyz.devcmb.nerfsoplenty.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.devcmb.nerfsoplenty.NerfManager;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @ModifyExpressionValue(
        method = "turnPlayer",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance;get()Ljava/lang/Object;", ordinal = 0)
    )
    private Object modifySensitivity(Object original) {
        if(NerfManager.INSTANCE.isActive(NerfManager.HYPERSPEED_SENSITIVITY)) return 1.0;
        if(NerfManager.INSTANCE.isActive(NerfManager.YAWN_SENSITIVITY)) return 0.0;
        return original;
    }
}
