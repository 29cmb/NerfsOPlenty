package xyz.devcmb.nerfsoplenty.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.devcmb.nerfsoplenty.NerfManager;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @ModifyExpressionValue(
        method = "bobHurt",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/renderer/state/OptionsRenderState;damageTiltStrength:D"
        )
    )
    private double modifyDamageTiltStrength(double strength) {
        return NerfManager.INSTANCE.isActive(NerfManager.ONE_FIFTY_PERCENT_DAMAGE_TILT) ? 1.5 : strength;
    }
}