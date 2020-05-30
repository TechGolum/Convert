package com.example.convert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.size
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<TextInputEditText>(R.id.input)
        val output = findViewById<TextView>(R.id.output)
        val convert = findViewById<Button>(R.id.convert)
        val input_name = findViewById<TextView>(R.id.input_name)
        val output_name = findViewById<TextView>(R.id.output_name)
        val change = findViewById<Button>(R.id.change)
        var dollar : Boolean = false
        var course : Double = 1/74.0
        val spinner_in = findViewById<Spinner>(R.id.spinner_in)
        val spinner_out = findViewById<Spinner>(R.id.spinner_out)
        val change_cur = findViewById<Button>(R.id.change_cur)

        ArrayAdapter.createFromResource(
            this,
            R.array.cur,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_in.adapter = adapter
            spinner_out.adapter = adapter
        }
        change_cur.setOnClickListener {
            input_name.text = spinner_in.selectedItem.toString()
            output_name.text = spinner_out.selectedItem.toString()
        }
        change.setOnClickListener {
            dollar = !dollar
            if(dollar)
            {
                input_name.text = "Доллар"
                output_name.text = "Рубль"
                course = 74.0
                if(input.text.toString().isNotEmpty()) {
                    output.text = (input.text.toString().toDouble() * course).toString()
                    if (output.text.length > 5) output.text = output.text.subSequence(0, 4)
                }
            }
            else
            {
                output_name.text = "Доллар"
                input_name.text = "Рубль"
                course = 1/74.0
                if(input.text.toString().isNotEmpty()) {
                    output.text = (input.text.toString().toDouble() * course).toString()
                    if (output.text.length > 5) output.text = output.text.subSequence(0, 4)
                }
            }
        }
        convert.setOnClickListener{
            if(input.text.toString().isNotEmpty()) {
                output.text = (input.text.toString().toDouble() * course).toString()
                if (output.text.length > 5) output.text = output.text.subSequence(0, 4)
            }
        }

    }
}