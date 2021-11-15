package com.bearya.mobile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bearya.mobile.data.model.ForgetViewModel
import com.bearya.mobile.listener.R
import com.bearya.mobile.listener.databinding.SheetForgetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ForgetSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var bindView: SheetForgetBinding
    private val viewModel: ForgetViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindView = DataBindingUtil.inflate(inflater, R.layout.sheet_forget, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.viewModel = viewModel
    }

    override fun onClick(view: View?) {

    }

}