package velord.bnrg.beatbox.model

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5


class BeatBox(private val assets: AssetManager) {

    var speedRate = 1.0f

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    init {
        sounds = loadSounds()
    }

    fun play(sound: Sound) =
        sound.soundId?.let {
            soundPool.play(
                it, 1.0f,
                1.0f, 1, 0, speedRate
            )
        }

    fun release() = soundPool.release()

    private fun loadSounds(): List<Sound> {
        val soundNames = loadSoundNames().apply {
            if (size == 0)
                return emptyList()
        }

        return mutableListOf<Sound>().apply {
            soundNames.forEach { fileName ->
                val assetPath = "$SOUNDS_FOLDER/$fileName"
                val sound = Sound(assetPath)
                try {
                    load(sound)
                    this.add(sound)
                } catch (ioe: IOException) {
                    Log.e(TAG, "Cound not load sound $fileName", ioe)
                }
            }
        }
    }


    private fun loadSoundNames(): Array<String> =
        try {
            assets.list(SOUNDS_FOLDER)!!
        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            arrayOf()
        }


    private fun load(sound: Sound) {
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }
}

