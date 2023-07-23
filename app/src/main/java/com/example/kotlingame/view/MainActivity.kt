package com.example.kotlingame.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.kotlingame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Listar.setOnClickListener { Toast.makeText(this, "FaltaImplementar", Toast.LENGTH_SHORT).show() }
        binding.btnSalir.setOnClickListener {
            finish()
        }

        binding.btnAdd.setOnClickListener {
            supportFragmentManager.commit {
                replace(
                    binding.frame.id,
                    AddDataFragment()
                )
                addToBackStack(null)
            }
        }

        val showDataFragment = ShowDataFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.frame.id, showDataFragment)
            .commit()


    }
}