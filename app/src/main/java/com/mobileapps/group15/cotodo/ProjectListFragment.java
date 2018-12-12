package com.mobileapps.group15.cotodo;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class ProjectListFragment extends ListFragment implements AdapterView.OnItemClickListener {



    public  ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        return view;
    }

    public ArrayAdapter transformProjectList(){
        Iterator it = MainActivity.projects.iterator();
        List<String> list = new ArrayList<String>();
        list.add("Refresh");
        while(it.hasNext()){
            Project p = (Project)it.next();
            list.add(p.getTitle());
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        return adapter;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = transformProjectList();
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        if(position == 0) {
            ArrayAdapter adapter = transformProjectList();
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);
        }
        else{
            Intent intent = new Intent(getActivity(), ProjectActivity.class);
            intent.putExtra("id", position - 1);
            startActivity(intent);
        }
    }
}
