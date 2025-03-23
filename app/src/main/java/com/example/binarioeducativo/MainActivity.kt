package com.example.binarioeducativo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar el tema guardado antes de setContentView
        val themeOption = ThemeManager.getThemeOption(this)
        ThemeManager.applyTheme(this, themeOption)

        setContentView(R.layout.activity_main)

        // Configurar botones
        findViewById<Button>(R.id.btnLearning).setOnClickListener {
            startActivity(Intent(this, LearningActivity::class.java))
        }

        findViewById<Button>(R.id.btnGames).setOnClickListener {
            startActivity(Intent(this, GamesActivity::class.java))
        }

        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            showSettingsDialog()
        }
    }

    private fun showSettingsDialog() {
        val dialog = SettingsDialogFragment()
        dialog.show(supportFragmentManager, "SettingsDialog")
    }
}