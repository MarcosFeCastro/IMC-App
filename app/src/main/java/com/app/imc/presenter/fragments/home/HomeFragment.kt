package com.app.imc.presenter.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.app.imc.R
import com.app.imc.application.ImcApplication
import com.app.imc.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var resultTv: TextView
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultTv = view.findViewById(R.id.tv_result)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        loadLatestResults()
    }

    private fun loadLatestResults() {
        resultTv.text = homeViewModel.getLastResult()
    }

}