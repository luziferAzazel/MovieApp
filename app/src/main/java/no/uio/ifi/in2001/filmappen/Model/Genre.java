package no.uio.ifi.in2001.filmappen.Model;

public class Genre {
    private int id;
    private String name;
    private String backdrop_path;

    // https://api.themoviedb.org/3/genre/movie/list?api_key=aaba60dc6164849086da1c4ddc25b066&language=eng

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void addImage(String imageUri) {
        backdrop_path = imageUri;
    }
    public String getImage() {
        return backdrop_path;
    }

}

