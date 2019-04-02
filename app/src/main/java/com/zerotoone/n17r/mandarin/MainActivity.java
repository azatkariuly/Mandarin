package com.zerotoone.n17r.mandarin;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ShareCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zerotoone.n17r.mandarin.FragmentMainActivity.CollectionFragment;
import com.zerotoone.n17r.mandarin.FragmentMainActivity.MainActivityFragment;
import com.zerotoone.n17r.mandarin.FragmentMainActivity.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    TextView mEmailTextView;

    FirebaseAuth mAuth;
    MainActivityFragment mMainFragment;
    CollectionFragment mCollectionFragment;
    SettingsFragment mSettingsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        View headerView = navigationView.getHeaderView(0);
        mEmailTextView = (TextView) headerView.findViewById(R.id.emailTxt);
        mEmailTextView.setText(user.getEmail());


        mMainFragment = new MainActivityFragment();
        mCollectionFragment = new CollectionFragment();
        mSettingsFragment = new SettingsFragment();

        android.app.FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.replace(R.id.container, mMainFragment);
        ftrans.commit();


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            this.finishAffinity();
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.app.FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.wordsOfTheDay) {
            // Handle the camera action
            ftrans.replace(R.id.container, mMainFragment);
        } else if (id == R.id.collection) {
            ftrans.replace(R.id.container, mCollectionFragment);
        } else if (id == R.id.settings) {
            ftrans.replace(R.id.container, mSettingsFragment);
        } else if (id == R.id.nav_share) {
            String mimeType = "text/plain";
            String title = "Mandarin";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle(title)
                    .setText("I'm learning Mandarin. Try it out!")
                    .startChooser();
        }
        ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
