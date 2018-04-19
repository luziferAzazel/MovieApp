package no.uio.ifi.in2001.filmappen.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import no.uio.ifi.in2001.filmappen.Fragments.GenresFragment;
import no.uio.ifi.in2001.filmappen.Fragments.MovieFragment;
import no.uio.ifi.in2001.filmappen.Model.Movie;
import no.uio.ifi.in2001.filmappen.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Movie} and makes a call to the
 * specified {@link MovieFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieItemRecyclerViewAdapter extends RecyclerView.Adapter<MovieItemRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> mValues;
    private final MovieFragment.OnListFragmentInteractionListener mListener;
    private final Context context;

    public MovieItemRecyclerViewAdapter(List<Movie> items, MovieFragment.OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public MovieItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }
    //henter fra sporring, position for liste<Movie>
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Movie currentMovie = mValues.get(position);
        System.out.println("Movie URL " + currentMovie.getPoster_path());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+currentMovie.getPoster_path()).into(holder.mPosterView);
        holder.mContentView.setText(currentMovie.getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final ImageView mPosterView;
        private final TextView mContentView;
        private  Movie mItem;

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
