package xyz.devcmb.nerfsoplenty.nerf

import net.minecraft.client.Minecraft

class NoHudNerf : Nerf("No Hud") {
    override fun activate() {
        Minecraft.getInstance().options.hideGui = true
    }

    override fun deactivate() {
        Minecraft.getInstance().options.hideGui = false
    }
}