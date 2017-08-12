package com.example.mikehhsu.simplenotepad.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mikehhsu.simplenotepad.R;

import java.util.ArrayList;

/**
 * Created by mikehhsu on 8/7/17.
 */

public class ListNotesAdapter extends RecyclerView.Adapter {

    private ArrayList<String> notes;

    public ListNotesAdapter() {
        this.notes = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout itemView = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notes_list, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NoteViewHolder viewHolder = (NoteViewHolder)holder;
        viewHolder.noteTextView.setText(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView noteTextView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            noteTextView = (TextView) itemView.findViewById(R.id.noteTextView);
        }
    }

    public ArrayList<String> getNotes() {
        return notes;
    }
}
