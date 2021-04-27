package com.app.imc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.imc.domain.Imc

class CalculatorViewModel: ViewModel() {

    // Live Data
    var peso = MutableLiveData<String>().apply { value = null }
    var altura = MutableLiveData<String>().apply { value = null }
    var idade = MutableLiveData<String>().apply { value = null }
    var sexo = MutableLiveData<String>().apply { value = "Masculino" }

    fun calculate() : Imc? {
        val p = peso.value?.toFloatOrNull()
        val a = altura.value?.toFloatOrNull()
        if (p != null && a != null) {
            val result = p / (a * a)
            var difference = 0.00
            if (result > 24.9) {
                difference = result - 24.9
            } else if (result < 18.5) {
                difference = 18.5 - result
            }
            return Imc(-1, result, difference.toFloat())
        } else {
            return null
        }
    }

}