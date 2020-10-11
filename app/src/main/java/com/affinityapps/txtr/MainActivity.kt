package com.affinityapps.txtr

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.READ_SMS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.affinityapps.txtr.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

private const val PERMISSION_REQUEST = 10

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var context: Context
    private var permissions = arrayOf(READ_CONTACTS, READ_SMS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        context = this

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M
        ) {
            if (checkPermission(context, permissions)) {
                Toast.makeText(context, "Permission is already provided", Toast.LENGTH_SHORT).show()
            } else {
                requestPermissions(permissions, PERMISSION_REQUEST)
            }
        } else {
            Toast.makeText(context, "Permission is already provided", Toast.LENGTH_SHORT).show()
        }
        
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_home,
                R.id.fragment_statistics, R.id.fragment_forum
            ), binding.drawerLayout
        )
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun checkPermission(context: Context, permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            var allSuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false
                    val requestAgain = android.os.Build.VERSION.SDK_INT >=
                            android.os.Build.VERSION_CODES.M &&
                            shouldShowRequestPermissionRationale(permissions[i])
                    if (requestAgain) {
                        Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Request Permissions", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (allSuccess) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
