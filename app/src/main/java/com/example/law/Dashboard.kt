package com.example.law

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.law.databinding.ActivityDashboardBinding
import com.google.android.material.appbar.MaterialToolbar

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    private lateinit var topAppBar : MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        topAppBar = findViewById(R.id.profile)

        topAppBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.profile -> replaceFragment(RegisterFragment())
                R.id.calendar -> replaceFragment(Calendar())

                else -> {

                }
            }
            true
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.lawyers -> replaceFragment(Lawyers())
                R.id.clients -> replaceFragment(Clients())
                R.id.cases -> replaceFragment(Cases())
                R.id.finances -> replaceFragment(Finances())

                else -> {

                }
            }

            true

        }
    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}