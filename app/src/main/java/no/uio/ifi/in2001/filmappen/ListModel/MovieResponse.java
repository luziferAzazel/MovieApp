package no.uio.ifi.in2001.filmappen.ListModel;
import java.util.List;

import no.uio.ifi.in2001.filmappen.Model.Movie;

/**
 * Created by dilek on 23.02.2018.
 */

public class MovieResponse {
    private List<Movie> results;

    public MovieResponse(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getList() {
        return results;
    }
}


