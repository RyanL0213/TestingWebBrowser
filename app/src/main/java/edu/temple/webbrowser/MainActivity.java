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
import android.util.Log;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextFragment.ItemPickedInterface, webviewFragment.setaddressInterface,BrowserControlFragment.addingfragment,PageListFragment.notifychange {
    FragmentManager fm;
    webviewFragment webfragment = new webviewFragment();
    TextFragment browser;
    BrowserControlFragment controlFragment;
    PageListFragment listfragment;
    ArrayList<Fragment> arrayList;
    ViewPager viewPager;
    int positioner = -1;
    webviewFragment instance;
    FragmentStatePagerAdapter adapter;
    ArrayList<String> address;
    ArrayList<String> title;
    BaseAdapter pagelistadapter;
    /*ViewPager viewPager;
    ArrayList<webviewFragment> fragments;
    */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        address = new ArrayList<>();
        viewPager = (ViewPager)findViewById(R.id.viewPage);
        viewPager.setOnPageChangeListener(new MyPagerChangeListener()) ;
        arrayList = new ArrayList<Fragment>();
        arrayList.add(webfragment);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), arrayList);
        viewPager.setAdapter(adapter);
        pagelistadapter = new PageListAdapter(this,title);
        address.add("");
        title = new ArrayList<String>();
        title.add("");



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
                //webfragment = new webviewFragment();
                browser = new TextFragment();
                controlFragment = new BrowserControlFragment();
                //listfragment = new PageListFragment();
                fm
                        .beginTransaction()
                        .replace(R.id.container_1, browser)
                        //.replace(R.id.viewPage, webfragment)
                        .replace(R.id.container_3, controlFragment)
                        //.replace(R.id.container_4, listfragment)
                        .commit();
            } else {
                browser = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.container_1);
               webfragment = (webviewFragment) getSupportFragmentManager().findFragmentById(R.id.viewPage);
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
            //webfragment = new webviewFragment();
            browser = new TextFragment();
            controlFragment = new BrowserControlFragment();
            listfragment = new PageListFragment(title);
            fm
                    .beginTransaction()
                    .replace(R.id.container_1, browser)
                    //.replace(R.id.viewPage, webfragment)
                    .replace(R.id.container_3, controlFragment)
                    .replace(R.id.container_4, listfragment)
                    .commit();
        } else {
            browser = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.container_1);
            webfragment = (webviewFragment) getSupportFragmentManager().findFragmentById(R.id.viewPage);
            controlFragment = (BrowserControlFragment) getSupportFragmentManager().findFragmentById(R.id.container_3);
            listfragment = (PageListFragment) getSupportFragmentManager().findFragmentById(R.id.container_4);


        }


    }


}

    @Override
    public void itemPicked(String s) {
        webfragment = (webviewFragment) adapter.getItem(viewPager.getCurrentItem());
        webfragment.performURL(s);
        for(int i =0;i<=arrayList.size()-1;i++){
            if(address.get(i)==""){
                address.set(i,"");
            }
            if(i==positioner){
                address.set(i,s);
            }
        }

    }
    public void previousreturn(){
        webfragment = (webviewFragment) adapter.getItem(viewPager.getCurrentItem());
        webfragment.goback();
    }
    public void goingforward(){

        webfragment = (webviewFragment) adapter.getItem(viewPager.getCurrentItem());
        webfragment.goforward();
    }

    @Override
    public void setaddress(String s) {
        TextFragment.settingaddress(s);
    }

    @Override
    public void updatetitle(String s) {
        for(int i=0;i<=arrayList.size()-1;i++){
            if(title.get(i)==""){
                title.set(i,"No title specify");
                notifychangetitle();
            }
            if(i==positioner){
                title.set(i,s);
                notifychangetitle();
            }
        }
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
        Log.d("Positioner", "current arraylist length  = " + arrayList.size());


        arrayList.add(new webviewFragment());
        viewPager.getAdapter().notifyDataSetChanged();
        address.add("");
        title.add("");
    }

    @Override
    public void notifychangetitle() {
        PageListFragment.changeatitle();
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        private ArrayList<Fragment> marrayList;
        public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> arrayList)
        {
            super(fm);
            marrayList = arrayList;
        }


        @Override
        public Fragment getItem(int position)
        {
            return marrayList.get(position);
        }

        @Override
        public int getCount()
        {
            return marrayList.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            if (arrayList.contains(object))
                return arrayList.indexOf(object);
            else
                return POSITION_NONE;
        }
    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            webfragment = (webviewFragment) adapter.getItem(arg0);
            webfragment.performURL(address.get(arg0));
            positioner= arg0;
            Log.d("Selected", "current selected item = " + arg0);
            Log.d("Positioner", "current position item = " + positioner);
            Log.d("Current Address","Current URL = "+ address.get(arg0));


        }



    }
}