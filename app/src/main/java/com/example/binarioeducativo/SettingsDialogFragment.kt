package com.example.binarioeducativo

import android.app.Dialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.appcompat.app.AppCompatActivity

class SettingsDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_settings, null)

        val radioGroup = view.findViewById<RadioGroup>(R.id.rgThemes)
        val rbGuinda = view.findViewById<RadioButton>(R.id.rbGuinda)
        val rbAzul = view.findViewById<RadioButton>(R.id.rbAzul)

        // Establecer la selección actual
        val currentTheme = ThemeManager.getThemeOption(requireContext())
        when (currentTheme) {
            ThemeManager.THEME_GUINDA -> rbGuinda.isChecked = true
            ThemeManager.THEME_AZUL -> rbAzul.isChecked = true
        }

        builder.setView(view)
            .setTitle(R.string.settings_title)
            .setPositiveButton(R.string.btn_apply) { _, _ ->
                val selectedId = radioGroup.checkedRadioButtonId
                val themeOption = when (selectedId) {
                    R.id.rbGuinda -> ThemeManager.THEME_GUINDA
                    R.id.rbAzul -> ThemeManager.THEME_AZUL
                    else -> ThemeManager.THEME_GUINDA
                }

                // Aplicar y guardar el tema
                ThemeManager.setTheme(requireActivity() as AppCompatActivity, themeOption)

                // Recrear la actividad para aplicar el tema
                requireActivity().recreate()
            }
            .setNegativeButton(R.string.btn_cancel) { dialog, _ ->
                dialog.cancel()
            }

        return builder.create()
    }
}