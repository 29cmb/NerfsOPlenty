package xyz.devcmb.nerfsoplenty.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.devcmb.nerfsoplenty.NerfManager;
import xyz.devcmb.nerfsoplenty.NerfsOPlenty;

import java.util.Objects;

@Mixin(Gui.class)
public class GuiMixin {
	@Inject(at = @At("HEAD"), method = "extractCrosshair", cancellable = true)
	private void init(CallbackInfo info) {
		if(NerfManager.INSTANCE.isActive(NerfManager.NO_CURSOR)) info.cancel();
	}
}