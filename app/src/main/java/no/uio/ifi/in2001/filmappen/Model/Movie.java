package no.uio.ifi.in2001.filmappen.Model;

import java.util.List;

import no.uio.ifi.in2001.filmappen.Model.Genre;

/**
 * Created by dilek on 23.02.2018.
 */
public class Movie {
    private int id;
    private String title;
    private List<Genre> genres;
    private String backdrop_path;
    private String poster_path;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
