package com.app.imc.viewmodel

import androidx.lifecycle.ViewModel
import com.app.imc.application.ImcApplication
import com.app.imc.domain.Imc

class ResultViewModel:ViewModel() {

    fun saveImc(imc: Imc) {
        try {
            ImcApplication.instance.helperDB?.insertImc(imc)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun deleteImc(id: Int) {
        try {
            ImcApplication.instance.helperDB?.deleteImc(id)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}