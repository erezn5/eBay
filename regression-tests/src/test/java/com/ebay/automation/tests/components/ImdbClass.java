package com.ebay.automation.tests.components;

import java.io.Serializable;
import java.util.List;

public class ImdbClass implements Serializable {

    String Title;
    String Year;
    String imdbID;
    String Type;
    String Poster;
    String Rated;
    String Released;
    String Runtime;
    String Genre;
    String Director;
    String Writer;
    String Actors;
    String Plot;
    String Language;
    String Country;
    String Awards;
    List<Rating> Ratings;
    String Metascore;
    String imdbRating;
    String imdbVotes;
    String DVD;
    String BoxOffice;
    String Production;
    String Response;
    String Website;


    @Override
    public String toString() {
        return "ImdbClass{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + Type + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Rated='" + Rated + '\'' +
                ", Released='" + Released + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Director='" + Director + '\'' +
                ", Writer='" + Writer + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Language='" + Language + '\'' +
                ", Country='" + Country + '\'' +
                ", Awards='" + Awards + '\'' +
                ", Ratings=" + Ratings +
                ", Metascore='" + Metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", DVD='" + DVD + '\'' +
                ", BoxOffice='" + BoxOffice + '\'' +
                ", Production='" + Production + '\'' +
                ", Response='" + Response + '\'' +
                ", WebSite='" + Website + '\'' +
                '}';
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public void setRatings(List<Rating> ratings) {
        Ratings = ratings;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public List<Rating> getRatings() {
        return Ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getDVD() {
        return DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public String getResponse() {
        return Response;
    }

    public String getWebsite() {
        return Website;
    }

    public ImdbClass(){}

    public static class Rating {

        String Source;
        String Value;

        public void setSource(String source) {
            Source = source;
        }

        public void setValue(String value) {
            Value = value;
        }

        @Override
        public String toString() {
            return "Rating{" +
                    "Source='" + Source + '\'' +
                    ", Value='" + Value + '\'' +
                    '}';
        }

        public String getSource() {
            return Source;
        }

        public String getValue() {
            return Value;
        }
    }

}