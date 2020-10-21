package com.affinityapps.txtr.ui.home

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var dataFragmentTransfer: DataFragmentTransfer

    interface DataFragmentTransfer {
        fun dataListInputSent(
            id: String?, documentTitle: String?, organization: String?, project: String?,
            date: String?, hours: String?, miles: String?, purchases: String?
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DataFragmentTransfer) {
            dataFragmentTransfer = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val contactsList: MutableList<Contacts> = ArrayList()
        val smsDataList: MutableList<Contacts> = ArrayList()
        val contacts = activity?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        if (contacts != null) {
            while (contacts.moveToNext()) {
                val name =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val contactsObject = Contacts()
                contactsObject.name = name
                contactsObject.number = number

                contactsList.add(contactsObject)
            }
            contacts.close()
        }

        recyclerView = binding.homeFragmentRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = homeAdapter
        }

        viewManager = LinearLayoutManager(activity)
        homeAdapter = HomeAdapter(contactsList)
        homeAdapter.setOnHomeItemClickListener(object : HomeAdapter.OnHomeItemClickListener {

            override fun onHomeItemClick(position: Int) {
                var contacts: Contacts = contactsList[position]
                //          dataFragmentTransfer.dataListInputSent() fill in data for other fragments
            }

        })

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        dataFragmentTransfer = null!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}