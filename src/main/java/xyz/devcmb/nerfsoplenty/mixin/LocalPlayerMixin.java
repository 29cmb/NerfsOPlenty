package xyz.devcmb.nerfsoplenty.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.devcmb.nerfsoplenty.NerfManager;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @ModifyExpressionValue(method = "applyInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Input;jump()Z"))
    boolean applyInput(boolean original) {
        if(NerfManager.INSTANCE.isActive(NerfManager.NO_JUMPING)) return false;
        return original;
    }
}
