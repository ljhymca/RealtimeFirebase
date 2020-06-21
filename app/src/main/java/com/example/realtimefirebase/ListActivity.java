package com.example.realtimefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton addFloatingActionButton;
    MainArrayAdapter adapter;

    DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference fruitsReference = rootReference.child("fruits");

    List<String> fruits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.main_listView);
        addFloatingActionButton = findViewById(R.id.main_add_floatingButtion);
        adapter = new MainArrayAdapter(this, fruits);

       listView.setAdapter(adapter);

       addFloatingActionButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent intent = new Intent(ListActivity.this, RangeActivity.class);
               startActivity(intent);
           }
       });
    }
    @Override
    protected void onStart(){
        super.onStart();

        fruitsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fruits.clear();

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    fruits.add(childSnapshot.getValue(String.class));
                }

                adapter.nofifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}