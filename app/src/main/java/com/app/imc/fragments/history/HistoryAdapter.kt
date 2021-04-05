package com.app.imc.fragments.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.imc.R
import com.app.imc.application.ImcApplication
import java.lang.Exception

// Classe para gerenciar a lista, obrigatória para RecyclerView
class HistoryAdapter(var listener: ClickItemHistoryListener) : RecyclerView.Adapter<HistoryAdapter.HistoryAdapterViewHolder>() {

    // Lista
    private val list: MutableList<Imc> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapterViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryAdapterViewHolder(view, list, listener)
    }

    override fun onBindViewHolder(holder: HistoryAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList() {
        var list: List<Imc> = mutableListOf()
        try {
            list = ImcApplication.instance.helperDB?.selectImc(null) ?: mutableListOf()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    // Classe para gerenciar cada item da lista
    class HistoryAdapterViewHolder(itemView: View, var list: List<Imc>, var listener: ClickItemHistoryListener) : RecyclerView.ViewHolder(itemView) {

        private val imcTV: TextView = itemView.findViewById(R.id.tv_item_imc)

        // Evento de click para item da lista
        init {
            itemView.setOnClickListener {
                listener.clickItemHistory(list[adapterPosition])
            }
        }

        fun bind(imc: Imc) {
            val result = imc.result
            when {
                result < 16.0 -> imcTV.setText("Magreza Grave")
                result > 16.0 && result < 16.9 -> imcTV.setText("Magreza Moderada")
                result > 17.0 && result < 18.4 -> imcTV.setText("Magreza Leve")
                result > 25.5 && result < 29.9 -> imcTV.setText("Sobrepeso")
                result > 30.0 && result < 34.9 -> imcTV.setText("Obesidade Grau I")
                result > 35.0 && result < 39.9 -> imcTV.setText("Obesidade Grau II")
                result > 40.0 -> imcTV.setText("Obesidade Grau III")
                else -> imcTV.setText("Saudável")
            }
        }

    }

}