package com.bearya.mobile.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bearya.mobile.data.model.LoginViewModel
import com.bearya.mobile.data.model.MainViewModel
import com.bearya.mobile.listener.R
import com.bearya.mobile.listener.databinding.SheetLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import es.dmoral.toasty.Toasty

class LoginSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var bindView: SheetLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val userViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = SheetLoginBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.privatePolicyText.movementMethod = LinkMovementMethod.getInstance()
        bindView.privatePolicyText.highlightColor = Color.TRANSPARENT
        bindView.loginVerify.setOnClickListener(this)
        bindView.viewModel = loginViewModel
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.login_verify -> {
                if (bindView.privatePolicyCheck.isChecked) {
                    loginViewModel.login {
                        userViewModel.invalidate()
                        dismiss()
                    }
                } else {
                    Toasty.warning(requireContext(), "请阅读并同意协议", Toasty.LENGTH_LONG).show()
                }
            }
        }
    }

}