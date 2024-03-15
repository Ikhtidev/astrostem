package uz.sayfullayeva.astrostem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.sayfullayeva.astrostem.database.entity.Variant
import uz.sayfullayeva.astrostem.databinding.ItemVariantBinding
import kotlin.properties.Delegates

class TestVariantAdapter(
    private val items: List<Variant>,
    private val clickListener: (variant: Variant) -> Unit
) : RecyclerView.Adapter<TestVariantAdapter.ViewHolder>() {

    private var selectedPosition by Delegates.observable(-1) { property, oldPos, newPos ->
        if (newPos in items.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    inner class ViewHolder(private val binding: ItemVariantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(variant: Variant, selected: Boolean) {
            binding.tvVariant.text = variant.title
            itemView.isSelected = selected
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemVariantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val variant = items[position]
        holder.onBind(variant, position == selectedPosition)
        holder.itemView.setOnClickListener {
            selectedPosition = position
            clickListener.invoke(variant)
        }
    }
}