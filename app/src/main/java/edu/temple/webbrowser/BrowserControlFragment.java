package edu.temple.webbrowser;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.Browser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrowserControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrowserControlFragment extends Fragment {

    View l;
    ImageButton addingbutton;
    ArrayList<webviewFragment> fragments;

    BrowserControlFragment.addingfragment parentActivity;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l= inflater.inflate(R.layout.fragment_browser_control, container, false);
        addingbutton = l.findViewById(R.id.imageButton);
        addingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.addingfragment();
            }
        });

        return l;
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BrowserControlFragment.addingfragment)
            parentActivity = (BrowserControlFragment.addingfragment) context;
        else
            throw new RuntimeException("Error: no implementation of interface");
    }

    interface addingfragment{
        void addingfragment();
    }
}