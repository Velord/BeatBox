package velord.bnrg.beatbox.viewModel

import io.kotlintest.Spec
import io.kotlintest.specs.StringSpec
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import velord.bnrg.beatbox.model.BeatBox
import velord.bnrg.beatbox.model.Sound

class SoundViewModelTest: StringSpec() {

    private lateinit var beatBox: BeatBox
    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)

        beatBox =  mock(BeatBox::class.java)
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    init {
        "exposes sound name as title" {
            subject.title == sound.name
        }

        "call BeatBox.playOnButtonClicked" {
            subject.onButtonClicked()
            verify(beatBox).play(sound)
        }
    }
}

class SoundViewModelTestJUnit {

    private lateinit var beatBox: BeatBox
    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        beatBox =  mock(BeatBox::class.java)
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle() {
        assertThat(subject.title, `is`(sound.name))
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        subject.onButtonClicked()
    }
}