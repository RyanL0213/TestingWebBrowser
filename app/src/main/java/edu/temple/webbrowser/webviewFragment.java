package edu.temple.webbrowser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link webviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class webviewFragment extends Fragment {

    View l;
    WebView webview;
    String preaddress = "https://";
    webviewFragment.setaddressInterface parentActivity;


    Handler responseHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message message) {
            webview.canGoBack();
            webview.canGoForward();
            webview.setWebViewClient(new HelloWebViewClient());
            webview.getSettings().setLoadsImagesAutomatically(true);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(message.obj.toString());
            return false;
        }
    });
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            parentActivity.setaddress(url);

            return true;
        }
    }

    public webviewFragment() {
        // Required empty public constructor
    }
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
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

    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

     */



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_webview, container, false);
        webview = l.findViewById(R.id.webView);
        return l;
    }

    public void performURL(final String s) {
        final String finaladdress;

        try {
            if(!s.contains(preaddress)) {
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
    }
}
