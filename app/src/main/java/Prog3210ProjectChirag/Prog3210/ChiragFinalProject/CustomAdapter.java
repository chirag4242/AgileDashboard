package Prog3210ProjectChirag.Prog3210.ChiragFinalProject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList work_id, work_title, work_TeamMember,work_duration;


    CustomAdapter(Context context , ArrayList work_id, ArrayList work_title, ArrayList work_TeamMember, ArrayList work_duration){
        this.context = context;
        this.work_id = work_id;
        this.work_title = work_title;
        this.work_TeamMember = work_TeamMember;
        this.work_duration = work_duration;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.work_id_txt.setText(String.valueOf(work_id.get(position)));
        holder.work_title_txt.setText(String.valueOf(work_title.get(position)));
        holder.work_team_member.setText(String.valueOf(work_TeamMember.get(position)));
        holder.work_duration.setText(String.valueOf(work_duration.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(work_id.get(position)));
                intent.putExtra("title", String.valueOf(work_title.get(position)));
                intent.putExtra("member", String.valueOf(work_TeamMember.get(position)));
                intent.putExtra("workDuration", String.valueOf(work_duration.get(position)));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return work_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView work_id_txt, work_title_txt,work_team_member,work_duration;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            work_id_txt = itemView.findViewById(R.id.work_id);
            work_title_txt = itemView.findViewById(R.id.work_title);
            work_team_member = itemView.findViewById(R.id.team_member_name);
            work_duration = itemView.findViewById(R.id.txtWorkDuration);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
