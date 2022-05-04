package Prog3210ProjectChirag.Prog3210.ChiragFinalProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements Adapter.OnActivityListener {

    RecyclerView dataLists;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;
    public static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataLists = findViewById(R.id.dataList);
        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Home");
        titles.add("View");
        titles.add("About");
        titles.add("Logout");

        images.add(R.drawable.ic_baseline_home_24);
        images.add(R.drawable.ic_baseline_preview_24);
        images.add(R.drawable.ic_baseline_speaker_notes_24);
        images.add(R.drawable.logout);

        adapter = new Adapter(this,titles,images,this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        dataLists.setLayoutManager(gridLayoutManager);
        dataLists.setAdapter(adapter);

    }


    @Override
    public void OnItemClicked(int position) {

        switch(position){
            case 0:
              startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Log.d(TAG,"Position: " + position);
                break;
            case 1:
                startActivity(new Intent(MainActivity.this,ViewDataActivity.class));
                Log.d(TAG,"Position: " + position);
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(),About_Activity.class));
                Log.d(TAG,"Position: " + position);
                break;

            case 3:
                Log.d(TAG,"Position: " + position);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                break;
        }
    }
}