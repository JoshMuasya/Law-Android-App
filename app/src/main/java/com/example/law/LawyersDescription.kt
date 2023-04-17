package com.example.law

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.law.databinding.ActivityLawyersDescriptionBinding
import com.google.android.material.appbar.MaterialToolbar


class LawyersDescription : AppCompatActivity() {
    private lateinit var binding: ActivityLawyersDescriptionBinding

    private lateinit var topAppBar : MaterialToolbar

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLawyersDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(LawyerFragment())

        topAppBar = findViewById(R.id.lawyerProfile)

        topAppBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(Home())

                else -> {

                }
            }
            true
        }

        binding.lawyerNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.back -> replaceFragment(LawyerFragment())
                R.id.add -> replaceFragment(AddFragment())
                R.id.delete -> replaceFragment(DeleteFragment())
                R.id.update -> replaceFragment(UpdateFragment())

                else -> {

                }
            }
            true
        }

    }

    private fun replaceFragment(lawyerFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.lawyerFrame_layout, lawyerFragment)
        fragmentTransaction.commit()
    }


}