package com.andydeveloper.coroutinedemo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.andydeveloper.coroutinedemo.viewmodel.FragmentViewModel

class ResultFragment : Fragment(R.layout.fragment_result) {
    private val viewModel: FragmentViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = view.findViewById<TextView>(R.id.result)
        viewModel.info.observe(viewLifecycleOwner) {
            result.text = it
        }
    }
}
