package com.example.convert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.size
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<TextInputEditText>(R.id.input)
        val output = findViewById<TextView>(R.id.output)
        val convert = findViewById<Button>(R.id.convert)
        val inputName = findViewById<TextView>(R.id.input_name)
        val outputName = findViewById<TextView>(R.id.output_name)
        val change = findViewById<Button>(R.id.change)
        var course : Double = 1.0
        val spinnerIn = findViewById<Spinner>(R.id.spinner_in)
        val spinnerOut = findViewById<Spinner>(R.id.spinner_out)
        val changeCur = findViewById<Button>(R.id.change_cur)
        var save : String


        ArrayAdapter.createFromResource(
            this,
            R.array.cur,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerIn.adapter = adapter
            spinnerOut.adapter = adapter
        }

        var nums_cur = arrayOf("Доллар", "Рубль", "Евро", "Фунт")
        var curs : MutableMap<String, Array<Double>> = mutableMapOf()
        curs[nums_cur[0]] = arrayOf(1.0, 74.0, 0.89, 0.79)
        curs[nums_cur[1]] = arrayOf(0.014, 1.0, 0.013, 87.0)
        curs[nums_cur[2]] = arrayOf(1.13, 77.0, 1.0, 0.89)
        curs[nums_cur[3]] = arrayOf(1.27, 0.011, 1.12, 1.0)


        changeCur.setOnClickListener {
            inputName.text = spinnerIn.selectedItem.toString()
            outputName.text = spinnerOut.selectedItem.toString()
        }

        change.setOnClickListener {
            save = inputName.text.toString()
            inputName.text = outputName.text
            outputName.text = save
        }

        convert.setOnClickListener{
            if(input.text.toString().isNotEmpty()) {
                course = curs[inputName.text.toString()]!![nums_cur.indexOf(outputName.text.toString())]
                output.text = (input.text.toString().toDouble() * course).toString()
                if (output.text.length > 5 && !output.text.toString().toCharArray().contains('.')) output.text = output.text.subSequence(0, 5).toString() + "E" + (output.text.length - 5).toString()
            }
        }

    }
}