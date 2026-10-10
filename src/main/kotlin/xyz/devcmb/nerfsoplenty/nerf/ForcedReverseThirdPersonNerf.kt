package xyz.devcmb.nerfsoplenty.nerf

import net.minecraft.client.CameraType
import net.minecraft.client.Minecraft

class ForcedReverseThirdPersonNerf : Nerf("Forced Reverse Third Person") {
    override fun activate() {
        Minecraft.getInstance().options.cameraType = CameraType.THIRD_PERSON_FRONT
    }
}