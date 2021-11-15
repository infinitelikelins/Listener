package com.bearya.mobile.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bearya.mobile.data.model.MainViewModel
import com.bearya.mobile.data.repository.UserRepository
import com.bearya.mobile.listener.R
import com.bearya.mobile.listener.databinding.FragmentMineBinding
import com.kongzue.dialogx.dialogs.MessageDialog
import com.kongzue.dialogx.util.TextInfo

class MineFragment : Fragment(), View.OnClickListener {

    private lateinit var bindView: FragmentMineBinding
    private val userViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentMineBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.userViewModel = userViewModel
        bindView.userNick.setOnClickListener(this)
        bindView.userAvatar.setOnClickListener(this)

        bindView.settings.setOnClickListener(this)
        bindView.userVip.setOnClickListener(this)
        bindView.useRecord.setOnClickListener(this)
        bindView.parentChild.setOnClickListener(this)
        bindView.devicesManager.setOnClickListener(this)
        bindView.knownFlag.setOnClickListener(this)
        bindView.questionHelp.setOnClickListener(this)
        bindView.techVoice.setOnClickListener(this)
        bindView.winEmblem.setOnClickListener(this)
        bindView.myCollected.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.user_nick, R.id.user_avatar -> {
                if (UserRepository.isUnsigned) {
                    MessageDialog("登录 | 注册", "在登录|注册前,你需先阅读并同意三项协议", "已有账号，去登录", "忘记密码，去修改", "首次使用，去注册")
                        .apply {
                            buttonOrientation = LinearLayout.VERTICAL
                            titleTextInfo = TextInfo().setBold(true).setFontSize(24).setFontColor(ContextCompat.getColor(requireContext(), R.color.text_color_title))
                            messageTextInfo = TextInfo().setBold(false).setFontSize(16)
                            okTextInfo = TextInfo().setBold(true).setFontColor(Color.WHITE)
                            cancelTextInfo = TextInfo().setBold(true).setFontColor(ContextCompat.getColor(requireContext(), R.color.text_color_headline))
                            otherTextInfo = TextInfo().setBold(true).setFontColor(ContextCompat.getColor(requireContext(), R.color.text_color_headline))
                            setOkButtonClickListener { _, _ ->
                                LoginSheetFragment().show(parentFragmentManager, "login").let { false }
                            }
                            setOtherButtonClickListener { _, _ ->
                                RegisterSheetFragment().show(childFragmentManager, "register").let { false }
                            }
                            setCancelButtonClickListener { _, _ ->
                                ForgetSheetFragment().show(childFragmentManager, "forget").let { false }
                            }
                        }.show()
                }
            }
        }
    }

}