package com.app.imc.fragments.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.imc.R
import com.app.imc.ResultActivity
import com.app.imc.ResultActivity.Companion.EXTRA_RESULT

class HistoryFragment : Fragment(), ClickItemHistoryListener {

    private var recyclerViewList: RecyclerView? = null
    private val adapter = HistoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewList = view.findViewById(R.id.recycler_view_list)
        bindView()
        updateList()
    }

    private fun bindView() {
        recyclerViewList?.adapter = adapter
        recyclerViewList?.layoutManager = LinearLayoutManager(activity)
    }

    private fun updateList() {
        adapter.updateList()
    }

    // Envia dados do item para Result activity
    override fun clickItemHistory(imc: Imc) {
        val intent = Intent(activity, ResultActivity::class.java)
        intent.putExtra(EXTRA_RESULT, imc)
        startActivity(intent)
    }

}