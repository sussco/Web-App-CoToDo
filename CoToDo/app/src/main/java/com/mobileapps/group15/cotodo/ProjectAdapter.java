package com.mobileapps.group15.cotodo;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProjectAdapter extends RecyclerView.Adapter {


    Context context;


    public ProjectAdapter(Context context) {
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // set the data in items
        Iterator it = MainActivity.projects.iterator();
        List<String> list = new ArrayList<String>();
        while(it.hasNext()){
            Project p = (Project)it.next();
            list.add(p.getTitle());
        }
        ((MyViewHolder)holder).name.setText( list.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProjectActivity.class);
                intent.putExtra("id", position );
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return MainActivity.projects.size();
    }

}
