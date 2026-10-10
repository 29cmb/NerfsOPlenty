package xyz.devcmb.nerfsoplenty.mixin;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.devcmb.nerfsoplenty.NerfManager;

@Mixin(Gui.class)
public class GuiMixin {
	@Inject(at = @At("HEAD"), method = "extractCrosshair", cancellable = true)
	private void extractCrosshair(CallbackInfo ci) {
		if(NerfManager.INSTANCE.isActive(NerfManager.NO_CROSSHAIR)) ci.cancel();
	}

	@Inject(at = @At("HEAD"), method = "extractItemHotbar", cancellable = true)
	private void extractItemHotbar(CallbackInfo ci) {
		if(NerfManager.INSTANCE.isActive(NerfManager.NO_HOTBAR)) ci.cancel();
	}

	@Inject(at = @At("HEAD"), method = "extractHearts", cancellable = true)
	private void extractHearts(CallbackInfo ci) {
		if(NerfManager.INSTANCE.isActive(NerfManager.NO_HEALTH_BAR)) ci.cancel();
	}
}