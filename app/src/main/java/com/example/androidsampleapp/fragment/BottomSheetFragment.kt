package com.example.androidsampleapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.contentsquare.android.Contentsquare
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.androidsampleapp.R

class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
            .also {
                Contentsquare.mask(it.findViewById<ImageView>(R.id.bottom_sheet_image_view))
                Contentsquare.unMask(it.findViewById<TextView>(R.id.bottom_sheet_text_view))
                Contentsquare.mask(it.findViewById<TextView>(R.id.textTitle))
            }
    }
}