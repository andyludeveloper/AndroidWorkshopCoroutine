package com.andydeveloper.coroutinedemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andydeveloper.coroutinedemo.viewmodel.FragmentViewModel

class ResultFragment : Fragment(R.layout.fragment_result) {
    private val viewModel: FragmentViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = view.findViewById<TextView>(R.id.result)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_circular)
        viewModel.info.observe(viewLifecycleOwner) {
            result.text = it
        }

        view.findViewById<Button>(R.id.cancel_button).also {
            it.setOnClickListener {
                viewModel.cancelJob()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            when (it) {
                true -> progressBar.visibility = View.VISIBLE
                false -> progressBar.visibility = View.GONE
            }
        }
    }
}
