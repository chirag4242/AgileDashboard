package Prog3210ProjectChirag.Prog3210.ChiragFinalProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finalproject.R;

public class AddActivity extends AppCompatActivity {

    EditText input_title, member_input, duration_input;
    Button add_button_input, bak_toList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        input_title = findViewById(R.id.txtTitle);
        member_input = findViewById(R.id.txtWorkAllot);
        duration_input = findViewById(R.id.txtdeadlineDays);
        add_button_input = findViewById(R.id.btnAddData);
        add_button_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper mydb =new MyDatabaseHelper( AddActivity.this);
                mydb.addTask(input_title.getText().toString().trim(),
                        member_input.getText().toString().trim(),
                        Integer.valueOf(duration_input.getText().toString().trim()));
            }
        });
        bak_toList = findViewById(R.id.btnBackToList);
        bak_toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this,ViewDataActivity.class);
                startActivity(intent);
            }
        });
    }
}