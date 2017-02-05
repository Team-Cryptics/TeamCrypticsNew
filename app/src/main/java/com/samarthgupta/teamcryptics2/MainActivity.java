package com.samarthgupta.teamcryptics2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference();
        ImageButton ib1;
        EditText et1;
        ListView lv1;
        ArrayList<String> myList;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        lv1 = (ListView)findViewById(R.id.listView);
        et1=(EditText)findViewById(R.id.editText);
        ib1=(ImageButton)findViewById(R.id.imageButton);
        myList = new ArrayList<>();

            ib1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String t=et1.getText().toString();
                    getList(t);
                    ArrayAdapter<String> arrayAdapter =
                            new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, myList);
                    lv1.setAdapter(arrayAdapter);
                    et1.setText("");
                }
            });

    }


    public void getList(final String where) {

        myList.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.child(where).getChildren()) {
                    myList.add(dsp.getKey().toString());
                    Log.i("TAG", dsp.getKey().toString());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}




