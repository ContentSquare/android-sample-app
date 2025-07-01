package com.example.androidsampleapp.fragment

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.contentsquare.android.api.sessionreplay.csqMaskRadialPicker
import com.contentsquare.android.api.sessionreplay.csqUnMaskHeader
import java.util.Calendar


class TimePickerFragment(
    private val onTimeSelected: (hourOfDay: Int, minute: Int) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            onTimeSelected(selectedHour, selectedMinute)
        }, hour, minute, true).also {
            it.csqMaskRadialPicker()
            it.csqUnMaskHeader()
        }
    }
}