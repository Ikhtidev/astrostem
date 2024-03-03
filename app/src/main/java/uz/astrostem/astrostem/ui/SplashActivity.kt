package uz.astrostem.astrostem.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.astrostem.astrostem.R
import uz.astrostem.astrostem.database.ThemeDatabase
import uz.astrostem.astrostem.database.entity.MyTest
import uz.astrostem.astrostem.database.entity.Question
import uz.astrostem.astrostem.database.entity.Theme
import uz.astrostem.astrostem.database.entity.Variant
import uz.astrostem.astrostem.utils.Constants.Companion.THEME_LIST
import uz.astrostem.astrostem.utils.TYPE
import java.io.IOException

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val themeDatabase: ThemeDatabase by lazy {
        ThemeDatabase.getInstance(this)
    }
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
                addTestsToDb()
            }
            withContext(Dispatchers.Default) {
                createThemeList()
            }
            withContext(Dispatchers.Default) {
                addThemesToDb()
            }
            withContext(Dispatchers.Default) {
                addQuestionsToDb()
            }
            withContext(Dispatchers.Default) {
                startMainActivity()
            }

        }
    }

    private suspend fun addTestsToDb() {
        val tests = ArrayList(themeDatabase.testDao().getAllTests())
        if(tests.isEmpty()){
            themeDatabase.testDao().addTest(MyTest(name = "Test savollari. Sharq astronomiyasi (2)", testsCount = 150))
            themeDatabase.testDao().addTest(MyTest(name = "Test test 1", testsCount = 100))
            themeDatabase.testDao().addTest(MyTest(name = "Test test 2", testsCount = 50))
        } else {
            delay(500)
        }
    }

    private suspend fun addQuestionsToDb() {
        val questions = ArrayList(themeDatabase.questionDao().getAllQuestions())
        if (questions.isEmpty()) {
            for (i in 1..1) {
                writeOneQuestionToDb(i)
            }
        } else {
            delay(2000)
        }
    }

    private fun writeOneQuestionToDb(testId: Int) {

        try {
            val input = assets.open("$testId/$testId.html")
            val document: Document = Jsoup.parse(input, "windows-1252", "http://astrostem.uz/")

            // Change the value of the src attribute of the img tag
            val imgElements = document.select("img")
            for (img in imgElements) {
                var imgPath = img.attr("src")
                imgPath = "file:///android_asset/$testId/$imgPath"
                img.attr("src", imgPath)
            }

            // All tables
            val tables = document.select("table")

            // Each table
            for (table in tables) {
                val rows: Elements = table.select("tr")

                val questionAndVariants: Elements = rows.select("td")
                var questionId: Long = -1
                for (i in 0 until questionAndVariants.size) {

                    if (i == 0) {
                        themeDatabase.questionDao().addQuestion(
                            Question(
                                title = "<b>${questionAndVariants[i]}</b",
                                testId = (testId - 1)
                            )
                        ).also { questionId = it }
                    } else {
                        themeDatabase.variantDao().addVariant(
                            if (i == 1) {
                                Variant(
                                    title = questionAndVariants[i].toString(),
                                    isTrue = true,
                                    questionOwnerId = questionId
                                )
                            } else {
                                Variant(
                                title = questionAndVariants[i].toString(),
                                isTrue = false,
                                questionOwnerId = questionId
                            )
                            }
                        )
                    }
                }

            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun addThemesToDb() {
        val themes = ArrayList(themeDatabase.themeDao().getAllThemes())
        if (themes.isEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                THEME_LIST.forEach { theme ->
                    themeDatabase.themeDao().addTheme(theme)
                }
            }
        }
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

    private fun startMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}