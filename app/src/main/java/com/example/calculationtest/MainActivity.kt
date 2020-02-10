package com.example.calculationtest

import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_POSITIVE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (controller.currentDestination!!.id == R.id.questionFragment) {
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.quit_dialog_title))

//            builder.setPositiveButton(
//                R.string.dialog_positive_message,
//                DialogInterface.OnClickListener { dialog, which -> controller.navigateUp() })

            builder.setPositiveButton(
                R.string.dialog_positive_message,
                DialogInterface.OnClickListener { _, _ -> controller.navigateUp() })

            builder.setNegativeButton(
                R.string.dialog_negative_message,
                DialogInterface.OnClickListener { _, _ ->  })

            var dialog: AlertDialog = builder.create()
            dialog.show()
        } else {
            controller.navigate(R.id.titleFragment)
        }

        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        onSupportNavigateUp()
    }

}
