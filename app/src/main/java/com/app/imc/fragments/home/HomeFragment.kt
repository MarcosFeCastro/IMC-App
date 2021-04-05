package com.app.imc.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.imc.R
import com.app.imc.application.ImcApplication

class HomeFragment : Fragment() {

    private lateinit var resultTv: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultTv = view.findViewById(R.id.tv_result)
        loadLatestResults()
    }

    private fun loadLatestResults() {
        var lastImc = ImcApplication.instance.helperDB?.selectImc(1)
        if (!lastImc.isNullOrEmpty()) {
            resultTv.text = lastImc[0].result.toString()
        }
    }

}