package com.nrv.weatherdemo.ui.historical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nrv.weatherdemo.R

class Last10DayFragment : Fragment() {

    private lateinit var last10DayViewModel: Last10DayViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        last10DayViewModel =
                ViewModelProvider(this).get(Last10DayViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_last_10_days, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        last10DayViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}