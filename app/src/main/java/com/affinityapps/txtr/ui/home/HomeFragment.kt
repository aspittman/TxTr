package com.affinityapps.txtr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.FragmentHomeBinding

class HomeFragment() : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var homeFragmentArrayList: ArrayList<Contact>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeFragmentArrayList = ArrayList()
        homeFragmentArrayList.add(Contact("Test", "Test", 1))
        homeFragmentArrayList.add(Contact("Test", "Test", 2))
        homeFragmentArrayList.add(Contact("Test", "Test", 3))

        viewManager = LinearLayoutManager(activity)
        homeAdapter = HomeAdapter(homeFragmentArrayList)

        recyclerView = binding.homeFragmentRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = homeAdapter
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}