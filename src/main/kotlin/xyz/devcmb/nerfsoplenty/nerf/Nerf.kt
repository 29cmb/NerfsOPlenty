package xyz.devcmb.nerfsoplenty.nerf

/**
 * A superclass for all Nerfs
 */
abstract class Nerf(val name: String) {
    var isActive: Boolean = false

    open fun init() {}
    open fun activate() {}
    open fun deactivate() {}
}