package com.andydeveloper.coroutinedemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.andydeveloper.coroutinedemo.viewmodel.FragmentViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: FragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = view.findViewById<EditText>(R.id.input_edit)
        val info = view.findViewById<TextView>(R.id.info)
        val progressIndicator = view.findViewById<ProgressBar>(R.id.progress_circular)
        with(view.findViewById<Button>(R.id.search)) {
            setOnClickListener {
                viewModel.getGithubUser(editText.text.toString())
            }
        }


        viewModel.info.observe(viewLifecycleOwner) {
            info.text = it
        }

        viewModel.loading.observe(viewLifecycleOwner){
            when(it){
                true -> progressIndicator.visibility = View.VISIBLE
                false -> progressIndicator.visibility = View.GONE
            }
        }
    }
}