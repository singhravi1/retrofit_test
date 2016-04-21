package com.startxlabs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.arcfix.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by deep on 18/04/16.
 */
public class AppBrowserFragment extends Fragment {
    public final String KEY_WEBVIEW_URL_DATA = "web_data_argument";
    @Bind(R.id.webview_app)
    WebView mWebView;

    private String mUrl = "http://www.google.com";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_app_browser, container, false);
        ButterKnife.bind(this, v);
        if (getArguments() != null && getArguments().containsKey(KEY_WEBVIEW_URL_DATA)) {
            mUrl = getArguments().getString(KEY_WEBVIEW_URL_DATA);
        }

        setHasOptionsMenu(true);
        return v;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView.setWebViewClient(new WebClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.loadUrl(mUrl);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_app_browser, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                if (mWebView.canGoBack())
                    mWebView.goBack();
                return true;
            case R.id.action_forward:
                if (mWebView.canGoForward())
                    mWebView.goForward();
                return true;
            case R.id.action_refresh:

                mWebView.reload();
                return true;
            case R.id.action_bookmark:
                //TODO: bookmark url where do we have to save this?
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class WebClient extends WebViewClient {

    }
}
