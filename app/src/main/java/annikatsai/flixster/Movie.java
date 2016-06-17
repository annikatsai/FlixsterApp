package annikatsai.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w300/%s", backdropPath);
    }

    public String getRating() {
        String ratingString = rating.toString();
        return ratingString;
    }
    public String getPopularity() {
        String popString = popularity.toString();
        return popString;
    }

    public String title;
    public String posterPath;
    public String overview;
    public String backdropPath;
    public Double rating;
    public Double popularity;

    public Movie(JSONObject jsonObject) throws JSONException {

        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.popularity = jsonObject.getDouble("popularity");
        this.rating = jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++) {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
