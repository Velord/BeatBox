package velord.bnrg.beatbox.model

private const val WAV = ".wav"

class Sound(private val assetPath: String) {

    val name = assetPath.split("/").last().removeSuffix(WAV)
}