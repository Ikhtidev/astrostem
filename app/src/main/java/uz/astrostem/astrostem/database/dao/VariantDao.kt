package uz.astrostem.astrostem.database.dao

import androidx.room.*
import uz.astrostem.astrostem.database.entity.Variant

@Dao
interface VariantDao {

    @Insert
    fun addVariant(variant: Variant)

}