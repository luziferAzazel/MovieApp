package no.uio.ifi.in2001.filmappen;
import java.util.List;

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


