package com.example.binarioeducativo.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.binarioeducativo.GamesActivity
import com.example.binarioeducativo.R

class ConversionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_conversion, container, false)

        // Configurar botón para ir a juegos
        view.findViewById<Button>(R.id.btnGoToGames).setOnClickListener {
            startActivity(Intent(requireContext(), GamesActivity::class.java))
        }

        return view
    }
}