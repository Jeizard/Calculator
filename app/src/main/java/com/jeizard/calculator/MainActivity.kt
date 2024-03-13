package com.jeizard.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.jeizard.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

private const val OPERATOR_ADD = "+"
private const val OPERATOR_SUBTRACT = "-"
private const val OPERATOR_MULTIPLY = "×"
private const val OPERATOR_DIVIDE = "÷"
private const val OPERATOR_POWER = "^"
private const val OPERATOR_DOT = "."
private const val OPERATOR_OPEN_BRACKET = "("
private const val OPERATOR_CLOSE_BRACKET = ")"
private const val OPERATOR_PERCENT = "%"
private const val OPERATOR_RECIPROCAL = "^(-1)"
private const val OPERATOR_ROOT = "√"

private const val STATE_ENTRY_FIELD = "Entry Field Text"
private const val STATE_RESULT_FIELD = "Result Field Text"

private const val DIVISION_BY_ZERO_EXP4J_ERROR = "Division by zero!"
private const val DIVISION_BY_ZERO_ERROR = "На 0 делить нельзя!"
private const val ROOT_ERROR = "Нельзя брать корень из отрицательного числа!"

private const val LOG_ERROR_TAG = "Ошибка"

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var memoryValue: Double = 0.0

    private val operators = listOf(OPERATOR_ADD, OPERATOR_SUBTRACT, OPERATOR_MULTIPLY,
        OPERATOR_DIVIDE, OPERATOR_POWER, OPERATOR_DOT, OPERATOR_OPEN_BRACKET, OPERATOR_PERCENT
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setButtonsClickListeners();
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_ENTRY_FIELD, binding.entryField.text.toString())
        outState.putString(STATE_RESULT_FIELD, binding.resultField.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.entryField.text = savedInstanceState.getString(STATE_ENTRY_FIELD)
        binding.resultField.text = savedInstanceState.getString(STATE_RESULT_FIELD)
    }

    private fun setButtonsClickListeners() {
        val buttons = listOf(
            binding.btnClear, binding.btnBack, binding.btnResult,
            binding.btnAdd, binding.btnSubtract, binding.btnMultiply,
            binding.btnDivision, binding.btnPercent, binding.btnPower,
            binding.btnDot, binding.btnBracket, binding.btnReciprocal,
            binding.btnRoot, binding.btnSign,
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9,
            binding.btnMemoryStore, binding.btnMemoryRecall, binding.btnMemoryClear,
            binding.btnMemoryAdd, binding.btnMemorySubtract
        )
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onClick(v: View) {
        var str = binding.entryField.text.toString()

        fun appendSymbol(symbol: String) {
            str += symbol
            setEntryField(str)
        }

        fun appendNumber(number: String) {
            val lastNumber = str.split(Regex("""[-+*/()]""")).lastOrNull()
            if (lastNumber != null && lastNumber == "0" ) {
                str = str.dropLast(lastNumber.length)
            }
            if (!str.endsWith(OPERATOR_CLOSE_BRACKET)) {
                appendSymbol(number)
                calculateResult()
            }
        }

        fun appendOperator(operator: String) {
            if (str.isNotEmpty()) {
                if (operators.any { str.endsWith(it) }) {
                    str = str.dropLast(1)
                    if(str.endsWith(OPERATOR_ROOT)){
                        str = str.dropLast(2)
                    }
                }
                if (str.isNotEmpty()) {
                    appendSymbol(operator)
                    calculateResult()
                }
            }
        }

        when(v.id){
            binding.btnClear.id -> {
                setEntryField("")
                calculateResult() //
            }
            binding.btnBack.id -> {
                if(str.isNotEmpty()){
                    str = str.dropLast(1)
                    if(str.endsWith(OPERATOR_ROOT)){
                        str = str.dropLast(1)
                    }
                    setEntryField(str)
                    calculateResult()
                }
            }
            binding.btnResult.id -> {
                if(binding.resultField.text.toString().toDoubleOrNull() != null){
                    binding.entryField.text = binding.resultField.text
                    binding.resultField.text = ""
                }
            }
            binding.btnAdd.id -> appendOperator(OPERATOR_ADD)
            binding.btnSubtract.id -> {
                if(operators.any { str.endsWith(it) }) {
                    if(!(str.endsWith(OPERATOR_OPEN_BRACKET))){
                        str = str.dropLast(1)
                    }
                }
                appendSymbol(OPERATOR_SUBTRACT)
                calculateResult()
            }
            binding.btnMultiply.id -> appendOperator(OPERATOR_MULTIPLY)
            binding.btnDivision.id -> appendOperator(OPERATOR_DIVIDE)
            binding.btnPercent.id -> {
                if(str.isNotEmpty()) {
                    if (operators.any { str.endsWith(it) }) {
                        str = str.dropLast(1)
                        if(str.endsWith(OPERATOR_ROOT)){
                            str = str.dropLast(2)
                        }
                    }
                    appendSymbol(OPERATOR_PERCENT)
                    calculateResult()
                }
            }
            binding.btnPower.id -> {
                if(str.isNotEmpty()) {
                    if (operators.any { str.endsWith(it) }) {
                        str = str.dropLast(1)
                    }
                    appendSymbol(OPERATOR_POWER)
                }
            }
            binding.btnDot.id -> {
                if ((str.isEmpty() || operators.any { str.endsWith(it)}) && !str.endsWith(OPERATOR_DOT)) {
                    str += "0$OPERATOR_DOT"
                } else {
                    val lastNumber = str.split(Regex("""[-+*/()]""")).last()
                    if (!lastNumber.contains(OPERATOR_DOT)) {
                        str += OPERATOR_DOT
                    }
                }
                setEntryField(str)
                calculateResult()
            }
            binding.btnBracket.id -> {
                if(str.isEmpty() || operators.any { str.endsWith(it) }) {
                    str += OPERATOR_OPEN_BRACKET
                    setEntryField(str)
                }
                else if(str[str.lastIndex] != '(' && str.count { it == '(' } > str.count { it == ')' }){
                    appendSymbol(OPERATOR_CLOSE_BRACKET)
                    calculateResult()
                }
            }
            binding.btnReciprocal.id -> {
                if(str.isNotEmpty()){
                    if(operators.any { str.endsWith(it) }){
                        str = str.dropLast(1)
                    }
                    if(str.endsWith(OPERATOR_RECIPROCAL)){
                        str = str.dropLast(5)
                    }
                    else{
                        str += OPERATOR_RECIPROCAL
                    }
                    setEntryField(str)
                    calculateResult()
                }
            }
            binding.btnRoot.id -> {
                if(str.isNotEmpty() && !(operators.any { str.endsWith(it) })){
                    str += OPERATOR_MULTIPLY
                }
                appendSymbol(OPERATOR_ROOT + OPERATOR_OPEN_BRACKET)
                calculateResult()
            }
            binding.btnSign.id -> {
                if (str.isEmpty()) {
                    str += "(-"
                }
                else if (str == "(-"){
                    str = ""
                }
                else {
                    val lastNumber = str.split(Regex("""[-+*/()]""")).lastOrNull()
                    if (!lastNumber.isNullOrBlank()) {
                        val lastIndex = str.lastIndexOf(lastNumber)
                        str = if (lastIndex > 0 && str[lastIndex - 1] == '-') {
                            str.substring(0, lastIndex - 2) + lastNumber
                        } else {
                            str.substring(0, lastIndex) + "(-$lastNumber"
                        }
                    }
                }
                setEntryField(str)
                calculateResult()
            }
            binding.btn0.id -> appendNumber("0")
            binding.btn1.id -> appendNumber("1")
            binding.btn2.id -> appendNumber("2")
            binding.btn3.id -> appendNumber("3")
            binding.btn4.id -> appendNumber("4")
            binding.btn5.id -> appendNumber("5")
            binding.btn6.id -> appendNumber("6")
            binding.btn7.id -> appendNumber("7")
            binding.btn8.id -> appendNumber("8")
            binding.btn9.id -> appendNumber("9")
            binding.btnMemoryStore.id -> {
                if(binding.resultField.text.toString().toDoubleOrNull() != null) {
                    memoryValue = if (binding.resultField.text.toString().isNotEmpty()) {
                        binding.resultField.text.toString().toDouble()
                    } else {
                        str.toDouble()
                    }
                }
            }
            binding.btnMemoryRecall.id -> {
                if(operators.any { str.endsWith(it) } || str.isEmpty())
                {
                    val longRes = memoryValue.toLong()
                    var resStr: String
                    resStr = if(memoryValue == longRes.toDouble()){
                        longRes.toString()
                    } else{
                        memoryValue.toString()
                    }
                    if(resStr.startsWith('-')){
                        resStr = "($resStr)"
                    }
                    appendSymbol(resStr)
                    calculateResult()
                }
            }
            binding.btnMemoryClear.id -> {
                memoryValue = 0.0
            }
            binding.btnMemoryAdd.id -> {
                if(binding.resultField.text.toString().isNotEmpty()) {
                    memoryValue += binding.resultField.text.toString().toDouble()
                }
            }
            binding.btnMemorySubtract.id -> {
                if(binding.resultField.text.toString().isNotEmpty()) {
                    memoryValue -= binding.resultField.text.toString().toDouble()
                }
            }
        }
    }
    private fun setEntryField(str: String) {
        binding.entryField.text = str
    }

    private fun calculateResult(){
        try {
            var entryText = binding.entryField.text.toString()
            if((entryText.count { it == '(' }) - (entryText.count { it == ')' }) == 1){
                entryText += ')'
            }
            if(entryText.isNotEmpty()){
                entryText = entryText.replace(OPERATOR_ROOT + OPERATOR_OPEN_BRACKET, "sqrt(")

                entryText = entryText.replace(Regex("%(?=\\d)"), "%*")
                entryText = entryText.replace(OPERATOR_PERCENT, "/100")

                entryText = entryText.replace(OPERATOR_DIVIDE, "/")
                entryText = entryText.replace(OPERATOR_MULTIPLY, "*")

                val ex = ExpressionBuilder(entryText).build()
                val result = ex.evaluate()

                if(entryText.contains("sqrt(") && result.isNaN()) {
                    binding.resultField.text = ROOT_ERROR
                }
                else{
                    val longRes = result.toLong()
                    if(result == longRes.toDouble()){
                        binding.resultField.text = longRes.toString()
                    }
                    else{
                        binding.resultField.text = DecimalFormat("#.##########").format(result)
                    }
                }
            }
            else{
                binding.resultField.text = ""
            }
        }
        catch (e: Exception){
            if(e.message.toString() == DIVISION_BY_ZERO_EXP4J_ERROR) {
                binding.resultField.text = DIVISION_BY_ZERO_ERROR
            }
            else{
                binding.resultField.text = ""
            }
            Log.d(LOG_ERROR_TAG, e.message.toString())
        }
    }
}