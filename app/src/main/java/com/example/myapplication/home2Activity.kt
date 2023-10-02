package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.content.Context
import android.view.View
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityHome2Binding
import java.util.Locale

class home2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHome2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val change=intent.getStringExtra("key_name")

        if(change!=null){
            val cur=getCurrentLocaleLanguage(this)
            if (cur == "en") {
                val hindiLocale = Locale("hi")
                Locale.setDefault(hindiLocale)

                val config = Configuration()
                config.locale = hindiLocale
                resources.updateConfiguration(config, resources.displayMetrics)
            } else {
                val englishLocale = Locale("en")
                Locale.setDefault(englishLocale)

                val config = Configuration()
                config.locale = englishLocale
                resources.updateConfiguration(config, resources.displayMetrics)
            }

        }

        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.home ->replaceFragment(Home())
                R.id.disease_detection ->replaceFragment(DiseaseDetection())
                R.id.profile->replaceFragment(Profile())

                else->{


                }
            }
            true
        }
    }
    fun getCurrentLocaleLanguage(context: Context): String {
        val resources = context.resources
        val configuration: Configuration = resources.configuration
        val locale: Locale = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            configuration.locales[0]
        } else {
            configuration.locale
        }
        return locale.language // Returns the current language code (e.g., "en" for English)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you really want to close this app?")
            .setPositiveButton("Yes") { _, _ ->
                // Close the app
                finishAffinity()
            }
            .setNegativeButton("No") { _, _ ->
                // Do nothing, continue with the app
            }
            .show()
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager =supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

}