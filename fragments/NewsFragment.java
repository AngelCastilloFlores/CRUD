package com.example.android.menudietas.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.android.menudietas.R;

/**
 * Created by angelcastillo on 5/5/17.
 */

public class NewsFragment extends Fragment {

    public static final String URL_DIETAS_WEB = "http://dietasmusculacion.com/noticias";
    private WebView myWebView;

    public static NewsFragment newInstance(){
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_news, container, false);
        myWebView = (WebView) view.findViewById(R.id.webView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myWebView.loadUrl(URL_DIETAS_WEB);
    }
}
