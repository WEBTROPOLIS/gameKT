package com.example.kotlingame.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlingame.R
import com.example.kotlingame.databinding.ListItemDataBinding
import com.example.kotlingame.model.Datos

class DataListAdapter(private var dataList: MutableList<Datos>) :
    RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteButtonClick(position: Int)
    }

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }



    fun deleteItem(position: Int){
        if (position in 0 until dataList.size){
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,dataList.size)
        }
    }

    class DataViewHolder(private val binding: ListItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Datos, isSelected: Boolean,listener:OnItemClickListener?) {
            binding.tvNombreJuego.text = "Nombre del juego: ${data.nameGame}"
            binding.tvNombreReal.text = "Nombre real: ${data.nameReal}"
            binding.tvEdad.text = "Edad: ${data.age}"




            if (isSelected) {
                binding.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.cardviewBlue
                    )
                )
            } else {
                binding.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.cardviewBack
                    )
                )
            }
            binding.btnDelete.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onDeleteButtonClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemDataBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem, position == selectedItemPosition,listener)

        holder.itemView.setOnClickListener {
            val adapterPosition = holder.absoluteAdapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                selectedItemPosition = adapterPosition
                notifyDataSetChanged()
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newDataList: List<Datos>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): Datos? {
        if (position in 0 until dataList.size) {
            return dataList[position]
        }
        return null
    }
}
