package com.thoughtworks.helloandroidview.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.thoughtworks.helloandroidview.R


class MainActivity : AppCompatActivity() {
    private val REQUEST_READ_CONTACTS_PERMISSION = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        generateButtons()
        requestContactsPermission()
    }


    private fun generateButtons() {
        val layout = findViewById<LinearLayout>(R.id.linear_layout)

        for (number in 1..20) {
            val button = Button(this)
            button.text = "${getString(R.string.button_name)} $number"
            val layoutParams = ViewGroup.MarginLayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 16, 0, 0)
            button.layoutParams = layoutParams

            if (number == 1) {
                button.text = getString(R.string.constrain_layout_button_name)
                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, ConstraintActivity::class.java)
                    startActivity(intent)
                }
            }
            if (number == 2) {
                button.text = getString(R.string.login_button_name)
                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }

            if (number == 3) {
                button.text = getString(R.string.pick_contact_button_name)
                button.setOnClickListener {
                    if (hasContactsPermission()) {
                        val intent =
                            Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                        pickContact.launch(intent)
                    }
                }
            }

            if (number == 4) {
                button.text = getString(R.string.fragment_button_name)
                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, LanguageSelectionActivity::class.java)
                    startActivity(intent)
                }
            }

            if (number == 5) {
                button.text = getString(R.string.recycler_view_button_name)
                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, TweetsActivity::class.java)
                    startActivity(intent)
                }
            }

            if (number == 6) {
                button.text = getString(R.string.thread_button_name)
                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, ThreadActivity::class.java)
                    startActivity(intent)
                }
            }

            if (number == 7) {
                button.text = getString(R.string.shared_preference_button_name)
                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, SharedPreferenceActivity::class.java)
                    startActivity(intent)
                }
            }

            if (number == 8) {
                button.text = getString(R.string.data_store_button_name)
                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, DataStoreActivity::class.java)
                    startActivity(intent)
                }
            }

            layout.addView(button)
        }
    }

    private fun hasContactsPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun requestContactsPermission() {
        if (!hasContactsPermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.READ_CONTACTS),
                REQUEST_READ_CONTACTS_PERMISSION
            )
        }
    }

    private val pickContact =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val contactUri: Uri? = result.data?.data
                contactUri?.let { uri ->
                    getContactInfo(uri)
                }
            }
        }

    @SuppressLint("Range")
    private fun getContactInfo(contactUri: Uri) {
        val cursor: Cursor? = contentResolver.query(contactUri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val name: String =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                var phoneNumber: String? = null
                val id = it.getLong(it.getColumnIndex(ContactsContract.Contacts._ID))
                if (it.getInt(it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val phoneCursor: Cursor? = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = $id",
                        null,
                        null
                    )
                    phoneCursor?.use { pc ->
                        if (pc.moveToFirst()) {
                            phoneNumber =
                                pc.getString(pc.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        }
                    }
                }

                showContactDialog(name, phoneNumber)
            }
        }
    }

    private fun showContactDialog(name: String, phoneNumber: String?) {
        val dialogBuilder = AlertDialog.Builder(this)
        if (phoneNumber != null) {
            dialogBuilder.setMessage("$name\n$phoneNumber")
        } else {
            dialogBuilder.setMessage(name)
        }
        dialogBuilder.setPositiveButton("OK", null)
        val dialog = dialogBuilder.create()
        dialog.show()
    }
}