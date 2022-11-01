package com.project.notesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.notesapp.databinding.ActivityAddNoteBinding
import com.project.notesapp.model.Note
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note :Note
    private lateinit var old_Note :Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_Note = intent.getSerializableExtra("current_note") as Note
            binding.edtTittle.setText(old_Note.tittle)
            binding.edtDates.setText(old_Note.note)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.btnCheck.setOnClickListener {
            val tittle = binding.edtTittle.text.toString()
            val note_desc = binding.edtDates.text.toString()

            if (tittle.isNotEmpty() || note_desc.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE,d MMM yyyy HH:mm a")

                if (isUpdate) {
                    note = Note(
                        old_Note.id, tittle, note_desc, formatter.format(Date())

                    )
                } else {
                    note = Note(
                        null, tittle, note_desc, formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {

            }
        }
    }
}