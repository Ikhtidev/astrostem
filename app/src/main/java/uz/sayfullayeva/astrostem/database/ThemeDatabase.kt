package uz.sayfullayeva.astrostem.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.sayfullayeva.astrostem.MyApp
import uz.sayfullayeva.astrostem.database.dao.QuestionDao
import uz.sayfullayeva.astrostem.database.dao.TestDao
import uz.sayfullayeva.astrostem.database.dao.ThemeDao
import uz.sayfullayeva.astrostem.database.dao.VariantDao
import uz.sayfullayeva.astrostem.database.entity.MyTest
import uz.sayfullayeva.astrostem.database.entity.Question
import uz.sayfullayeva.astrostem.database.entity.Theme
import uz.sayfullayeva.astrostem.database.entity.Variant


@Database(entities = [Theme::class, Question::class, Variant::class, MyTest::class], version = 1)
abstract class ThemeDatabase : RoomDatabase() {
    abstract fun themeDao(): ThemeDao
    abstract fun questionDao(): QuestionDao
    abstract fun variantDao(): VariantDao
    abstract fun testDao(): TestDao

    companion object {
        private var INSTANCE: ThemeDatabase? = null

        @Synchronized
        fun getInstance(): ThemeDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    MyApp.getContext(),
                    ThemeDatabase::class.java,
                    "theme_database"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}