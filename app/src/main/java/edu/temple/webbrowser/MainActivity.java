package edu.temple.webbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TextFragment.ItemPickedInterface, webviewFragment.setaddressInterface  {
    FragmentManager fm;
    webviewFragment webfragment;
    TextFragment browser;
    boolean twoPlane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twoPlane = findViewById(R.id.container_2)!= null;
        fm = getSupportFragmentManager();
        webfragment = new webviewFragment();
        browser = new TextFragment();
        fm
                .beginTransaction()
                .add(R.id.container_1,browser)
                .add(R.id.container_2,webfragment)
                .commit();
    }

    @Override
    public void itemPicked(String s) {
        webfragment.performURL(s);
    }
    public void previousreturn(){
        webfragment.goback();
    }
    public void goingforward(){
        webfragment.goforward();
    }

    @Override
    public void setaddress(String s) {
        TextFragment.settingaddress(s);
    }
}