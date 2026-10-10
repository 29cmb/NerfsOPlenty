package xyz.devcmb.nerfsoplenty.nerf

import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ProjectileWeaponItem

class NoBowNerf : Nerf("No Bow") {
    override fun init() {
        UseItemCallback.EVENT.register { player, _, hand ->
            val stack: ItemStack = player.getItemInHand(hand)
            if(stack.item is ProjectileWeaponItem && isActive) InteractionResult.FAIL else InteractionResult.PASS
        }
    }
}