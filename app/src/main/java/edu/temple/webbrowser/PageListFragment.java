package edu.temple.webbrowser;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class PageListFragment extends Fragment {

        ListView listview;
        View l;
        ArrayList<String> title;
        static BaseAdapter adapter;
        PageListFragment.notifychange parentActivity;

    public PageListFragment(){

    }



    public static PageListFragment newInstance(ArrayList<String> title) {
        
        Bundle args = new Bundle();
        args.putStringArrayList("title",title);
        PageListFragment fragment = new PageListFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        l = inflater.inflate(R.layout.fragment_page_list, container, false);
        listview = l.findViewById(R.id.listView);
        //ArrayAdapter itemsAdapter;
        //itemsAdapter = new ArrayAdapter<String>(this,R.layout.fragment_page_list,title);
        title = getArguments().getStringArrayList("title");
        adapter = new PageListAdapter(getContext().getApplicationContext(),title);
       listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               parentActivity.fragmentonclick(position);
            }
        });



        return l;
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PageListFragment.notifychange)
            parentActivity = (PageListFragment.notifychange) context;
        else
            throw new RuntimeException("Error: no implementation of interface");
    }

    interface notifychange{
        void notifychangetitle();
        void fragmentonclick(int position);
    }
    static void changeatitle(){
        adapter.notifyDataSetChanged();
    }
}