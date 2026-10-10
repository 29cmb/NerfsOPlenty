package xyz.devcmb.nerfsoplenty.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.devcmb.nerfsoplenty.NerfManager;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    @Nullable
    public LocalPlayer player;

    @Inject(
        method = "handleKeybinds",
        at = @At(value = "FIELD", target = "Lnet/minecraft/client/Options;hideGui:Z"),
        cancellable = true
    )
    void disableHidingGUI(CallbackInfo ci) {
        if(NerfManager.INSTANCE.isActive(NerfManager.NO_HUD)) ci.cancel();
    }

    @Inject(
        method = "startAttack",
        at = @At("HEAD"),
        cancellable = true
    )
    void disableSword(CallbackInfoReturnable<Boolean> cir) {
        if(player == null) return;

        ItemStack item = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (item.isEmpty()) return;

        if(
            NerfManager.INSTANCE.isActive(NerfManager.NO_SWORD)
            && player.getItemInHand(InteractionHand.MAIN_HAND).is(ItemTags.SWORDS)
        ) {
            cir.setReturnValue(false);
        }
    }
}
