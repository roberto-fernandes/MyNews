package robfernandes.xyz.mynews.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import robfernandes.xyz.mynews.R;

import static robfernandes.xyz.mynews.Utils.Constants.ARTS_INDEX;
import static robfernandes.xyz.mynews.Utils.Constants.BUSINESS_INDEX;
import static robfernandes.xyz.mynews.Utils.Constants.FRAGMENTS;
import static robfernandes.xyz.mynews.Utils.Constants.MOST_POPULAR_INDEX;
import static robfernandes.xyz.mynews.Utils.Constants.PAGE_TITLE;
import static robfernandes.xyz.mynews.Utils.Constants.SPORTS_INDEX;
import static robfernandes.xyz.mynews.Utils.Constants.TOP_STORIES_INDEX;
import static robfernandes.xyz.mynews.Utils.Constants.TRAVEL_INDEX;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        setSupportActionBar(toolbar);
        setupViewPager();
        setDrawer();
    }

    private void setViews() {
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawerLayout);
        tabLayout = findViewById(R.id.tab_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    private void setDrawer() {
        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //handling navigation view item event
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int index;
        switch (item.getItemId()) {
            case R.id.menu_drawer_sports:
                index = SPORTS_INDEX;
                break;
            case R.id.menu_drawer_arts:
                index = ARTS_INDEX;
                break;
            case R.id.menu_drawer_business:
                index = BUSINESS_INDEX;
                break;
            case R.id.menu_drawer_most_popular:
                index = MOST_POPULAR_INDEX;
                break;
            case R.id.menu_drawer_top_stories:
                index = TOP_STORIES_INDEX;
                break;
            case R.id.menu_drawer_travel:
                index = TRAVEL_INDEX;
                break;
            default:
                index = 0;
        }
        viewPager.setCurrentItem(index);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_toolbar_about:
                intent = new Intent(MainActivity.this,AboutActivity.class);
                break;
            case R.id.menu_toolbar_help:
                intent = new Intent(MainActivity.this,HelpActivity.class);
                break;
            case R.id.menu_toolbar_notifications:
                intent = new Intent(MainActivity.this,NotificationsActivity.class);
                break;
            case R.id.menu_toolbar_search:
                intent = new Intent(MainActivity.this,SearchActivity.class);
                break;
            default:  intent = new Intent(MainActivity.this,MainActivity.class);
        }
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        for (int i = 0; i < PAGE_TITLE.length; i++) {
            String title = PAGE_TITLE[i];
            Fragment fragment = FRAGMENTS[i];
            tabLayout.addTab(tabLayout.newTab().setText(title));
            adapter.addFragment(fragment, title);
        }
        viewPager.setAdapter(adapter);
        //to change fragments when a tab is clicked
        tabLayout.setupWithViewPager(viewPager);
        //change Tab selection when changing ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}