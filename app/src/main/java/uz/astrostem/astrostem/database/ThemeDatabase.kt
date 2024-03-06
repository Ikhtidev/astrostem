package uz.astrostem.astrostem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import uz.astrostem.astrostem.database.dao.QuestionDao
import uz.astrostem.astrostem.database.dao.TestDao
import uz.astrostem.astrostem.database.dao.ThemeDao
import uz.astrostem.astrostem.database.dao.VariantDao
import uz.astrostem.astrostem.database.entity.MyTest
import uz.astrostem.astrostem.database.entity.Question
import uz.astrostem.astrostem.database.entity.Theme
import uz.astrostem.astrostem.database.entity.Variant

@Database(entities = [Theme::class, Question::class, Variant::class, MyTest::class], version = 1)
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
//                    .addCallback(object : Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            db.execSQL("PRAGMA encoding='windows-1252';")
//                        }
//                    })
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}