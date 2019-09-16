package velord.bnrg.beatbox.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import velord.bnrg.beatbox.R
import velord.bnrg.beatbox.utils.initFragment

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val sf = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSoundListFragment()
    }

    private fun initSoundListFragment() {
        initFragment(sf, SoundListFragment(),  R.id.fragment_container)
    }
}
