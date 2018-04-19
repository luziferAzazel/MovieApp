package no.uio.ifi.in2001.filmappen.ListModel;

import java.util.List;
import no.uio.ifi.in2001.filmappen.Model.Genre;

public class GenreResponse {
    private List<Genre> genres;

    public GenreResponse(List<Genre> genres) {
        this.genres = genres;
    }
    public List<Genre> getList() {
        return genres;
    }
}
