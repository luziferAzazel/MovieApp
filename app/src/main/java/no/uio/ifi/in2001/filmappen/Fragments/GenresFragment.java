package no.uio.ifi.in2001.filmappen.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import no.uio.ifi.in2001.filmappen.ListModel.GenreResponse;
import no.uio.ifi.in2001.filmappen.Model.Genre;
import no.uio.ifi.in2001.filmappen.R;
import no.uio.ifi.in2001.filmappen.RecyclerViewAdapters.GenreItemRecyclerViewAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link GenresFragment.OnListFragmentInteractionListener}
 * interface.
 */
public class GenresFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;

    public GenresFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GenresFragment newInstance(int columnCount) {
        GenresFragment fragment = new GenresFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
    // from HomeFragment
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        io.reactivex.Observable.fromCallable(new Callable<GenreResponse>() {

            @Override
            public GenreResponse call() throws Exception {
                return fetchGenres();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GenreResponse>() {
                    @Override
                    public void accept(GenreResponse genreResponse) throws Exception {
                        //String movieFetch = "Movies fetched: " + genreResponse.getList().size();

                        if (view instanceof RecyclerView) {
                            Context context = view.getContext();
                            RecyclerView recyclerView = (RecyclerView) view;
                            if (mColumnCount <= 1) {
                                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            } else {
                                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                            }
                            recyclerView.setAdapter(new GenreItemRecyclerViewAdapter(genreResponse.getList(), mListener, context));
                        }
                    }
                });
    }

    private GenreResponse fetchGenres() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/genre/movie/list?api_key=aaba60dc6164849086da1c4ddc25b066&language=eng")
                .build();

        Response response = client.newCall(request).execute();

        return toModel(response.body().string());
    }
    private GenreResponse toModel(String GenreString) {
        Gson gson = new Gson();
        System.out.println("Er den tom? genre" + gson.fromJson(GenreString, GenreResponse.class).getList());
        return gson.fromJson(GenreString, GenreResponse.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Genre genre);
    }
}
