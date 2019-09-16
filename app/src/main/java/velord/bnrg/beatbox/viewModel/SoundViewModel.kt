package velord.bnrg.beatbox.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import velord.bnrg.beatbox.model.BeatBox
import velord.bnrg.beatbox.model.Sound

class SoundViewModel(private val beatBox: BeatBox) : BaseObservable() {

    @get:Bindable
    val title: String?
        get() = sound?.name

    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }

    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }
}