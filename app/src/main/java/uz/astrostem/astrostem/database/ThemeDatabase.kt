package uz.astrostem.astrostem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.astrostem.astrostem.database.dao.QuestionDao
import uz.astrostem.astrostem.database.dao.TestDao
import uz.astrostem.astrostem.database.dao.ThemeDao
import uz.astrostem.astrostem.database.dao.VariantDao
import uz.astrostem.astrostem.database.entity.Question
import uz.astrostem.astrostem.database.entity.Theme
import uz.astrostem.astrostem.database.entity.Variant

@Database(entities = [Theme::class, Question::class, Variant::class], version = 1)
abstract class ThemeDatabase: RoomDatabase() {
    abstract fun themeDao(): ThemeDao
    abstract fun questionDao(): QuestionDao
    abstract fun variantDao(): VariantDao
    abstract fun testDao(): TestDao

    companion object {
        private var INSTANCE: ThemeDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ThemeDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, ThemeDatabase::class.java, "theme_database")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}