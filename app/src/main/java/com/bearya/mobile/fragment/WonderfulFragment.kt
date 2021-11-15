package com.bearya.mobile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bearya.mobile.data.model.MainViewModel
import com.bearya.mobile.listener.databinding.FragmentWonderfulBinding

class WonderfulFragment : Fragment() {

    private lateinit var bindView: FragmentWonderfulBinding
    private val wonderfulViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindView = FragmentWonderfulBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.viewModel = wonderfulViewModel
    }

}