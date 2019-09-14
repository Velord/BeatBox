package velord.bnrg.beatbox.viewModel

import androidx.lifecycle.MutableLiveData
import velord.bnrg.beatbox.model.Sound

class SoundViewModel {

    val title: MutableLiveData<String?> = MutableLiveData()

    var sound: Sound? = null
        set(sound) {
            field = sound
            title.postValue(sound?.name)
        }
}