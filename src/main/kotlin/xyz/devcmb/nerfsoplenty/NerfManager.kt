package xyz.devcmb.nerfsoplenty

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.core.Registry
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import xyz.devcmb.nerfsoplenty.nerf.*

@Suppress("unused")
object NerfManager {
    val NERF_REGISTRY_KEY: ResourceKey<Registry<Nerf>> = ResourceKey.createRegistryKey(NerfsOPlenty.id("nerfs"))
    val REGISTRY = FabricRegistryBuilder.createDefaulted(NERF_REGISTRY_KEY, NerfsOPlenty.id("none")).buildAndRegister()

    val nerfs: ArrayList<NerfEntry> = ArrayList()

    var activeNerf: Nerf? = null
    var activeNerfID: Identifier? = null

    @JvmField val NONE = register("none", EmptyNerf("None", false))
    @JvmField val NO_CROSSHAIR = register("no_crosshair", EmptyNerf("No Crosshair"))
    @JvmField val THIRTY_FOV = register("30_fov", EmptyNerf("30 FOV"))
    @JvmField val NO_HOTBAR = register("no_hotbar", EmptyNerf("No Hotbar"))
    @JvmField val NO_HEALTH_BAR = register("no_healthbar", EmptyNerf("No Healthbar"))
    @JvmField val NO_HUD = register("no_hud", NoHudNerf())
    @JvmField val NO_SWORD = register("no_sword", EmptyNerf("No Sword"))
    @JvmField val NO_BOW = register("no_bow", NoBowNerf())
    @JvmField val HYPERSPEED_SENSITIVITY = register("hyperspeed_sensitivity", EmptyNerf("Hyperspeed Sensitivity"))
    @JvmField val YAWN_SENSITIVITY = register("yawn_sensitivity", EmptyNerf("Yawn Sensitivity"))
    @JvmField val TEN_FPS = register("10_fps", EmptyNerf("10 FPS"))
    @JvmField val NO_JUMPING = register("no_jumping", EmptyNerf("No Jumping"))
    @JvmField val FORCED_THIRD_PERSON = register("forced_third_person", ForcedThirdPersonNerf())
    @JvmField val FORCED_REVERSE_THIRD_PERSON = register("forced_reverse_third_person", ForcedReverseThirdPersonNerf())
    @JvmField val INVERTED_MOUSE = register("inverted_mouse", EmptyNerf("Inverted Mouse"))
    @JvmField val TWO_HUNDRED_PERCENT_FOV_EFFECTS = register("200_percent_fov_effects", EmptyNerf("200% FOV Effects"))
    @JvmField val ONE_FIFTY_PERCENT_DAMAGE_TILT = register("150_percent_damage_tilt", EmptyNerf("150% Damage Tilt"))
    @JvmField val INVERTED_CONTROLS = register("inverted_controls", EmptyNerf("Inverted Controls"))
    @JvmField val SHUFFLED_HOTBAR = register("shuffled_hotbar", ShuffledHotbarNerf())

    fun init() {
        nerfs.forEach {
            it.nerf.init()
            Registry.register(
                REGISTRY,
                it.id,
                it.nerf
            )
        }
    }

    fun register(id: String, nerf: Nerf) : Identifier {
        val identifier = NerfsOPlenty.id(id)
        nerfs.add(NerfEntry(identifier, nerf))
        return identifier
    }

    fun activateNerf(id: Identifier): Nerf {
        clearNerf()

        require(REGISTRY.containsKey(id)) { "Attempted to activate a nerf without an entry in the registry" }

        val nerf = REGISTRY.getValue(id)
        nerf.activate()
        nerf.isActive = true

        activeNerf = nerf
        activeNerfID = id

        return nerf
    }

    fun clearNerf() {
        activeNerf?.deactivate()
        activeNerf?.isActive = false
        activeNerf = null
        activeNerfID = null
    }

    fun isActive(identifier: Identifier): Boolean {
        return activeNerfID?.equals(identifier) ?: false
    }

    data class NerfEntry(val id: Identifier, val nerf: Nerf)
}