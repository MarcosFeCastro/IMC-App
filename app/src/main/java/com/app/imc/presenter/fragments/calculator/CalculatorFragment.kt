package com.app.imc.presenter.fragments.calculator

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.imc.R
import com.app.imc.presenter.ResultActivity
import com.app.imc.domain.Imc
import com.app.imc.viewmodel.CalculatorViewModel

class CalculatorFragment : Fragment() {

    private lateinit var pesoEDT: EditText
    private lateinit var alturaEDT: EditText
    private lateinit var idadeEDT: EditText
    private lateinit var sexoGroup: RadioGroup
    private lateinit var sexoRadio: RadioButton
    private lateinit var calcular: Button

    private lateinit var calculatorViewModel: CalculatorViewModel

    private val model: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //model.peso.observe(viewLifecycleOwner, Observer<String> { if (it != null) pesoEDT.setText(it) })
        //model.altura.observe(viewLifecycleOwner, Observer<String> { if (it != null) alturaEDT.setText(it) })
        //model.idade.observe(viewLifecycleOwner, Observer<String> { if (it != null) idadeEDT.setText(it) })

        model.sexo.observe(viewLifecycleOwner, Observer<String> {
            if (it == "Masculino") {
                sexoRadio = view.findViewById(R.id.maleRadio)
                sexoRadio.isChecked = true
            } else {
                sexoRadio = view.findViewById(R.id.femaleRadio)
                sexoRadio.isChecked = true
            }
        })

        calculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        pesoEDT = view.findViewById(R.id.pesoEt)
        pesoEDT.doAfterTextChanged { calculatorViewModel.peso.value = it.toString() }
        alturaEDT = view.findViewById(R.id.alturaEt)
        alturaEDT.doAfterTextChanged { calculatorViewModel.altura.value = it.toString() }
        idadeEDT = view.findViewById(R.id.idadeEt)
        idadeEDT.doAfterTextChanged { calculatorViewModel.idade.value = it.toString() }
        sexoGroup = view.findViewById(R.id.sexoRadioGroup)
        sexoRadio = view.findViewById(R.id.maleRadio)
        sexoGroup.setOnCheckedChangeListener { _, checkedId ->
            sexoRadio = view.findViewById(checkedId)
            calculatorViewModel.sexo.value = sexoRadio.text.toString()
            Toast.makeText(activity, "Sexo: ${sexoRadio.text}", Toast.LENGTH_SHORT).show()
        }

        calcular = view.findViewById(R.id.calcularBtn)
        calcular.setOnClickListener {
            val res: Imc? = calculatorViewModel.calculate()
            if (res == null) {
                Toast.makeText(activity,"Informe seu peso e altura", Toast.LENGTH_SHORT).show()
            } else {
                val imc = Imc(res.id, res.result, res.difference)
                val intent = Intent(activity, ResultActivity::class.java)
                intent.putExtra(ResultActivity.EXTRA_RESULT, imc)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        pesoEDT.setText(model.peso.value)
        alturaEDT.setText(model.altura.value)
        idadeEDT.setText(model.idade.value)
    }

}