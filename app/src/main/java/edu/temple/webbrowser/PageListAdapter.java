package edu.temple.webbrowser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PageListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> title;
    public PageListAdapter(Context context, ArrayList<String> title){
        this.context = context;
        this.title = title;
    }
    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return title.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textview;
        if(view==null){
            textview = new TextView(context);
            textview.setTextSize(12);
            textview.setPadding(10,10,10,10);
        }
        else
            textview = (TextView)view;

        textview.setText(getItem(i).toString());

        return textview;
    }
    public View getDroppedDownView(int i, View view,ViewGroup viewGroup){
        View v = getView(i,view,viewGroup);
        return v;
    }
}
