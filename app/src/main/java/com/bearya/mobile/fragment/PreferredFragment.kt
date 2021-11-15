package com.bearya.mobile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bearya.mobile.data.model.MainViewModel
import com.bearya.mobile.listener.databinding.FragmentPreferredBinding
import com.bearya.mobile.widget.cardpage.CardPageSwitcher
import es.dmoral.toasty.Toasty

class PreferredFragment : Fragment() {

    private lateinit var bindView: FragmentPreferredBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var switcher: CardPageSwitcher? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindView = FragmentPreferredBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        switcher = CardPageSwitcher(
            bindView.families, bindView.teacherNameSwitcher,
            bindView.teacherWorksSwitcher,
            bindView.teacherIntroduceSwitcher, bindView.teacherAvatar
        ) {
            Toasty.info(requireContext(), "${it?.name}", Toasty.LENGTH_LONG).show()
        }
        bindView.viewModel = viewModel
        viewModel.teachers.observe(viewLifecycleOwner) {
            switcher?.submit(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        switcher?.unbind()
        switcher = null
    }

}