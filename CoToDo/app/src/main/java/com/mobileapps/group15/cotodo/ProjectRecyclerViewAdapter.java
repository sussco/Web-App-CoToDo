package com.mobileapps.group15.cotodo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProjectRecyclerViewAdapter extends
        RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    private final List<Project> mProjects;

    public ProjectRecyclerViewAdapter(List<Project> projects) {
        mProjects = projects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_project,
                             parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Project project = mProjects.get(position);

        holder.title.setText(project.getTitle());
        holder.description.setText(project.getDescription());
        holder.owner.setText(project.getOwner());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount(){
        return mProjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView description;
        public final TextView owner;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            owner = view.findViewById(R.id.owner);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + description.getText() + "'";
        }
    }
}
