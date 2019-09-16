package velord.bnrg.beatbox.viewModel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import velord.bnrg.beatbox.model.BeatBox
import kotlin.reflect.KProperty


private const val ANDROID_PROGRESS = "android:progress"

class SpeedRateViewModel(private val beatBox: BeatBox) : BaseObservable() {

    private fun changePlayBackSpeed(progress: Float) {
        beatBox.speedRate = progress
    }

    private var playbackSpeed: Int = 0
    private var playbackSpeedDisplay: Int = 0

    @Bindable
    fun getPlaybackSpeed(): Int {
        return playbackSpeed
    }

    fun setPlaybackSpeed(speed: Int) {
        this.playbackSpeed = speed
        notifyChange()
        setPlaybackSpeedDisplay(speed)
        changePlayBackSpeed(speed.toFloat())
        Log.d("PlaybackSpeedViewModel", "Playback Speed set at $speed")
    }

    @Bindable
    fun getPlaybackSpeedDisplay(): String {
        return Integer.toString(playbackSpeedDisplay)
    }

    fun setPlaybackSpeedDisplay(speed: Int) {
        this.playbackSpeedDisplay = speed
        notifyChange()
    }

//    companion object {
//        @BindingAdapter(ANDROID_PROGRESS)
//        @JvmStatic
//        fun setSeekBarProgress(seekBar: SeekBar, progress: Int) {
//            try {
//                seekBar.progress = progress
//            } catch (nfe: Resources.NotFoundException) {
//                nfe.printStackTrace()
//            }
//
//        }
//
//        @InverseBindingAdapter(attribute = ANDROID_PROGRESS)
//        @JvmStatic
//        fun getSeekBarProgress(seekBar: SeekBar): Int {
//            try {
//                return (seekBar.progress)
//            } catch (nfe: Resources.NotFoundException) {
//                nfe.printStackTrace()
//                return 0
//            }
//
//        }
//    }
}

class DelegatedBindable<T>(private var value: T,
                           private val observer: BaseObservable) {

    private var bindingTarget: Int = -1

    operator fun getValue(thisRef: Any?, p: KProperty<*>) = value

    operator fun setValue(thisRef: Any?, p: KProperty<*>, v: T) {

        val oldValue = value
        value = v
        if (bindingTarget == -1) {
            bindingTarget = BR::class.java.fields.filter {
                it.name == p.name
            }[0].getInt(null)
        }
        observer.notifyPropertyChanged(bindingTarget)
    }
}


