package com.spit.spirit17.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.spit.spirit17.Fragments.*;
import com.spit.spirit17.R;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    FragmentManager fm;
    String backStageName;

    private static final long DRAWER_DELAY = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*int[] images = {
                R.drawable.event_codatron,
                R.drawable.event_laser_maze,
                R.drawable.event_laser_tag,
                R.drawable.event_vsm,
                R.drawable.event_battle_frontier,
                R.drawable.event_escape_plan,
                R.drawable.event_tech_charades,
                R.drawable.event_tech_xplosion,
                R.drawable.event_no_escape,
                R.drawable.event_techeshis_castle,
                R.drawable.event_technovanza,
                R.drawable.event_tesseract,
                R.drawable.event_battle_of_brains,
                R.drawable.event_human_foosball,
                R.drawable.event_lan_gaming,
                R.drawable.event_lan_mafia,
                R.drawable.event_mind_that_word,
                R.drawable.event_pokemon_showdown
        };

        for(int i: images)
            Picasso.with(getApplicationContext()).load(i).resize(400, 400).centerCrop().fetch();*/


        //instantiation
        toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        navigationView =(NavigationView)findViewById(R.id.navigation_view);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        if(savedInstanceState == null){
            fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            EventsTabFragment fragment = EventsTabFragment.newInstance("Inter");
            transaction.replace(R.id.fragment_container,fragment).commit();
        }

        setupDrawerLayout();

        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    public void setupDrawerLayout(){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        drawerLayout.closeDrawers();
                        if(!item.isChecked()) {
                            final FragmentTransaction fragmentTransaction = fm.beginTransaction();
                            //fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                            fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
                            switch (item.getItemId()) {
                                case R.id.main_menuItem:

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            boolean isFragmentInStack = fm.popBackStackImmediate(backStageName, 0);
                                            if(!isFragmentInStack){
                                                EventsTabFragment fragment = EventsTabFragment.newInstance("Inter");
                                                fragmentTransaction.replace(R.id.fragment_container, fragment);
                                                backStageName = fragment.getClass().getName();
                                                fragmentTransaction.addToBackStack(backStageName).commit();
                                            }
                                            toolbar.setTitle(getString(R.string.app_name));
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.favorites_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new FavoritesFragment());
                                            fragmentTransaction.addToBackStack(null).commit();
                                            toolbar.setTitle("Favorites");
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.sponsors_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "Coming soon!", Toast.LENGTH_SHORT).show();

                                            // IMPORTANT: Remove checkable=false from the 'Sponsors' menu item in res/menu/navdrawer_menu.xml when Sponsors fragment is complete

                                            /* Delete later
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new SponsorsFragment());
                                            appBarLayout.setExpanded(false, true);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            collapsingToolbarLayout.setTitle("Sponsors");
                                            */
                                        }
                                    }, DRAWER_DELAY);
                                    return true; //Replace by 'break' later

                                case R.id.commitee_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new CommitteeFragment());
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            toolbar.setTitle("Committee");
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.developers_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new DevelopersFragment());
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            toolbar.setTitle("Developers");
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.contact_us_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new ContactUsFragment());
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            toolbar.setTitle("Contact us");
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.achievement_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new AchievementsFragment());
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            toolbar.setTitle(getString(R.string.achievements));
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.about_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new AboutAppFragment());
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            toolbar.setTitle(getResources().getString(R.string.aboutapp));
                                        }
                                    }, DRAWER_DELAY);
                            }
                        }
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri uri=null;
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menuItem_rate_app:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getResources().getString(R.string.playstore_link)));
                startActivity(intent);
                break;
            case R.id.menuItem_share_app:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT, "Check out the official app for Spirit 17!\n\n" + getResources().getString(R.string.playstore_link));
                intent1.setType("text/plain");
                startActivity(Intent.createChooser(intent1, getResources().getString(R.string.share_message)));
                break;
            /*case R.id.follow_us:
                return true;
            case R.id.menu_visit_website:
                uri = Uri.parse(getResources().getString(R.string.matrix_website));
                break;
            case R.id.menu_follow_facebook:
                uri = Uri.parse(getResources().getString(R.string.matrix_fb_link));
                break;
            case R.id.menu_follow_twitter:
                uri = Uri.parse(getResources().getString(R.string.matrix_twit_link));
                break;
            case R.id.menu_follow_instagram:
                uri = Uri.parse(getResources().getString(R.string.matrix_insta_link));
                break;
            case R.id.menu_follow_snapchat:
                uri = Uri.parse(getResources().getString(R.string.matrix_snap_link));
                break;*/
        }

        /*Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawers();
        else {
            navigationView.getMenu().getItem(0).setChecked(true);
            toolbar.setTitle(getString(R.string.app_name));

            super.onBackPressed();
        }
    }
}
