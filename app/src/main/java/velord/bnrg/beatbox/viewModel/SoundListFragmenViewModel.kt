package velord.bnrg.beatbox.viewModel

import androidx.lifecycle.ViewModel
import velord.bnrg.beatbox.model.BeatBox

class SoundListFragmentViewModel : ViewModel() {

    var beatBoxIsInit: Boolean = false
    lateinit var beatBox: BeatBox

    var speedRate = 9

}