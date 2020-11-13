package edu.temple.webbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextFragment.ItemPickedInterface, webviewFragment.setaddressInterface,BrowserControlFragment.addingfragment {
    FragmentManager fm;
    webviewFragment webfragment;
    TextFragment browser;
    BrowserControlFragment controlFragment;
    PageListFragment listfragment;

    /*ViewPager viewPager;
    ArrayList<webviewFragment> fragments;
    */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientation = this.getResources().getConfiguration().orientation;



           /* findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragments.add(new webviewFragment());
                    viewPager.getAdapter().notifyDataSetChanged();;
                }
            });

            */
        //listview = findViewById(R.id.listview);


        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            if (savedInstanceState == null) {
                fm = getSupportFragmentManager();
                webfragment = new webviewFragment();
                browser = new TextFragment();
                controlFragment = new BrowserControlFragment();
                //listfragment = new PageListFragment();
                fm
                        .beginTransaction()
                        .replace(R.id.container_1, browser)
                        .replace(R.id.container_2, webfragment)
                        .replace(R.id.container_3, controlFragment)
                        //.replace(R.id.container_4, listfragment)
                        .commit();
            } else {
                browser = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.container_1);
                webfragment = (webviewFragment) getSupportFragmentManager().findFragmentById(R.id.container_2);
                controlFragment = (BrowserControlFragment) getSupportFragmentManager().findFragmentById(R.id.container_3);
                //listfragment = (PageListFragment) getSupportFragmentManager().findFragmentById(R.id.container_4);


           /* browser = (TextFragment) getSupportFragmentManager().getFragment(savedInstanceState, "browser1");
            webfragment = (webviewFragment) getSupportFragmentManager().getFragment(savedInstanceState, "webviewFrag");
        */
            }

        }

    else

    {
        if (savedInstanceState == null) {
            fm = getSupportFragmentManager();
            webfragment = new webviewFragment();
            browser = new TextFragment();
            controlFragment = new BrowserControlFragment();
            listfragment = new PageListFragment();
            fm
                    .beginTransaction()
                    .replace(R.id.container_1, browser)
                    .replace(R.id.container_2, webfragment)
                    .replace(R.id.container_3, controlFragment)
                    .replace(R.id.container_4, listfragment)
                    .commit();
        } else {
            browser = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.container_1);
            webfragment = (webviewFragment) getSupportFragmentManager().findFragmentById(R.id.container_2);
            controlFragment = (BrowserControlFragment) getSupportFragmentManager().findFragmentById(R.id.container_3);
            listfragment = (PageListFragment) getSupportFragmentManager().findFragmentById(R.id.container_4);


        }
    }


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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getSupportFragmentManager().putFragment(outState, "browser1", browser);
        getSupportFragmentManager().putFragment(outState, "webviewFrag", webfragment);
        getSupportFragmentManager().putFragment(outState, "controlfrag", controlFragment);
        getSupportFragmentManager().putFragment(outState,"listfrag",listfragment);


    }

    @Override
    public void addingfragment() {
        webfragment.addfragment();
    }
}