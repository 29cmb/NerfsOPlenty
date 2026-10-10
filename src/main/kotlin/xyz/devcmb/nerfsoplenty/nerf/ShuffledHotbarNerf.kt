package xyz.devcmb.nerfsoplenty.nerf

class ShuffledHotbarNerf : Nerf("Shuffled Hotbar") {
    val shuffledIndices: HashMap<Int, Int> = HashMap()
    val inverseIndices: HashMap<Int, Int> = HashMap()
    override fun activate() {
        shuffledIndices.clear()
        inverseIndices.clear()
        (0..8).shuffled().forEachIndexed { index, value ->
            shuffledIndices[index] = value
            inverseIndices[value] = index
        }
    }
}