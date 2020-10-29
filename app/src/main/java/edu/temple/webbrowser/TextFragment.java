package edu.temple.webbrowser;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class TextFragment extends Fragment {

    View l;
    static EditText webaddress;
    ImageButton searchbutton;
    ImageButton previousbutton;
    ImageButton nextbutton;

    ItemPickedInterface parentActivity;


    public TextFragment() {
        // Required empty public constructor
    }

    public static void settingaddress(String s) {
        webaddress.setText(s);
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  ItemPickedInterface)
            parentActivity = (ItemPickedInterface) context;
        else
            throw new RuntimeException("Error: no implementation of interface");
    }


    /*// TODO: Rename and change types and number of parameters
    /public static TextFragment newInstance(String param1, String param2) {
        TextFragment fragment = new TextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    */


   /* public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l =inflater.inflate(R.layout.fragment_text, container, false);
        webaddress = l.findViewById(R.id.webaddress);
        searchbutton = l. findViewById(R.id.searchbutton);
        previousbutton = l.findViewById(R.id.previousbutton);
        nextbutton = l. findViewById(R.id.nextbutton);


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = webaddress.getEditableText().toString();
                parentActivity.itemPicked(s);
            }
        });
        previousbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.previousreturn();
            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.goingforward();
            }
        });
        return l;
    }

    interface ItemPickedInterface{
        void itemPicked(String s);
        void previousreturn();
        void goingforward();
    }

}