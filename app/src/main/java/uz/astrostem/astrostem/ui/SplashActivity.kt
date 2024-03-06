package uz.astrostem.astrostem.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import uz.astrostem.astrostem.R
import uz.astrostem.astrostem.database.ThemeDatabase
import uz.astrostem.astrostem.database.entity.MyTest
import uz.astrostem.astrostem.database.entity.Question
import uz.astrostem.astrostem.database.entity.Theme
import uz.astrostem.astrostem.database.entity.Variant
import uz.astrostem.astrostem.utils.Constants.Companion.THEME_LIST
import uz.astrostem.astrostem.utils.TYPE
import java.io.InputStream

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val themeDatabase: ThemeDatabase by lazy {
        ThemeDatabase.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViews()

    }

    private fun initViews() {

        val lottieAnimationView: LottieAnimationView = findViewById(R.id.imageLoading)
        lottieAnimationView.setAnimation(R.raw.loading)

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
        if (tests.isEmpty()) {
            themeDatabase.testDao()
                .addTest(MyTest(name = "Test savollari. Sharq astronomiyasi (2)", testsCount = 150))
            themeDatabase.testDao().addTest(MyTest(name = "Test test 2", testsCount = 100))
            themeDatabase.testDao().addTest(MyTest(name = "Test test 3", testsCount = 50))
            themeDatabase.testDao().addTest(MyTest(name = "Test test 4", testsCount = 200))
        } else {
            delay(500)
        }
    }

    private suspend fun addQuestionsToDb() {
        val questions = ArrayList(themeDatabase.questionDao().getAllQuestions())
        if (questions.isEmpty()) {
            val context: Context = applicationContext
            val assetManager = context.assets
            val myInput: InputStream = assetManager.open("questions.xls")
            val myFileSystem = POIFSFileSystem(myInput)
            val myWorkBook = HSSFWorkbook(myFileSystem)
            for (i in 0..0) {
                writeOneTestToDb(myWorkBook, i)
            }
        } else {
            delay(2000)
        }
    }

    private fun writeOneTestToDb(myWorkBook: HSSFWorkbook, testId: Int) {
        try {
            val mySheet: HSSFSheet = myWorkBook.getSheetAt(testId)
            val rows: Iterator<Row> = mySheet.rowIterator()
            var questionId: Long = -1
            while (rows.hasNext()) {
                val row: HSSFRow = rows.next() as HSSFRow
                val cells: Iterator<Cell> = row.cellIterator()
                var cellNumber = 0
                while (cells.hasNext()) {
                    val cell: HSSFCell = cells.next() as HSSFCell
                    if (cellNumber == 0) {
                        themeDatabase.questionDao().addQuestion(
                            Question(
                                title = cell.toString(),
                                testId = (testId)
                            )
                        ).also { questionId = it }
                    } else {
                        themeDatabase.variantDao().addVariant(
                            if (cellNumber == 1) {
                                Variant(
                                    title = cell.toString(),
                                    isTrue = true,
                                    questionOwnerId = questionId
                                )
                            } else {
                                Variant(
                                    title = cell.toString(),
                                    isTrue = false,
                                    questionOwnerId = questionId
                                )
                            }
                        )
                    }
                    cellNumber++
                }
            }
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                getString(R.string.error_loading_data),
                Toast.LENGTH_SHORT
            ).show()
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