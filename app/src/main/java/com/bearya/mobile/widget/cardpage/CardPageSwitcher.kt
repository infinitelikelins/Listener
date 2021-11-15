package com.bearya.mobile.widget.cardpage

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import coil.transform.CircleCropTransformation
import com.bearya.mobile.adapter.TeacherPagerAdapter
import com.bearya.mobile.data.entity.Teachers
import com.bearya.mobile.ext.assetsPath

class CardPageSwitcher(
    private val viewPager2: ViewPager2,
    private val nameSwitcher: AppCompatTextView,
    private val groupNameSwitcher: AppCompatTextView,
    private val introduceSwitcher: AppCompatTextView,
    private val avatarSwitcher: AppCompatImageView,
    private val onClickListener: ((Teachers?) -> Unit)? = null
) : View.OnClickListener {

    private var teacher: Teachers? = null
    private var teachers: MutableList<Teachers>? = null
    private val pagerAdapter: TeacherPagerAdapter by lazy {
        TeacherPagerAdapter().apply {
            setOnItemClickListener { _, _, _ ->
                onClickListener?.invoke(teacher)
            }
        }
    }
    private var pageChangeCallback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                changeSwitcher(position)
            }
        }

    init {
        viewPager2.adapter = pagerAdapter
        CardPageTransformer().with(viewPager2)
        viewPager2.registerOnPageChangeCallback(pageChangeCallback)
        nameSwitcher.setOnClickListener(this)
        groupNameSwitcher.setOnClickListener(this)
        introduceSwitcher.setOnClickListener(this)
        avatarSwitcher.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        onClickListener?.invoke(teacher)
    }

    private fun changeSwitcher(index: Int) {
        teacher = teachers?.get(index)?.apply {
            nameSwitcher.text = name
            groupNameSwitcher.text = group_name
            introduceSwitcher.text = subtitle
            avatarSwitcher.load(cover_path.assetsPath()) {
                transformations(CircleCropTransformation())
            }
        }
    }

    fun submit(objects: MutableList<Teachers>?) {
        teachers = objects
        pagerAdapter.setDiffNewData(teachers)
    }

    fun unbind() {
        viewPager2.unregisterOnPageChangeCallback(pageChangeCallback)
    }

}