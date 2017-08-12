package com.example.mikehhsu.simplenotepad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mikehhsu.simplenotepad.adapters.ListNotesAdapter;
import com.example.mikehhsu.simplenotepad.fragments.ListScrollFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextEnterNote;
    Button buttonSend;

    ListScrollFragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, getFragmentForActivity(), null)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        editTextEnterNote = (EditText) findViewById(R.id.editext_enter_note);
        buttonSend = (Button) findViewById(R.id.btn_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable noteText = editTextEnterNote.getEditableText();
                ((ListNotesAdapter)fragment.getAdapter()).getNotes().add(noteText.toString());
                ((ListNotesAdapter)fragment.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    private ListScrollFragment getFragmentForActivity(){
        this.fragment = ListScrollFragment.newInstance();
        return this.fragment;
    }
}
