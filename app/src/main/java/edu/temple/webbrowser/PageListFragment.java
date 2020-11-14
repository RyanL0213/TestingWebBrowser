package edu.temple.webbrowser;

import android.R.id;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.text1;

public class PageListFragment extends Fragment {

        ListView listview;
        View l;
        ArrayList<String> title;
        static BaseAdapter adapter;



    public PageListFragment(ArrayList<String> title) {
        this.title = title;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        l = inflater.inflate(R.layout.fragment_page_list, container, false);
        listview = l.findViewById(R.id.listView);
        //ArrayAdapter itemsAdapter;
        //itemsAdapter = new ArrayAdapter<String>(this,R.layout.fragment_page_list,title);
        adapter = new PageListAdapter(getContext().getApplicationContext(),title);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.d("haha","I got clicked");
            }
        });



        return l;
    }

    interface notifychange{
        void notifychangetitle();
    }
    static void changeatitle(){
        adapter.notifyDataSetChanged();
    }
}