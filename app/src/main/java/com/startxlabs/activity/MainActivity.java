package com.startxlabs.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.arcfix.R;
import com.startxlabs.fragment.AppBrowserFragment;
import com.startxlabs.fragment.ChatTabFragment;
import com.startxlabs.fragment.FragmentTabsHome;
import com.startxlabs.fragment.InquiryFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabInitiateChat=(FloatingActionButton) findViewById(R.id.fab);
        mFabSendInquiry=(FloatingActionButton) findViewById(R.id.floating_inquiry);


        fabInitiateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class));
//                Snackbar.make(view, "Action need to be implemented", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            fabInitiateChat.setVisibility(View.VISIBLE);
            replaceFragment(FragmentTabsHome.class.getName(), FragmentTabsHome.class.getName(), null, null);
        }
        startActivity(new Intent(this, LatestNewsActivity.class));
    }


    public void replaceFragment(String fName, String tag, String backstaktag, Bundle data) {
        if (fName.equalsIgnoreCase(InquiryFragment.class.getName()) || fName.equalsIgnoreCase(ChatTabFragment.class.getName())||fName.equalsIgnoreCase(AppBrowserFragment.class.getName())) {
            fabInitiateChat.setVisibility(View.GONE);
            if(fName.equalsIgnoreCase(AppBrowserFragment.class.getName())){
                mFabSendInquiry.setVisibility(View.VISIBLE);
            }else{
                mFabSendInquiry.setVisibility(View.GONE);
            }

        } else {
            fabInitiateChat.setVisibility(View.VISIBLE);
        }
        Fragment fragment = Fragment.instantiate(this, fName, data);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment, tag);
        transaction.addToBackStack(backstaktag);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_youtube) {
            startActivity(new Intent(this, PlayerViewActivity.class));
        } else if (id == R.id.nav_inquiry) {
            replaceFragment(InquiryFragment.class.getName(), InquiryFragment.class.getName(), FragmentTabsHome.class.getName(), null);
        } else if (id == R.id.nav_home) {
            replaceFragment(FragmentTabsHome.class.getName(), FragmentTabsHome.class.getName(), null, null);
        } else if (id == R.id.nav_messages) {
            replaceFragment(ChatTabFragment.class.getName(), ChatTabFragment.class.getName(), FragmentTabsHome.class.getName(), null);

        }else if (id == R.id.nav_support) {
            replaceFragment(AppBrowserFragment.class.getName(), AppBrowserFragment.class.getName(), FragmentTabsHome.class.getName(), null);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
