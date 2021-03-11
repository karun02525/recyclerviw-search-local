package io.chingari

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_display.view.*


class UserAdapter(var list: List<UserModel> = listOf()) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var listener: MainActivity.OnClickListener? = null

    fun setListener(onClickListener: MainActivity.OnClickListener) {
        this.listener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_display,
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    fun filterList(filteredNames: ArrayList<UserModel>) {
        this.list = filteredNames
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindItems(model: UserModel) {
            itemView.run {
                textView.text=model.name
                //listener?.onClickEvent(model)
            }
        }
    }
}