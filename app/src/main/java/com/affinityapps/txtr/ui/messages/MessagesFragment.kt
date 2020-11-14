package com.affinityapps.txtr.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.FragmentMessagesBinding
import com.affinityapps.txtr.ui.home.Messages


class MessagesFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var messagesAdapter: MessagesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val messagesList: MutableList<Messages> = ArrayList()
        messagesList.add(Messages("asobjdgksjd", "aijdbfhijadbsjf", "lskdfngldskfng"))
        messagesList.add(Messages("aodasdf", "aijdbfhijadbsjf", "kajbfjkdsf"))
        messagesList.add(Messages("aodjfdaas", "aijdbfhijadbsjf", "kajbfjkdsf"))

        viewManager = LinearLayoutManager(activity)
        messagesAdapter = MessagesAdapter(messagesList)

        recyclerView = binding.messagesFragmentRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = messagesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}