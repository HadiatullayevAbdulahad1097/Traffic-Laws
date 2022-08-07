package developer.abdulahad.homework25.adapater

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulahad.homework25.DB.MyDbHelper
import developer.abdulahad.homework25.Models.MyData
import developer.abdulahad.homework25.Models.Traffics
import developer.abdulahad.homework25.R
import developer.abdulahad.homework25.databinding.ItemRvHomeBinding

class RvHomeAdapter(var list: List<Traffics>,var context: Context, var clickItem: ClickItem) :
    RecyclerView.Adapter<RvHomeAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvHomeBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(traffics: Traffics, position: Int) {
            var myDbHelper = MyDbHelper(context)
            itemRv.tvNomi.text = traffics.name
            itemRv.imageItem.setImageURI(Uri.parse(traffics.image))

            if (traffics.like == 0) {
                itemRv.liked.setImageResource(R.drawable.like_selected)
            }else{
                itemRv.liked.setImageResource(R.drawable.like_unselected)
            }

            itemRv.liked.setOnClickListener {
                if (traffics.like == 0) {
                    itemRv.liked.setImageResource(R.drawable.like_selected)
                    traffics.like = 1
                    myDbHelper.updateTraffics(traffics)
                }else{
                    itemRv.liked.setImageResource(R.drawable.like_unselected)
                    traffics.like = 0
                    myDbHelper.updateTraffics(traffics)
                }
            }
            itemRv.card.setOnClickListener {
                clickItem.itemClick(traffics, position)
            }
            itemRv.delete.setOnClickListener {
                clickItem.deleteClick(traffics, position)
            }
            itemRv.edit.setOnClickListener {
                clickItem.editClick(traffics, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface ClickItem {
        fun itemClick(traffics: Traffics, position: Int)
        fun deleteClick(traffics: Traffics, position: Int)
        fun editClick(traffics: Traffics, position: Int)
    }
}