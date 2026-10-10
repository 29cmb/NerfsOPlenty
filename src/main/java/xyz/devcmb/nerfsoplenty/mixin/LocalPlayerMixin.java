package xyz.devcmb.nerfsoplenty.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec2;
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

    @ModifyExpressionValue(method = "applyInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/ClientInput;getMoveVector()Lnet/minecraft/world/phys/Vec2;"))
    Vec2 getMoveVector(Vec2 original) {
        return NerfManager.INSTANCE.isActive(NerfManager.INVERTED_CONTROLS) ? original.negated() : original;
    }
}
