package Prog3210ProjectChirag.Prog3210.ChiragFinalProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_Button;
    MyDatabaseHelper myDB;
    ArrayList<String> work_id, work_title, team_member, Work_Duration;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        recyclerView = findViewById(R.id.recyclerList);
        add_Button= findViewById(R.id.add_button);
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewDataActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        myDB =  new MyDatabaseHelper(ViewDataActivity.this);
        work_id = new ArrayList<>();
        work_title = new ArrayList<>();
        team_member = new ArrayList<>();
        Work_Duration = new ArrayList<>();

        StoredDataInArrays();
        customAdapter = new CustomAdapter(ViewDataActivity.this,work_id,work_title,team_member,Work_Duration)   ;
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewDataActivity.this ));
    }
    void StoredDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                work_id.add(cursor.getString(0));
                work_title.add(cursor.getString(1));
                team_member.add(cursor.getString(2));
                Work_Duration.add(cursor.getString(3));
            }
        }
    }
}