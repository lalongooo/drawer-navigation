package lalongooo.com.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity implements FragmentTwo.ShowDetail, FragmentManager.OnBackStackChangedListener {


    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FragmentOne()).commit();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Home").withIdentifier(1);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName("Settings").withIdentifier(2);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2
                ).withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        //this method is only called if the Arrow icon is shown. The hamburger is automatically managed by the MaterialDrawer
                        //if the back arrow is shown. close the activity
                        MainActivity.super.onBackPressed();
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

                        //get the DrawerLayout from the Drawer
                        DrawerLayout drawerLayout = drawer.getDrawerLayout();
                        //do whatever you want with the Drawer. Like locking it.
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        //return true if we have consumed the event
                        return true;
                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (drawerItem.getIdentifier()) {
                            case 1:

                                if(!(getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentOne)){

                                    getSupportFragmentManager()
                                            .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.fragmentContainer, new FragmentOne())
                                            .commit();
                                }

                                break;
                            case 2:
                                if(!(getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) instanceof FragmentTwo)) {

                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .hide(getSupportFragmentManager().findFragmentById(R.id.fragmentContainer))
                                            .add(R.id.fragmentContainer, new FragmentTwo())
                                            .addToBackStack(null)
                                            .commit();
                                }
                                break;
                        }
                        return false;
                    }
                })

                .build();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDetails() {

        getSupportFragmentManager()
                .beginTransaction()
                .hide(getSupportFragmentManager().findFragmentById(R.id.fragmentContainer))
                .add(R.id.fragmentContainer, new FragmentThree())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackStackChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if(fragment instanceof FragmentOne){
            drawer.setSelection(1);
        }else if(fragment instanceof FragmentTwo){
            drawer.setSelection(2);
        }
    }
}
