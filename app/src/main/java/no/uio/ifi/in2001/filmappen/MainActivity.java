package no.uio.ifi.in2001.filmappen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import no.uio.ifi.in2001.filmappen.Fragments.GenresFragment;
import no.uio.ifi.in2001.filmappen.Fragments.MovieFragment;
import no.uio.ifi.in2001.filmappen.Model.Genre;
import no.uio.ifi.in2001.filmappen.Model.Movie;

public class MainActivity extends AppCompatActivity implements GenresFragment.OnListFragmentInteractionListener, MovieFragment.OnListFragmentInteractionListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new MovieFragment())
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new GenresFragment())
                            .commit();
                    return true;
                case R.id.navigation_search:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
        

    }

    @Override
    public void onListFragmentInteraction(Movie movie) {

    }

    @Override
    public void onListFragmentInteraction(Genre movie) {

    }
}
