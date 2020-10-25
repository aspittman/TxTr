package com.affinityapps.txtr.ui.home

import android.content.Context
import android.net.Uri
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

        val contactsDataList: MutableList<Contacts> = ArrayList()
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
                val contactsObject = Contacts(name, number)
                contactsDataList.add(contactsObject)
            }
            contacts.close()
        }

        val smsDataList: MutableList<Messages> = ArrayList()
        val messages = activity?.contentResolver?.query(
            Uri.parse("content://sms/"),
            null,
            null,
            null,
            null
        )

        if (messages != null) {
            while (messages.moveToNext()) {
                val date = messages.getString(messages.getColumnIndex("date"))
            //    val time = messages.getString(messages.getColumnIndex("time"))
                val message = messages.getString(messages.getColumnIndex("body"))
                val smsDataObject = Messages(date, "sdfgdf", message)
                smsDataList.add(smsDataObject)
            }
            messages.close()
        }

        viewManager = LinearLayoutManager(activity)
        homeAdapter = HomeAdapter(contactsDataList)

        recyclerView = binding.homeFragmentRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = homeAdapter
        }

        homeAdapter.setOnHomeItemClickListener(object : HomeAdapter.OnHomeItemClickListener {

            override fun onHomeItemClick(position: Int) {
                val smsData: Messages = smsDataList[position]
                dataFragmentTransfer?.dataListInputSent(smsData.date, smsData.time, smsData.message)
            }
        })
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