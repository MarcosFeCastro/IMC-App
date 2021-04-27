package com.app.imc.viewmodel

import androidx.lifecycle.ViewModel
import com.app.imc.application.ImcApplication

class HomeViewModel: ViewModel() {

    fun getLastResult(): String {
        var lastImc = ImcApplication.instance.helperDB?.selectImc(1)
        if (!lastImc.isNullOrEmpty()) {
            return lastImc[0].result.toString()
        } else {
            return "Faça um calculo"
        }
    }
}