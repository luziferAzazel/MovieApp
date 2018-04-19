package no.uio.ifi.in2001.filmappen.RecyclerViewAdapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import no.uio.ifi.in2001.filmappen.Fragments.GenresFragment;
import no.uio.ifi.in2001.filmappen.Fragments.MovieFragment;
import no.uio.ifi.in2001.filmappen.Model.Genre;
import no.uio.ifi.in2001.filmappen.R;


public class GenreItemRecyclerViewAdapter extends RecyclerView.Adapter<GenreItemRecyclerViewAdapter.ViewHolder>{
    private final List<Genre> genreList;
    private final GenresFragment.OnListFragmentInteractionListener mListener;
    private final Context context;

    public GenreItemRecyclerViewAdapter(List<Genre> items, GenresFragment.OnListFragmentInteractionListener listener, Context context) {
        genreList = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.gItem = genreList.get(position);
        final Genre currentGenre = genreList.get(position);
        System.out.println("Movie URL " + currentGenre.getName());
        //Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+currentMovie.poster_path).into(holder.mPosterView);
        holder.mContentView.setText(currentGenre.getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("TEST");
                if (null != mListener) {
                    System.out.println("genre " + currentGenre.getName());
                    System.out.println("id " + currentGenre.getId());
                }
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override

    public int getItemCount() { return genreList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final ImageView mPosterView;
        private final TextView mContentView;
        private Genre gItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mPosterView = view.findViewById(R.id.movie_poster);
            mContentView = view.findViewById(R.id.title);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

