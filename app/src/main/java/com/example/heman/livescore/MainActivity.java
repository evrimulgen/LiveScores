package com.example.heman.livescore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference root= FirebaseDatabase.getInstance().getReference();
    DatabaseReference child=root.child("TotalRuns");
    DatabaseReference details=child.child("Details");


    //DatabaseReference child=root.child("TotalRuns");
    TextView runsTx,oversTx,runRateTx,commentsTx;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // String runs= String.valueOf(child.getKey());
        runsTx=findViewById(R.id.textView11);
        oversTx=findViewById(R.id.textView13);
        runRateTx=findViewById(R.id.textView5);
        commentsTx=findViewById(R.id.textView6);
        //tx.setText(runs);

        /*root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot details : dataSnapshot.getChildren()) {

                    list.add(dataSnapshot.getValue(String.class));
                    adapter.notifyDataSetChanged();
                    Log.d("User Key",child.getKey());
                    Log.d("User ref",child.getRef().toString());
                    Log.d("User val", (String) details.getValue());
                    String run= details.getValue(String.class);
                    tx.setText(run + "Hi");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        details.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long runs = dataSnapshot.child("runs").getValue(Long.class);
                Long wickets=dataSnapshot.child("wickets").getValue(Long.class);
                Long overs=dataSnapshot.child("overs").getValue(Long.class);
                Long balls=dataSnapshot.child("balls").getValue(Long.class);
                Double runRate=dataSnapshot.child("runrate").getValue(Double.class);
                runRate=Math.round(runRate * 100.0) / 100.0;
                String.format("%.2f", runRate);
                String comments=dataSnapshot.child("comments").getValue(String.class);
                runsTx.setText(runs + "/" + wickets);
                Long ballsMod=balls%6;
                oversTx.setText(overs + "." + ballsMod);
                runRateTx.setText(runRate + "");
                commentsTx.setText(comments);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}