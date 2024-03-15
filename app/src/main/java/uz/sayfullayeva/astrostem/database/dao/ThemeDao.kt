package uz.sayfullayeva.astrostem.database.dao

import androidx.room.*
import uz.sayfullayeva.astrostem.database.entity.Theme

@Dao
interface ThemeDao {

    @Query("select * from theme order by id")
    fun getAllThemes():List<Theme>

    @Insert
    fun addTheme(theme:Theme)

    @Update
    fun updateTheme(theme: Theme)

}