package com.khirao.ocr_canvas_sample_app

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_layout.view.*
import java.io.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val CANVAS_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        canvasButton.setOnClickListener {
            val intent = Intent(this, CanvasActivity::class.java)
            startActivityForResult(intent, CANVAS_CODE)
        }

        ocrButton.setOnClickListener {
            val intent = Intent(this, OcrActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == CANVAS_CODE) {
            val alertDialog = AlertDialog.Builder(this).apply {
                val file = File(externalCacheDir, "sample_canvas_layout.png")
                val bitmap = BitmapFactory.decodeFile(file.path)
                val view = View.inflate(this@MainActivity, R.layout.dialog_layout, null)
                view.image.setImageBitmap(bitmap)
                setView(view)
                setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            }.create()
            alertDialog.show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
