package com.app.imc.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.app.imc.R
import com.app.imc.application.ImcApplication
import com.app.imc.domain.Imc
import com.app.imc.viewmodel.ResultViewModel

class ResultActivity : AppCompatActivity() {

    private lateinit var imc: Imc
    private lateinit var resultViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        resultViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        getExtras()
        bindViews()
        initListeners()
    }

    companion object {
        const val EXTRA_RESULT: String = "EXTRA_RESULT"
    }

    private fun getExtras() {
        imc = intent.getParcelableExtra<Imc>(EXTRA_RESULT)!!
    }

    private fun bindViews() {
        findViewById<TextView>(R.id.txt_imc).text = "Resultado: %.1f".format(imc.result)
        findViewById<TextView>(R.id.txt_difference).text = "Direfença: %.2f Kg".format(imc.difference)
        var titleTXT = findViewById<TextView>(R.id.txt_title_result)
        val result = imc.result
        if (result != null) {
            when {
                result < 16.0 -> titleTXT.text = "Magreza Grave"
                result > 16.0 && result < 16.9 -> titleTXT.text = "Magreza Moderada"
                result > 17.0 && result < 18.4 -> titleTXT.text = "Magreza Leve"
                result > 25.5 && result < 29.9 -> titleTXT.text = "Sobrepeso"
                result > 30.0 && result < 34.9 -> titleTXT.text = "Obesidade Grau I"
                result > 35.0 && result < 39.9 -> titleTXT.text = "Obesidade Grau II"
                result > 40.0 -> titleTXT.text = "Obesidade Grau III"
                else -> titleTXT.text = "Saudável"
            }
        }
    }

    private fun initListeners() {
        val backBtn = findViewById<Button>(R.id.btn_back)
        backBtn.setOnClickListener {
            closeActivity()
        }
        if (isRegistered()) {
            val deleteBtn = findViewById<Button>(R.id.btn_delete)
            deleteBtn.visibility = View.VISIBLE
            deleteBtn.setOnClickListener {
                deleteImc(imc.id!!)
            }
        } else {
            val saveBtn = findViewById<Button>(R.id.btn_save)
            saveBtn.visibility = View.VISIBLE
            saveBtn.setOnClickListener {
                saveImc()
                goToHome()
            }
        }
    }

    private fun saveImc() {
        resultViewModel.saveImc(imc)
    }

    private fun deleteImc(id: Int) {
        resultViewModel.deleteImc(id)
        finish()
    }

    private fun isRegistered() : Boolean {
        return imc.id > -1
    }

    private fun goToHome() {
        val intent = Intent( this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun closeActivity() {
        finish()
    }

}