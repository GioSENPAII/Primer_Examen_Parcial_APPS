package com.example.binarioeducativo.games

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.binarioeducativo.R
import com.example.binarioeducativo.ThemeManager
import com.google.android.material.appbar.MaterialToolbar
import kotlin.random.Random

class BinarySwitchGameActivity : AppCompatActivity() {

    private lateinit var tvTargetNumber: TextView
    private lateinit var tvBinaryValue: TextView
    private lateinit var btnCheckAnswer: Button
    private lateinit var tvScore: TextView
    private lateinit var tvFeedback: TextView

    private lateinit var switchButtons: List<Button>
    private var currentBinary = 0
    private var targetDecimal = 0
    private var score = 0
    private var questionsAsked = 0

    companion object {
        private const val MAX_QUESTIONS = 10
        private const val BITS = 4  // Usaremos 4 bits (0-15)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar el tema guardado
        val themeOption = ThemeManager.getThemeOption(this)
        ThemeManager.applyTheme(this, themeOption)

        setContentView(R.layout.activity_binary_switch_game)

        // Configurar toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Inicializar vistas
        tvTargetNumber = findViewById(R.id.tvTargetNumber)
        tvBinaryValue = findViewById(R.id.tvBinaryValue)
        btnCheckAnswer = findViewById(R.id.btnCheckAnswer)
        tvScore = findViewById(R.id.tvScore)
        tvFeedback = findViewById(R.id.tvFeedback)

        // Inicializar botones de interruptores (switches)
        switchButtons = listOf(
            findViewById(R.id.btnBit0),
            findViewById(R.id.btnBit1),
            findViewById(R.id.btnBit2),
            findViewById(R.id.btnBit3)
        )

        // Configurar eventos de clic para los interruptores
        for (i in switchButtons.indices) {
            val button = switchButtons[i]
            button.setOnClickListener {
                toggleBit(i)
            }
        }

        btnCheckAnswer.setOnClickListener {
            checkAnswer()
        }

        // Iniciar el juego
        resetSwitches()
        generateQuestion()
    }

    private fun toggleBit(position: Int) {
        // Alternar el bit en la posición especificada
        currentBinary = currentBinary xor (1 shl position)

        // Actualizar la vista del interruptor
        val button = switchButtons[position]
        if ((currentBinary and (1 shl position)) != 0) {
            button.text = "1"
            button.setBackgroundResource(R.drawable.switch_on_background)
        } else {
            button.text = "0"
            button.setBackgroundResource(R.drawable.switch_off_background)
        }

        // Actualizar la representación del número binario
        updateBinaryDisplay()
    }

    private fun updateBinaryDisplay() {
        // Convertir el número actual a representación binaria
        val binaryString = Integer.toBinaryString(currentBinary).padStart(BITS, '0')
        tvBinaryValue.text = binaryString
    }

    private fun resetSwitches() {
        // Restablecer todos los interruptores a 0
        currentBinary = 0
        for (button in switchButtons) {
            button.text = "0"
            button.setBackgroundResource(R.drawable.switch_off_background)
        }
        updateBinaryDisplay()
    }

    private fun generateQuestion() {
        // Generar un número decimal aleatorio entre 0 y 15
        targetDecimal = Random.nextInt(0, 16)
        tvTargetNumber.text = targetDecimal.toString()

        // Restablecer interruptores para la nueva pregunta
        resetSwitches()

        // Limpiar retroalimentación
        tvFeedback.visibility = View.INVISIBLE
    }

    private fun checkAnswer() {
        if (currentBinary == targetDecimal) {
            tvFeedback.text = getString(R.string.correct_feedback)
            tvFeedback.setTextColor(getColor(R.color.correct_answer))
            score++
        } else {
            val correctBinary = Integer.toBinaryString(targetDecimal).padStart(BITS, '0')
            tvFeedback.text = getString(R.string.wrong_feedback_switch, correctBinary)
            tvFeedback.setTextColor(getColor(R.color.wrong_answer))
        }

        tvFeedback.visibility = View.VISIBLE
        questionsAsked++

        // Actualizar puntuación
        tvScore.text = getString(R.string.score_text, score, questionsAsked)

        // Comprobar si hemos llegado al máximo de preguntas
        if (questionsAsked >= MAX_QUESTIONS) {
            showFinalScore()
        } else {
            // Esperar brevemente antes de la siguiente pregunta
            btnCheckAnswer.postDelayed({
                generateQuestion()
            }, 1500)
        }
    }

    private fun showFinalScore() {
        val percentage = (score * 100) / MAX_QUESTIONS

        val message = when {
            percentage >= 90 -> getString(R.string.excellent_score, score, MAX_QUESTIONS)
            percentage >= 70 -> getString(R.string.good_score, score, MAX_QUESTIONS)
            else -> getString(R.string.keep_practicing, score, MAX_QUESTIONS)
        }

        btnCheckAnswer.isEnabled = false
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}