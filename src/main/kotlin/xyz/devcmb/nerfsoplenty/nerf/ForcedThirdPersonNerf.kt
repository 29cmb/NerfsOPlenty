package xyz.devcmb.nerfsoplenty.nerf

import net.minecraft.client.CameraType
import net.minecraft.client.Minecraft

class ForcedThirdPersonNerf : Nerf("Forced Third Person") {
    override fun activate() {
        Minecraft.getInstance().options.cameraType = CameraType.THIRD_PERSON_BACK
    }
}