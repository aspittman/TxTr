package com.affinityapps.txtr.ui.home

import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.FragmentHomeBinding
import com.affinityapps.txtr.ui.graphs.HomeGraphViewModel
import com.affinityapps.txtr.ui.messages.HomeMessagesViewModel
import com.affinityapps.txtr.ui.summary.HomeSummaryViewModel
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var graphViewModel: HomeGraphViewModel
    private lateinit var messagesViewModel: HomeMessagesViewModel
    private lateinit var summaryViewModel: HomeSummaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateRecyclerViewContacts()
        sendContactsSMSData()
    }

    private fun populateRecyclerViewContacts() {
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

        viewManager = LinearLayoutManager(activity)
        homeAdapter = HomeAdapter(contactsDataList)

        recyclerView = binding.homeFragmentRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = homeAdapter
        }
    }

    private fun sendContactsSMSData() {
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
                val message = messages.getString(messages.getColumnIndex("body"))
                val time = messages.getString(messages.getColumnIndex("date"))
//                val date = LocalDate.parse(time, DateTimeFormatter.ISO_DATE)
//                val format = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
//                val trueTime = format.format(date)


                val smsDataObject = Messages(time, message, "Kristian is GAP")
                smsDataList.add(smsDataObject)
            }
            messages.close()
        }

        graphViewModel = ViewModelProvider(requireActivity()).get(HomeGraphViewModel::class.java)
        summaryViewModel = ViewModelProvider(requireActivity()).get(HomeSummaryViewModel::class.java)
        messagesViewModel = ViewModelProvider(requireActivity()).get(HomeMessagesViewModel::class.java)
        homeAdapter.setOnHomeItemClickListener(object : HomeAdapter.OnHomeItemClickListener {

            override fun onHomeItemClick(position: Int) {
                val messageLists = smsDataList[position]

                graphViewModel.messageTextAmount(messageLists.message)
                graphViewModel.messageTime(messageLists.time)
                graphViewModel.messageDate(messageLists.date)

                summaryViewModel.messageTextAmount(messageLists.message)
                summaryViewModel.messageTime(messageLists.time)
                summaryViewModel.messageDate(messageLists.date)

                messagesViewModel.messageData(messageLists)
                Log.d("HomeClick", "Clicked and on position $position")
                Log.i("ListTester", "Arraylist data is $messageLists")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}