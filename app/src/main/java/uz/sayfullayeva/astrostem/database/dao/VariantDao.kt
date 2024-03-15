package uz.sayfullayeva.astrostem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import uz.sayfullayeva.astrostem.database.entity.Variant

@Dao
interface VariantDao {

    @Insert
    fun addVariant(variant: Variant)

}