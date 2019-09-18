package velord.bnrg.beatbox.viewModel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import velord.bnrg.beatbox.model.BeatBox
import java.math.RoundingMode

private const val ANDROID_PROGRESS = "android:progress"
private const val MIN_RATE = 0.1f
private const val DIVIDER_RATE = 10f

class SpeedRateViewModel(private val beatBox: BeatBox,
                         speedRateInit: Int) : BaseObservable() {

    private var playbackSpeed: Int = speedRateInit
    private var playbackSpeedDisplay: Int = playbackSpeed

    @Bindable
    fun getPlaybackSpeed(): Int {
        return playbackSpeed
    }

    fun setPlaybackSpeed(speed: Int) {
        this.playbackSpeed = speed
        notifyChange()
        setPlaybackSpeedDisplay(speed)
        changePlayBackSpeed(intToFloat(speed))
        Log.d("PlaybackSpeedViewModel", "Playback Speed set at $speed")
    }

    @Bindable // how this impossible to return string ?
    fun getPlaybackSpeedDisplay(): String {
        return intToFloat(playbackSpeedDisplay).toString()
    }

    fun setPlaybackSpeedDisplay(speed: Int) {
        this.playbackSpeedDisplay = speed
        notifyChange()
    }

    private fun changePlayBackSpeed(progress: Float) {
        beatBox.speedRate = progress
    }

    private fun intToFloat(value: Int) =
        (MIN_RATE + (value / DIVIDER_RATE))
            .toBigDecimal()
            .setScale(1, RoundingMode.DOWN)
            .toFloat()
}

