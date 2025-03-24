package com.example.binarioeducativo.games

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.binarioeducativo.R
import com.example.binarioeducativo.ThemeManager
import com.google.android.material.appbar.MaterialToolbar
import kotlin.random.Random

class BinaryConverterGameActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var etAnswer: EditText
    private lateinit var btnSubmit: Button
    private lateinit var tvScore: TextView
    private lateinit var tvFeedback: TextView

    private var currentNumber = 0
    private var currentMode = DECIMAL_TO_BINARY
    private var score = 0
    private var questionsAsked = 0

    companion object {
        private const val DECIMAL_TO_BINARY = 0
        private const val BINARY_TO_DECIMAL = 1
        private const val MAX_QUESTIONS = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar el tema guardado
        val themeOption = ThemeManager.getThemeOption(this)
        ThemeManager.applyTheme(this, themeOption)

        setContentView(R.layout.activity_binary_converter_game)

        // Configurar toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Inicializar vistas
        tvQuestion = findViewById(R.id.tvQuestion)
        etAnswer = findViewById(R.id.etAnswer)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvScore = findViewById(R.id.tvScore)
        tvFeedback = findViewById(R.id.tvFeedback)

        btnSubmit.setOnClickListener {
            checkAnswer()
        }

        // Generar la primera pregunta
        generateQuestion()
    }

    private fun generateQuestion() {
        // Alternar entre modos
        currentMode = if (questionsAsked % 2 == 0) DECIMAL_TO_BINARY else BINARY_TO_DECIMAL

        if (currentMode == DECIMAL_TO_BINARY) {
            // Generar número decimal entre 1 y 15
            currentNumber = Random.nextInt(1, 16)
            tvQuestion.text = getString(R.string.convert_to_binary, currentNumber)
        } else {
            // Generar número binario entre 1 y 15 (1-1111)
            currentNumber = Random.nextInt(1, 16)
            val binaryString = Integer.toBinaryString(currentNumber)
            tvQuestion.text = getString(R.string.convert_to_decimal, binaryString)
        }

        // Limpiar el campo de respuesta y retroalimentación
        etAnswer.text.clear()
        tvFeedback.visibility = View.INVISIBLE
    }

    private fun checkAnswer() {
        val userAnswer = etAnswer.text.toString().trim()

        if (userAnswer.isEmpty()) {
            Toast.makeText(this, R.string.enter_answer, Toast.LENGTH_SHORT).show()
            return
        }

        var isCorrect = false

        if (currentMode == DECIMAL_TO_BINARY) {
            val correctAnswer = Integer.toBinaryString(currentNumber)
            isCorrect = userAnswer == correctAnswer

            if (isCorrect) {
                tvFeedback.text = getString(R.string.correct_feedback)
                tvFeedback.setTextColor(getColor(R.color.correct_answer))
                score++
            } else {
                tvFeedback.text = getString(R.string.wrong_feedback_binary, correctAnswer)
                tvFeedback.setTextColor(getColor(R.color.wrong_answer))
            }
        } else {
            try {
                val userDecimal = userAnswer.toInt()
                isCorrect = userDecimal == currentNumber

                if (isCorrect) {
                    tvFeedback.text = getString(R.string.correct_feedback)
                    tvFeedback.setTextColor(getColor(R.color.correct_answer))
                    score++
                } else {
                    tvFeedback.text = getString(R.string.wrong_feedback_decimal, currentNumber)
                    tvFeedback.setTextColor(getColor(R.color.wrong_answer))
                }
            } catch (e: NumberFormatException) {
                tvFeedback.text = getString(R.string.invalid_number)
                tvFeedback.setTextColor(getColor(R.color.wrong_answer))
            }
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
            btnSubmit.postDelayed({
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

        btnSubmit.isEnabled = false
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}