package com.diegooliveira.rock_paper_scissors.ui.util

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.diegooliveira.rock_paper_scissors.R
import com.google.android.material.button.MaterialButton

class DialogUtils {
    companion object {
        fun Context.showCustomDialog(
            message: Int,
            button: Int? = null,
            onCloseClickListener: (() -> Unit?)? = null
        ) {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_custom)

            val toolbar = dialog.findViewById<Toolbar>(R.id.dialog_toolbar)
            val messageTextView = dialog.findViewById<AppCompatTextView>(R.id.dialog_message)
            val closeButton = dialog.findViewById<MaterialButton>(R.id.button_dialog_custom)

            messageTextView.text = getString(message)
            button?.let { closeButton.text = getString(it) }

            closeButton.setOnClickListener {
                dialog.dismiss()
                onCloseClickListener?.invoke()
            }

            (this as? AppCompatActivity)?.setSupportActionBar(toolbar)
            (this as? AppCompatActivity)?.setSupportActionBar(toolbar) // Aqui é a mudança
            (this as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { dialog.dismiss() }

            dialog.show()
        }
    }
}