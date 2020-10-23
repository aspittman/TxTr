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
    private var dataFragmentTransfer: DataFragmentTransfer? = null

    interface DataFragmentTransfer {
        fun dataListInputSent(
            date: String?, time: String?, message: String?
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

        val contactsList: MutableList<ContactsData> = ArrayList()
        val smsDataList: MutableList<ContactsData> = ArrayList()
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
                val contactsObject = ContactsData(name, number)
                contactsList.add(contactsObject)

//                val date = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                val time = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
//                val message = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
//                val smsDataObject = ContactsData(date, time, message)
//                smsDataList.add(smsDataObject)
            }
            contacts.close()
        }

        viewManager = LinearLayoutManager(activity)
        homeAdapter = HomeAdapter(contactsList)

        recyclerView = binding.homeFragmentRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = homeAdapter
        }

//        homeAdapter.setOnHomeItemClickListener(object : HomeAdapter.OnHomeItemClickListener {
//
//            override fun onHomeItemClick(position: Int) {
//                var contacts: ContactsData = contactsList[position]
//                //          dataFragmentTransfer.dataListInputSent() fill in data for other fragments
//            }
//
//        })

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        dataFragmentTransfer = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}