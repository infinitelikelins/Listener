package com.bearya.mobile.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bearya.mobile.data.model.RegisterViewModel
import com.bearya.mobile.listener.R
import com.bearya.mobile.listener.databinding.SheetRegisterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import es.dmoral.toasty.Toasty

class RegisterSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var bindView: SheetRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindView = SheetRegisterBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.privatePolicyText.movementMethod = LinkMovementMethod.getInstance()
        bindView.privatePolicyText.highlightColor = Color.TRANSPARENT
        bindView.registerVerify.setOnClickListener(this)
        bindView.viewModel = registerViewModel
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.register_verify) {
            if (bindView.privatePolicyCheck.isChecked) {

            } else {
                Toasty.warning(requireContext(), "请阅读并同意协议", Toasty.LENGTH_LONG).show()
            }
       }
    }

}