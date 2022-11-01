package com.project.notesapp.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.project.notesapp.R
import com.project.notesapp.model.Note
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener: NotesClickListener) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NoteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NoteList[position]
        holder.title.text = currentNote.tittle
        holder.title.isSelected = true
        holder.date.text = currentNote.date
        holder.date.isSelected = true
        holder.note.text = currentNote.note
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.notes_layout.setCardBackgroundColor(
                holder.itemView.resources.getColor(
                    RandomColor(),
                    null
                )
            )
        }

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(NoteList[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(NoteList[holder.adapterPosition], holder.notes_layout)
            true

        }
    }

    override fun getItemCount(): Int {
        return NoteList.size
    }

    fun updateList(newList: List<Note>) {
        fullList.clear()
        fullList.addAll(newList)

        NoteList.clear()
        NoteList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search: String) {
        NoteList.clear()
        for (item in fullList) {
            if (item.tittle?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true
            ) {
                NoteList.add(item)
            }
        }

        notifyDataSetChanged()
    }

    fun RandomColor(): Int {
        val list = ArrayList<Int>()
        list.add(R.color.note_color1)
        list.add(R.color.note_color2)
        list.add(R.color.note_color3)
        list.add(R.color.note_color4)
        list.add(R.color.note_color5)
        list.add(R.color.note_color6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_tittle)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

    interface NotesClickListener {
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }


}
