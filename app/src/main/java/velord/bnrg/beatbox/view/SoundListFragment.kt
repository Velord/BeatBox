package velord.bnrg.beatbox.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import velord.bnrg.beatbox.R
import velord.bnrg.beatbox.databinding.ListItemSoundBinding
import velord.bnrg.beatbox.databinding.SoundListFragmentBinding
import velord.bnrg.beatbox.model.BeatBox
import velord.bnrg.beatbox.model.Sound
import velord.bnrg.beatbox.viewModel.SoundListFragmentViewModel
import velord.bnrg.beatbox.viewModel.SoundViewModel
import velord.bnrg.beatbox.viewModel.SpeedRateViewModel

private const val TAG = "SoundFragmentList"

class SoundListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var seekBarSpeed: TextView
    private lateinit var seekBar: SeekBar

    private val soundListViewModel: SoundListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(SoundListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = (DataBindingUtil.inflate(inflater,
            R.layout.sound_list_fragment,
            container,
            false) as SoundListFragmentBinding).apply {
            initBeatBox()
            viewModel = SpeedRateViewModel(
                soundListViewModel.beatBox,
                soundListViewModel.speedRate)
            lifecycleOwner = activity
        }

        return (binding.root).also {
            initAllViews(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundListViewModel.speedRate = seekBar.progress
    }

    private fun initAllViews(view: View) {
        recyclerView =
            (view.findViewById(R.id.sound_recycler_view) as RecyclerView).apply {
                layoutManager = GridLayoutManager(this@SoundListFragment.activity, 3)
                adapter = SoundAdapter(soundListViewModel.beatBox.sounds)
            }
        seekBarSpeed = view.findViewById(R.id.seek_bar_speed)
        seekBar = view.findViewById(R.id.seekBar)
    }

    private fun initBeatBox() {
        soundListViewModel.apply {
            if (!this.beatBoxIsInit) {
                this.beatBoxIsInit = true
                this.beatBox = BeatBox(activity!!.assets)
            }
        }
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = SoundViewModel(soundListViewModel.beatBox)
        }

        fun bind(sound: Sound) = binding.apply {
            viewModel?.sound = sound
            executePendingBindings()
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            ).apply {
                // if using LiveData  case
                lifecycleOwner = activity
            }
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            sounds[position].apply {
                holder.bind(this)
            }
        }

        override fun getItemCount(): Int = sounds.size
    }

    companion object {
        fun newInstance(): SoundListFragment = SoundListFragment()
    }
}
