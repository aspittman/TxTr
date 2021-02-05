package com.affinityapps.txtr.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affinityapps.txtr.databinding.FragmentHomeBinding
import com.affinityapps.txtr.ui.graphs.HomeGraphViewModel
import com.affinityapps.txtr.ui.messages.HomeMessagesViewModel
import com.affinityapps.txtr.ui.summary.HomeSummaryViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactDataPermissionRationale()
        smsDataPermissionRationale()
    }

    private fun contactDataPermissionRationale() {
        val requestPermissionLauncher =
            activity?.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    initiateContactsList()
                } else {
                    Toast.makeText(activity, "Contacts is now not going to work", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        when {
            checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) ==
                    PackageManager.PERMISSION_GRANTED -> {
                initiateContactsList()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                Toast.makeText(activity, "You need this contact permission", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                requestPermissionLauncher?.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }

    private fun smsDataPermissionRationale() {
        val requestPermissionLauncher =
            activity?.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    initiateSMSDataDisplay()
                } else {
                    Toast.makeText(activity, "SMS is now not going to work", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        when {
            checkSelfPermission(requireActivity(), Manifest.permission.READ_SMS) ==
                    PackageManager.PERMISSION_GRANTED -> {
                initiateSMSDataDisplay()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS) -> {
                Toast.makeText(activity, "You need this SMS permission", Toast.LENGTH_SHORT).show()
            }
            else -> {
                requestPermissionLauncher?.launch(Manifest.permission.READ_SMS)
            }
        }
    }

    private fun initiateContactsList() {
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


    private fun initiateSMSDataDisplay() {
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


                val smsDataObject = Messages(time, message, "aslkdjnflksadn")
                smsDataList.add(smsDataObject)
            }
            messages.close()
        }

        graphViewModel = ViewModelProvider(requireActivity()).get(HomeGraphViewModel::class.java)
        summaryViewModel =
            ViewModelProvider(requireActivity()).get(HomeSummaryViewModel::class.java)
        messagesViewModel =
            ViewModelProvider(requireActivity()).get(HomeMessagesViewModel::class.java)
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
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}