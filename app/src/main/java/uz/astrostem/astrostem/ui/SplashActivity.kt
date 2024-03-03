package uz.astrostem.astrostem.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.astrostem.astrostem.R
import uz.astrostem.astrostem.database.entity.Theme
import uz.astrostem.astrostem.utils.Constants.Companion.THEME_LIST
import uz.astrostem.astrostem.utils.TYPE

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViews()

    }

    private fun initViews() {

        val lottieAnimationView: LottieAnimationView = findViewById(R.id.imageLoading)
        lottieAnimationView.setAnimation(R.raw.loading1)

        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Default) {
                createThemeList()
            }
            withContext(Dispatchers.Default) {
                addThemesToDb()
            }
            withContext(Dispatchers.Default) {
                startMainActivity()
            }

        }
    }

    private fun addThemesToDb() {

    }

    private fun createThemeList() {
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_1), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_2), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_3), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_4), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_5), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_6), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_7), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_8), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_9), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_10), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_11), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_12), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_13), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_14), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_15), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_16), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_17), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_18), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_19), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_20), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_21), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_22), type = TYPE.T))
        THEME_LIST.add(Theme(name = getString(R.string.theme_n_23), type = TYPE.T))

        THEME_LIST.add(Theme(name = getString(R.string.theme_a_1), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_2), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_3), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_4), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_5), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_6), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_7), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_8), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_9), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_10), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_11), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_12), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_13), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_14), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_15), type = TYPE.P))
        THEME_LIST.add(Theme(name = getString(R.string.theme_a_16), type = TYPE.P))

        THEME_LIST.add(Theme(name = getString(R.string.theme_l_1), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_2), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_3), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_4), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_5), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_6), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_7), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_8), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_9), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_10), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_11), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_12), type = TYPE.L))
        THEME_LIST.add(Theme(name = getString(R.string.theme_l_13), type = TYPE.L))
    }

    private suspend fun startMainActivity() {
        delay(timeMillis = 2000)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}