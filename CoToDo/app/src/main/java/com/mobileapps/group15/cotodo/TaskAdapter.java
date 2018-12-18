package com.mobileapps.group15.cotodo;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.IconCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mobileapps.group15.cotodo.R.*;


public class TaskAdapter extends RecyclerView.Adapter {


    Context context;
    Project project;
    int projectId;

    TextView initials;
    TextView taskName;
    ImageView checkBox;

    public TaskAdapter(Context context, Project project, int projectId) {
        this.project = project;
        this.context = context;
        this.projectId = projectId;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(layout.task_item_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // set the data in items
       // holder.name.setText(personNames.get(position));
        //holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.
        ((MyViewHolder) holder).taskName.setText(project.getTasks().get(position).getName());
        boolean isCompleted = project.getTasks().get(position).isCompleted();
        if(!isCompleted){
            //drawable = AppCompatResources.getDrawable(context, R.id.checkbox);

            ((MyViewHolder) holder).checkBox.setImageResource(mipmap.empty);
        }
        else {
            ((MyViewHolder) holder).checkBox.setImageResource(mipmap.baseline_done_black_18dp);
        }
        if(!project.getTasks().get(position).getMembers().isEmpty()){
            ((MyViewHolder) holder).initials.setText(project.getTasks().get(position).getLastMember().getFirstName().toUpperCase().substring(0,1)
                    + project.getTasks().get(position).getLastMember().getLastName().toUpperCase().substring(0,1)
                    + ((project.getTasks().get(position).getMembers().size() > 1)? "+" : ""));
        }
        ((MyViewHolder) holder).initials.setTextColor(ContextCompat.getColor(context, R.color.black));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaskActivity.class);
                intent.putExtra("idProject", projectId);
                intent.putExtra("idTask", position);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return project.getTasks().size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView initials;
        TextView taskName;
        ImageView checkBox;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            initials =  itemView.findViewById(id.initials);
            taskName =  itemView.findViewById(id.taskName);
            checkBox =  itemView.findViewById(id.checkbox);
        }
    }
}