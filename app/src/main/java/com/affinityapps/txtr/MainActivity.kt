package com.affinityapps.txtr

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.affinityapps.txtr.databinding.ActivityMainBinding
import com.affinityapps.txtr.ui.home.HomeFragment

private const val PERMISSION_REQUEST = 10

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var permissions = arrayOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.READ_SMS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inputPermission()
        enableNavigation()
    }

    private fun inputPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M
        ) {
            if (checkPermission(permissions)) {
                Toast.makeText(this, "Permission is already provided", Toast.LENGTH_SHORT).show()
            } else {
                requestPermissions(permissions, PERMISSION_REQUEST)
            }
        } else {
            Toast.makeText(this, "Permission is already provided", Toast.LENGTH_SHORT).show()
        }
    }

    private fun enableNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_home,
                R.id.fragment_statistics,
                R.id.fragment_summary,
                R.id.fragment_messages
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
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
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Request Permissions", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (allSuccess) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.add_permissions -> {
                inputPermission()
                true
            }
            R.id.refresh_home -> {
                refreshHomeFragment()
                true
            }
            R.id.light_dark -> {
                darkLightMode()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshHomeFragment() {
        val frg: HomeFragment =
            supportFragmentManager.findFragmentByTag("HomeFragment") as HomeFragment
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.detach(frg)
        ft.attach(frg)
        ft.commit()
    }

    private fun darkLightMode() {
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        recreate()
    }
}