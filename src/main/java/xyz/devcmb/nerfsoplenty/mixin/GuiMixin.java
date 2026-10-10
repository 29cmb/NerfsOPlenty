package xyz.devcmb.nerfsoplenty.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.devcmb.nerfsoplenty.NerfManager;
import xyz.devcmb.nerfsoplenty.nerf.ShuffledHotbarNerf;
import java.util.Objects;

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

	@ModifyArg(method = "extractItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;getItem(I)Lnet/minecraft/world/item/ItemStack;"))
	int getShuffledItemStack(int slot) {
		if(NerfManager.INSTANCE.isActive(NerfManager.SHUFFLED_HOTBAR)) {
			return ((ShuffledHotbarNerf) Objects.requireNonNull(NerfManager.INSTANCE.getActiveNerf())).getShuffledIndices().get(slot);
		}
		return slot;
	}

	@ModifyExpressionValue(
		method = "extractItemHotbar",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;getSelectedSlot()I", ordinal = 0)
	)
	int getSelectedSlot(int original) {
		if (NerfManager.INSTANCE.isActive(NerfManager.SHUFFLED_HOTBAR)) {
			ShuffledHotbarNerf nerf = (ShuffledHotbarNerf) Objects.requireNonNull(NerfManager.INSTANCE.getActiveNerf());
			return nerf.getInverseIndices().getOrDefault(original, original);
		}
		return original;
	}
}