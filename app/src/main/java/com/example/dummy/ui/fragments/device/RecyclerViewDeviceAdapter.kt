package com.example.dummy.ui.fragments.device

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dummy.databinding.RvItemDevicesBinding
import com.example.dummy.domain.models.DeviceItemModel

class RecyclerViewDeviceAdapter : RecyclerView.Adapter<RecyclerViewDeviceAdapter.DeviceViewHolder>() {

    private val userList = mutableListOf<DeviceItemModel>()


    fun addData(newList: List<DeviceItemModel>) {
        val diffCallback = UserDiffCallback(userList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        userList.clear()
        userList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }



    inner class DeviceViewHolder(private val binding: RvItemDevicesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DeviceItemModel) {
            with(binding){
                tvProductName.text = data.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(RvItemDevicesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val data = userList[position]
        holder.bind(data)

    }
    override fun getItemCount(): Int = userList.size

}
class UserDiffCallback(
    private val oldList: List<DeviceItemModel>,
    private val newList: List<DeviceItemModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}