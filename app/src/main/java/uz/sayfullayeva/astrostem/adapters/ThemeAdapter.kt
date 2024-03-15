package uz.sayfullayeva.astrostem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.sayfullayeva.astrostem.database.entity.Theme
import uz.sayfullayeva.astrostem.databinding.ItemThemeBinding

class ThemeAdapter(private val items: List<Theme>) :
    RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    private var listener:ThemeClickListener?=null

    fun setListener(listener:ThemeClickListener){
        this.listener = listener
    }
    inner class ViewHolder(val binding: ItemThemeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemThemeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.themeName.text = items[position].name
        holder.itemView.setOnClickListener {
            listener?.onThemeClick(position)
        }
    }

    override fun getItemCount(): Int = items.size

    interface ThemeClickListener{
        fun onThemeClick(position:Int)
    }
}