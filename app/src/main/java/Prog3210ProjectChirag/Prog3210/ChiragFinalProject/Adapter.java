package Prog3210ProjectChirag.Prog3210.ChiragFinalProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
 List<String> title;
 List<Integer> images;
private  OnActivityListener aOnActivityListener;
 LayoutInflater inflater;

 public Adapter(Context ctx,List<String> titles,List<Integer> images,OnActivityListener onActivityListener){
this.title = titles;
this.images = images;
this.inflater = LayoutInflater.from(ctx);
this.aOnActivityListener = onActivityListener;
 }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
     return new ViewHolder(view,aOnActivityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.VHtitles.setText(title.get(position));
            holder.gridIcon.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView VHtitles;
        ImageView gridIcon;
        OnActivityListener onActivityListener;

        public ViewHolder(@NonNull View itemView, OnActivityListener onActivityListener ) {
            super(itemView);
            VHtitles = itemView.findViewById(R.id.gridText);
            gridIcon = itemView.findViewById(R.id.imageForGridLayout);
            this.onActivityListener = onActivityListener;

           itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onActivityListener.OnItemClicked(getAdapterPosition());
        }
    }
    public interface OnActivityListener{
        void OnItemClicked(int position);
    }
}
