package Prog3210ProjectChirag.Prog3210.ChiragFinalProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, member_input,workDuration_input;
    Button update_button;
    String id, title, member,duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.txtTitle2);
        member_input = findViewById(R.id.txtWorkAllot2);
        workDuration_input = findViewById(R.id.txtdeadlineDays2);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getIntentData();
    }
    void getIntentData(){
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("title")
                && getIntent().hasExtra("member") &&
                    getIntent().hasExtra("workDuration")){
                id= getIntent().getStringExtra("id");
                title= getIntent().getStringExtra("title");
            member= getIntent().getStringExtra("member");
            duration= getIntent().getStringExtra("workDuration");
            title_input.setText(title);
            member_input.setText(member);
            workDuration_input.setText(duration);
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}