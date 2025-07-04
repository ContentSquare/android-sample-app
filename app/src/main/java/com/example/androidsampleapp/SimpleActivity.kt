package com.example.androidsampleapp

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.contentsquare.android.Contentsquare
import com.contentsquare.android.api.sessionreplay.csqMaskPositiveButton
import com.contentsquare.android.api.sessionreplay.csqMaskTitle
import com.example.androidsampleapp.analytics.Analytics
import com.example.androidsampleapp.fragment.BottomSheetFragment
import com.example.androidsampleapp.fragment.CustomAlertDialog
import com.example.androidsampleapp.fragment.DatePickerFragment
import com.example.androidsampleapp.fragment.TimePickerFragment
import java.util.Locale

class SimpleActivity : AppCompatActivity() {

    private val TAG: String? = SimpleActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
    }

    override fun onResume() {
        super.onResume()
        Analytics.tagScreen("Simple-Activity")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.simple_menu, menu)

        // Apply masking for menu items here
        Contentsquare.unMaskMenuItem(R.id.menu_unmasked_item)
        Contentsquare.maskMenuItem(R.id.menu_masked_item)
        Analytics.tagScreen("Simple_Menu")

        return super.onCreateOptionsMenu(menu)
    }

    fun openBottomSheets(view: View) {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        Analytics.tagScreen("Bottom_Sheets")
    }

    fun openDatePicker(view: View) {
        val datePickerFragment = DatePickerFragment { year, month, day ->
            val formattedDate = "$day/${month + 1}/$year"
            Log.i(TAG, "Selected Date: $formattedDate")
        }
        datePickerFragment.show(supportFragmentManager, "datePicker")
        Analytics.tagScreen("Date_Picker")
    }

    fun openTimePicker(view: View) {
        val timePickerFragment = TimePickerFragment { hour, minute ->
            val formattedTime = String.format(Locale.ROOT, "%02d:%02d", hour, minute)
            Log.i(TAG, "Selected Time: $formattedTime")
        }
        timePickerFragment.show(supportFragmentManager, "timePicker")
        Analytics.tagScreen("Time_Picker")
    }

    fun openAlertDialog(view: View) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@SimpleActivity)
        builder.setMessage(getString(R.string.alert_message))
        builder.setTitle(getString(R.string.alert_title))
        builder.setCancelable(true)
        builder.setPositiveButton(getString(R.string.ok)) { dialog: DialogInterface?, _: Int ->
            dialog?.cancel()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

        // Apply Masking only after the show() is called
        alertDialog.csqMaskTitle()
        alertDialog.csqMaskPositiveButton()
        Analytics.tagScreen("Simple_Dialog")
    }

    fun openCustomAlertDialog(view: View) {
        val dialog = CustomAlertDialog(
            context = this,
            message = getString(R.string.sense_ai_description_short),
            imageResId = R.drawable.sense_ai_image,
            onOkPressed = {
                Log.i(TAG, "CustomAlertDialog: OK button tapped")
            }
        )
        dialog.show()
        Analytics.tagScreen("Simple_Custom_Dialog")
    }
}
