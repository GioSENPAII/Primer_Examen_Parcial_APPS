package com.example.binarioeducativo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.binarioeducativo.games.BinaryConverterGameActivity
import com.example.binarioeducativo.games.BinarySwitchGameActivity
import com.google.android.material.appbar.MaterialToolbar

class GamesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar el tema guardado antes de setContentView
        val themeOption = ThemeManager.getThemeOption(this)
        ThemeManager.applyTheme(this, themeOption)

        setContentView(R.layout.activity_games)

        // Configurar toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Configurar botones de juegos
        findViewById<Button>(R.id.btnPlayGame1).setOnClickListener {
            startActivity(Intent(this, BinaryConverterGameActivity::class.java))
        }

        findViewById<Button>(R.id.btnPlayGame2).setOnClickListener {
            startActivity(Intent(this, BinarySwitchGameActivity::class.java))
        }
    }
}