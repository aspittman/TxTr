package com.affinityapps.txtr.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.affinityapps.txtr.databinding.FragmentSummaryBinding


class SummaryFragment() : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(requireActivity()).get(HomeSummaryViewModel::class.java)
        model.message.observe(viewLifecycleOwner, {
    //        binding.summaryText.text = it
        })

        model.time.observe(viewLifecycleOwner, {
    //        binding.summaryTime.text = it
        })

        model.date.observe(viewLifecycleOwner, {
    //        binding.summaryDate.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}