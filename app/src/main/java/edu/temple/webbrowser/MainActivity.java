package edu.temple.webbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.PersistableBundle;

public class MainActivity extends AppCompatActivity implements TextFragment.ItemPickedInterface, webviewFragment.setaddressInterface  {
    FragmentManager fm;
    webviewFragment webfragment;
    TextFragment browser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            fm = getSupportFragmentManager();
            webfragment = new webviewFragment();
            browser = new TextFragment();
            fm
                    .beginTransaction()
                    .replace(R.id.container_1,browser)
                    .replace(R.id.container_2, webfragment)
                    .commit();
        }
        else{
            browser = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.container_1);
            webfragment = (webviewFragment)getSupportFragmentManager().findFragmentById(R.id.container_2);


           /* browser = (TextFragment) getSupportFragmentManager().getFragment(savedInstanceState, "browser1");
            webfragment = (webviewFragment) getSupportFragmentManager().getFragment(savedInstanceState, "webviewFrag");
        */
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

    }

}