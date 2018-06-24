package com.example.shimul.gdictionary;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.HashMap;

import java.util.Map;

import java.util.Scanner;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;

public class MainActivity extends SimpleActivity {

    private Map<String,String> dictionary;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        dictionary=new HashMap<>();

        Scanner  scanner= new Scanner(getResources().openRawResource(R.raw.dictionary));

        while (scanner.hasNextLine())
        {

            String line=scanner.nextLine();
            String[]parts=line.split("\t");
            dictionary.put(parts[0],parts[1]);

        }

        Spinner listView=(Spinner) findViewById(R.id.spinner);
        SimpleList.with(this).setItems(listView,dictionary.keySet());

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String word=parent.getItemAtPosition(position).toString();
                String defination=dictionary.get(word);
                toast(defination);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
