package com.affinityapps.txtr.ui.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.affinityapps.txtr.R

class ForumFragment() : Fragment() {

    private lateinit var forumViewModel: ForumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        forumViewModel = ViewModelProvider(this).get(ForumViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_forum, container, false)
        val textView:TextView = root.findViewById(R.id.forum_test)
        forumViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}