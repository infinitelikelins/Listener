package com.bearya.mobile.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bearya.mobile.data.entity.Teachers
import com.bearya.mobile.ext.assetsPath
import com.bearya.mobile.listener.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class TeacherPagerAdapter : BaseQuickAdapter<Teachers, BaseViewHolder>(R.layout.item_teacher_pager) {

    init {
        setDiffCallback(object : DiffUtil.ItemCallback<Teachers>() {
            override fun areItemsTheSame(oldItem: Teachers, newItem: Teachers): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Teachers, newItem: Teachers): Boolean = oldItem.name == newItem.name
        })
    }

    override fun convert(holder: BaseViewHolder, item: Teachers) {
        holder.getViewOrNull<AppCompatImageView>(R.id.group_cover)?.load(item.group_cover_path.assetsPath()) {
           transformations(RoundedCornersTransformation(32f,32f,32f,32f))
        }
    }

}