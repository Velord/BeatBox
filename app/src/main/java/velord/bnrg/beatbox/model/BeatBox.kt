package velord.bnrg.beatbox.model

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound>  =
        mutableListOf<Sound>().apply {
            val soundNames: Array<String>
            try {
                soundNames = assets.list(SOUNDS_FOLDER)!!

            } catch (e: Exception) {
                Log.e(TAG, "Could not list assets", e)
                return emptyList()
            }

            soundNames.forEach { fileName ->
                val assetPath = "$SOUNDS_FOLDER/$fileName"
                val sound = Sound(assetPath)
                this.add(sound)
            }
        }

}