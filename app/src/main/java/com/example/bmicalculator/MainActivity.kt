package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<TextView>(R.id.etWeight)
        val heightText = findViewById<TextView>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(inputvalidator(weight,height)){

                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                //limiting the result to 2 decimal places for the user to easily understand the result.
                val bmilimited = String.format("%.2f",bmi).toFloat()
                displayResult(bmilimited)

            }
        }
    }

    private fun inputvalidator(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"No input for weight", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"No input for height", Toast.LENGTH_LONG).show()
                return false
            }else -> {return true}
        }
    }

    private fun displayResult(bmi : Float){
        var resultIndex = findViewById<TextView>(R.id.tvindex)
        var resultDescription = findViewById<TextView>(R.id.tvresult)
        val info = findViewById<TextView>(R.id.tvinfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is between 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi < 18.50 -> {
                resultText = "Under_weight"
                color = R.color.Under_weight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.Healthy
            }
            bmi in 25.00..29.99 ->{
                resultText = "Over Weight"
                color = R.color.Over_weight
            }
            bmi > 29.99 -> {
                resultText = "OBESE"
                color = R.color.OBESE
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}