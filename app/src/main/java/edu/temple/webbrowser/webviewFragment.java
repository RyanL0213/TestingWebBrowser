package edu.temple.webbrowser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class webviewFragment extends Fragment {

    View l;
    WebView webview;
    String preaddress = "https://";
    webviewFragment.setaddressInterface parentActivity;
    private final String A_KEY = "address";
    static String finaladdress;
    //ViewPager viewPager;
    //final ArrayList<webviewFragment> fragments = new ArrayList<>();


    Handler responseHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(webview!=null) {
                webview.canGoBack();
                webview.canGoForward();
                webview.setWebViewClient(new HelloWebViewClient());
                webview.getSettings().setLoadsImagesAutomatically(true);
                webview.getSettings().setJavaScriptEnabled(true);
                webview.loadUrl(message.obj.toString());
            }
            return false;
        }
    });

    public void addfragment() {

    }



    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            parentActivity.setaddress(url);
            finaladdress = url;
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(getActivity()!=null)
            getActivity().setTitle(view.getTitle());
            //parentActivity.updatetitle(view.getTitle().toString());
            //parentActivity.updateaddress(url);
            Log.d("URL","current URL is " + url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            parentActivity.updatetitle(view.getTitle().toString());
            parentActivity.updateaddress(url);
        }
    }


   /* @Override
   /* public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        performURL(finaladdress);
    }

    */

    public webviewFragment() {
        // Required empty public constructor
    }




    /*public static webviewFragment newInstance(String param1, String param2) {
        webviewFragment fragment = new webviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

     */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            finaladdress = savedInstanceState.getString(A_KEY);
            performURL(finaladdress);
            //webview.restoreState(savedInstanceState);
        }

    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        l = inflater.inflate(R.layout.fragment_webview, container, false);
        webview = l.findViewById(R.id.webView);

        return l;
    }

    public String givemeaddress(){
        return finaladdress;
    }

    public void performURL(final String s) {


        try {
            if(s!=null&&!s.contains(preaddress)) {
                finaladdress = preaddress + s;
            }
            else{
                finaladdress = s;
            }
            URL url = new URL(finaladdress);
            Message msg = Message.obtain();
            msg.obj = url;
            responseHandler.sendMessage(msg);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void goback() {
        webview.goBack();
    }

    public void goforward() {
        webview.goForward();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof webviewFragment.setaddressInterface)
            parentActivity = (webviewFragment.setaddressInterface) context;
        else
            throw new RuntimeException("Error: no implementation of interface");
    }
    interface setaddressInterface{
        void setaddress(String s);
        void updatetitle(String s);
        void updateaddress(String url);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(A_KEY,finaladdress);
       // webview.saveState(outState);
    }



}
