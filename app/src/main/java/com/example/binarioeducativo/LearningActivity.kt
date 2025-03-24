package com.example.binarioeducativo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.binarioeducativo.adapters.LearningPagerAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LearningActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplicar el tema guardado antes de setContentView
        val themeOption = ThemeManager.getThemeOption(this)
        ThemeManager.applyTheme(this, themeOption)

        setContentView(R.layout.activity_learning)

        // Configurar toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Configurar ViewPager y TabLayout
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // Configurar adapter
        val pagerAdapter = LearningPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        // Conectar TabLayout con ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_binary_system)
                1 -> getString(R.string.tab_conversions)
                else -> ""
            }
        }.attach()
    }
}