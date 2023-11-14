package com.example.ex8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    /**
     * @author		ziv ankri address: za6200@bs.amalnet.k12.il
     * @version	    2022.3.1
     * @since       25/10/2023
     * class will make 3 buttons one saving data second reset data and third exit and save data
     */
    TextView textView;
    EditText editText;
    Button save;
    Button reset;
    Button exit;
    static final String FILENAME = "test.txt";
    String userInputText = "";
    Intent credits;
    BufferedWriter bW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        save = findViewById(R.id.save);
        reset = findViewById(R.id.reset);
        exit = findViewById(R.id.exit);
        credits = new Intent(this, credits.class);

        try {
            FileInputStream fIS = openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line);
                line = bR.readLine();
            }
            bR.close();
            textView.setText(sB.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * function will make the option menu
         * param menu: the menu
         */
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("credit")) {
            try {
                startActivity(credits);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void save(View view) {
        userInputText = readFromFile();
        userInputText += editText.getText().toString();
        try {
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            bW = new BufferedWriter(oSW);
            bW.write(userInputText);
            bW.close();
            oSW.close();
            fOS.close();
            textView.setText(userInputText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset(View view) {
        try {
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            bW = new BufferedWriter(oSW);
            if(bW != null) {
                userInputText = "";
                readFromFile();
                editText.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void exit(View view) {
        userInputText = readFromFile();
        userInputText += editText.getText().toString();
        try {
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write(userInputText);
            bW.close();
            oSW.close();
            fOS.close();
            textView.setText(userInputText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    private String readFromFile() {
        try {
            FileInputStream fIS = openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line);
                line = bR.readLine();
            }
            bR.close();
            textView.setText(sB.toString());
            return sB.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
