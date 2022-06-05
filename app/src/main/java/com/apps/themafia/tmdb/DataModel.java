package com.apps.themafia.tmdb;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by The Mafia on 5/31/2022.
 */

public class DataModel {
    private String title , rated , released , HD_Release , runtime , genre , director  , actors  , plot , awards , type , BoxOffice , imdbID , rottenTomatoes;
    private int year , imdbRating ,  metaScore , imdbVotes;
    private boolean response;
    private String imageURL;

    public static DataModel sDataModel(JSONObject response){
        DataModel dataModel = new DataModel();
        try {
            dataModel.imageURL = response.getString("Poster");
            dataModel.title = response.getString("Title");
            dataModel.director = response.getString("Director");
            dataModel.rated = response.getString("Rated");
            dataModel.released = response.getString("Released");
            dataModel.HD_Release = response.getString("DVD");
            dataModel.runtime = response.getString("Runtime");
            dataModel.genre = response.getString("Genre");
            dataModel.actors = response.getString("Actors");
            dataModel.plot = response.getString("Plot");
            dataModel.awards = response.getString("Awards");
            dataModel.type = response.getString("Type");
            dataModel.BoxOffice = response.getString("BoxOffice");
            dataModel.imdbID = response.getString("imdbID");
            dataModel.year = response.getInt("Year");
            dataModel.imdbRating = response.getInt("imdbRating");
            dataModel.rottenTomatoes = response.getJSONArray("Ratings").getJSONObject(1).getString("Value");
            dataModel.metaScore = response.getInt("Metascore");
            dataModel.imdbVotes = response.getInt("imdbVotes");
            dataModel.response = response.getBoolean("Response");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataModel;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getHD_Release() {
        return HD_Release;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getAwards() {
        return awards;
    }

    public String getType() {
        return type;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getRottenTomatoes() {
        return rottenTomatoes;
    }

    public int getYear() {
        return year;
    }

    public int getImdbRating() {
        return imdbRating;
    }

    public int getMetaScore() {
        return metaScore;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public boolean isResponse() {
        return response;
    }

    public String getImageURL() {
        return imageURL;
    }
}
