package xyz.devcmb.nerfsoplenty

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.core.Registry
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import xyz.devcmb.nerfsoplenty.nerf.EmptyNerf
import xyz.devcmb.nerfsoplenty.nerf.Nerf

object NerfManager {
    val NERF_REGISTRY_KEY: ResourceKey<Registry<Nerf>> = ResourceKey.createRegistryKey(NerfsOPlenty.id("nerfs"))
    val REGISTRY = FabricRegistryBuilder.createDefaulted(NERF_REGISTRY_KEY, NerfsOPlenty.id("none")).buildAndRegister()

    val nerfs: ArrayList<NerfEntry> = ArrayList()

    private var activeNerf: Nerf? = null
    private var activeNerfID: Identifier? = null

    @JvmField val NONE = register("none", EmptyNerf())
    @JvmField val NO_CURSOR = register("no_cursor", EmptyNerf())
    @JvmField val THIRTY_FOV = register("30_fov", EmptyNerf())

    fun init() {
        nerfs.forEach {
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

    fun activateNerf(id: Identifier) {
        activeNerf?.deactivate()
        activeNerf = null
        activeNerfID = null

        require(REGISTRY.containsKey(id)) { "Attempted to activate a nerf without an entry in the registry" }

        val nerf = REGISTRY.getValue(id)
        nerf.activate()

        activeNerf = nerf
        activeNerfID = id
    }

    fun isActive(identifier: Identifier): Boolean {
        return activeNerfID?.equals(identifier) ?: false
    }

    data class NerfEntry(val id: Identifier, val nerf: Nerf)
}