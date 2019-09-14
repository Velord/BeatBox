package velord.bnrg.beatbox.view

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import velord.bnrg.beatbox.R
import velord.bnrg.beatbox.databinding.ActivityMainBinding
import velord.bnrg.beatbox.databinding.ListItemSoundBinding
import velord.bnrg.beatbox.model.BeatBox
import velord.bnrg.beatbox.model.Sound
import velord.bnrg.beatbox.viewModel.SoundViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBeatBox()

        initAllViews()
    }

    private fun initAllViews() {
        (DataBindingUtil.setContentView(this, R.layout.activity_main)
                as ActivityMainBinding).apply {
            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = SoundAdapter(beatBox.sounds)
            }
        }
    }

    private fun initBeatBox() {
        beatBox = BeatBox(assets)
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding):
            RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = SoundViewModel()
        }

        fun bind(sound: Sound) = binding.apply {
            viewModel?.sound = sound
            executePendingBindings()
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>):
            RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            ).apply {
                lifecycleOwner = this@MainActivity
            }
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

        override fun getItemCount(): Int = sounds.size
    }
}
