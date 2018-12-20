package robfernandes.xyz.mynews.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import robfernandes.xyz.mynews.adapters.PageAdapter;
import robfernandes.xyz.mynews.R;

import static robfernandes.xyz.mynews.utils.Constants.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int index;
        switch (item.getItemId()) {
            case R.id.menu_drawer_sports:
                index = FragmentsConstants.SPORTS_INDEX;
                break;
            case R.id.menu_drawer_arts:
                index = FragmentsConstants.ARTS_INDEX;
                break;
            case R.id.menu_drawer_business:
                index = FragmentsConstants.BUSINESS_INDEX;
                break;
            case R.id.menu_drawer_most_popular:
                index = FragmentsConstants.MOST_POPULAR_INDEX;
                break;
            case R.id.menu_drawer_top_stories:
                index = FragmentsConstants.TOP_STORIES_INDEX;
                break;
            case R.id.menu_drawer_travel:
                index = FragmentsConstants.TRAVEL_INDEX;
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
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_toolbar_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                break;
            case R.id.menu_toolbar_help:
                intent = new Intent(MainActivity.this, HelpActivity.class);
                break;
            case R.id.menu_toolbar_notifications:
                intent = new Intent(MainActivity.this, NotificationsActivity.class);
                break;
            case R.id.menu_toolbar_search:
                intent = new Intent(MainActivity.this, SearchSelectorActivity.class);
                break;
            default:
                intent = new Intent(MainActivity.this, MainActivity.class);
        }
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        for (int i = 0; i < FragmentsConstants.PAGE_TITLES.length; i++) {
            String title = FragmentsConstants.PAGE_TITLES[i];
            Fragment fragment = FragmentsConstants.FRAGMENTS[i];
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