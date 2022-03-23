package ru.startandroid.develop.theroapp;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";
    Button btnTop;
    Button btnBottom;
    EditText editTextTextPassword2;
    EditText emailAddress;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAddress = (EditText) findViewById(R.id.myMail);

        btnTop = (Button) findViewById(R.id.btnTop);
        btnTop.setOnClickListener(this);

        btnBottom = (Button) findViewById(R.id.btnBottom);
        btnBottom.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTop:
                saveText();
                break;
            case R.id.btnBottom:
                loadText();
                break;
            default:
                break;
        }
    }
    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, emailAddress.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        emailAddress.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}