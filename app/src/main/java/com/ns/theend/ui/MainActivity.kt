package com.ns.theend.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.ns.theend.R
import com.ns.theend.databinding.ActivityMainBinding
import com.ns.theend.utils.makeVisibilityGone
import com.ns.theend.utils.makeVisible
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        checkView()
        initOnClick()
    }

    private fun initOnClick() {
        binding.bottomNavBar.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.navHostFragment
            )
        )
    }

    private fun checkView() {

        when (Navigation.findNavController(this, R.id.navHostFragment).currentDestination?.id) {
            R.id.loginFragment -> {

                binding.apply {
                    bottomNavBar.makeVisibilityGone()
                    toolbar.makeVisibilityGone()
                }
            }
            R.id.registerFragment -> {

                binding.apply {
                    binding.bottomNavBar.makeVisibilityGone()
                    binding.toolbar.makeVisibilityGone()
                }
            }
            R.id.mainFragment -> {

                binding.apply {
                    binding.bottomNavBar.makeVisible()
                    binding.toolbar.makeVisible()
                }

            }
            R.id.favoriteFragment -> {

                binding.apply {
                    bottomNavBar.makeVisible()
                    toolbar.makeVisible()

                }
            }
        }
    }


}