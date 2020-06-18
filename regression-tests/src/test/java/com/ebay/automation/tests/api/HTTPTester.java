package com.ebay.automation.tests.api;

import com.ebay.automation.framework.conf.EnvConf;
import com.ebay.automation.framework.utils.SimpleHttpClient;
import com.ebay.automation.tests.BaseTest;
import com.ebay.automation.tests.components.ImdbClass;
import com.ebay.automation.framework.utils.JsonHandler;
import com.ebay.automation.tests.components.Rating;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ebay.automation.framework.logger.LoggerFactory.LOG;

public class HTTPTester extends BaseTest {

    private HashMap<String, String> headersMap = new HashMap<>();
    private static final String BASIC_URL = EnvConf.getProperty("base.url");
    private static List<ImdbClass> lists = new ArrayList<>();
    private SimpleHttpClient simpleHttpClient;
    private List<ImdbClass> getParsedImdbList(String response) {
        JsonArray array = JsonHandler.asList(response, "Search");
        return JsonHandler.getJsonAsClassObjectList(array.toString(), ImdbClass[].class);
    }

    @BeforeMethod
    private void initSimpleHttpClient(){
        simpleHttpClient = new SimpleHttpClient();
    }

    private String getResponse(String path) throws IOException {
        String uri = BASIC_URL.concat(path);
        return simpleHttpClient.sendGetRequest(uri, headersMap);
    }

    @Test(priority = 5, dataProvider = "pathProvider")
    public void getImdbMovieList(String path) throws Exception {
        lists = getParsedImdbList(getResponse(path));
        Assert.assertFalse(lists.isEmpty(), "List should not be empty");
        for (ImdbClass item : lists) {
            if (!item.getPoster().equals("N/A")) {
                Reporter.log(item.getPoster());
                System.out.println(item.getPoster());
            }
        }

        LOG.info("Verification of getting list from server passed successfully!");
    }

    @DataProvider(name = "pathProvider")
    public Object[][] pathProvider(Method m) {
        switch (m.getName()) {
            case "getImdbMovieList":
                return new Object[][]{
                        {"/?apikey=772f549d&s=prom&y=2020"}
                };
            case "compareImdbObjectsValues":
                return new Object[][]{
                        {"/?apikey=772f549d&y=2020&i=tt0264947"}
                };
            case "verifyMovieByIMDBId":
                return new Object[][]{
                        {"/?apikey=772f549d&y=2020&s=Saving"}
                };
            case "verifyAPIKeyErrorMessage":
                return new Object[][]{
                        {"/?apikey=772f54d&y=2020"}
                };
            case "seekingMovieInFutureYear":
                return new Object[][]{
                        {"?apikey=772f549d&s=Saving Private Ryan&y=2030"}
                };
        }
        return null;

    }

    @Test(priority = 10, dataProvider = "pathProvider")
    public void compareImdbObjectsValues(String path) throws Exception {
        ImdbClass movieItem = JsonHandler.getJsonAsClassObject(getResponse(path), ImdbClass.class);
        Assert.assertNotNull(movieItem, "Item should be different from null");
        Assert.assertTrue(compareImdbObjectsValues(movieItem), "Two object should be equal");
        LOG.info("Comparing item objects passed successfully");
    }

    private boolean compareImdbObjectsValues(ImdbClass movieItem) {
        ImdbClass item = populateArtificialImdbObject();
        return item.getTitle().equals(movieItem.getTitle()) &&
                movieItem.getYear().equals(item.getYear()) &&
                movieItem.getImdbID().equals(item.getImdbID()) &&
                movieItem.getType().equals(item.getType()) &&
                movieItem.getPoster().equals(item.getPoster()) &&
                movieItem.getLanguage().equals(item.getLanguage()) &&
                movieItem.getRatings().retainAll(item.getRatings()) &&
                movieItem.getImdbRating().equals(item.getImdbRating()) &&
                movieItem.getImdbVotes().equals(item.getImdbVotes());
    }

    private ImdbClass populateArtificialImdbObject() {
        ImdbClass item = new ImdbClass();
        item.setTitle("A Private Affair");
        item.setYear("2000");
        item.setImdbID("tt0264947");
        item.setType("movie");
        item.setPoster("https://m.media-amazon.com/images/M/MV5BMTcxMTY4NzMyNl5BMl5BanBnXkFtZTcwMzQ0MjEzMQ@@._V1_SX300.jpg");
        item.setLanguage("English");
        item.setCountry("USA");
        Rating rating = new Rating();
        rating.setSource("Internet Movie Database");
        rating.setValue("7.1/10");
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        item.setRatings(ratingList);
        item.setImdbRating("7.1");
        item.setImdbVotes("20");
        return item;
    }

    @Test(priority = 20, dataProvider = "pathProvider")
    public void verifyMovieByIMDBId(String path) throws IOException {
        lists = getParsedImdbList(getResponse(path));
        int randomNumber = getRandomNumber();
        ImdbClass imdbClass = lists.get(randomNumber);
        String p = String.format("/?apikey=772f549d&i=%s", imdbClass.getImdbID());
        ImdbClass imdbIdItem = JsonHandler.getJsonAsClassObject(getResponse(p), ImdbClass.class);
        Assert.assertEquals(lists.get(randomNumber).getTitle(), imdbIdItem.getTitle(),
                String.format("titles should be equals instead IMDB item from is =[%s] and item from server is=[%s]",
                        lists.get(randomNumber).getTitle(),imdbIdItem.getTitle()));
        LOG.info("Verifying movie by id passed successfully");

    }

    private int getRandomNumber() {
        return (int) ((Math.random() * (lists.size() - 1)) + 1);
    }

    @Test(priority=25, dataProvider = "pathProvider")
    public void verifyAPIKeyErrorMessage(String path) throws IOException {
        JsonObject jsonObject = JsonHandler.getJsonAsClassObject(getResponse(path), JsonObject.class);
        Assert.assertEquals(jsonObject.get("Error").toString(), "\"Invalid API key!\"", "API Key should be invalid!");
        LOG.info("Verify API Key invalid is successful");
    }

    @Test(priority=30, dataProvider="pathProvider")
    public void seekingMovieInFutureYear(String path) throws IOException {
        JsonObject jsonObject = JsonHandler.getJsonAsClassObject(getResponse(path), JsonObject.class);
        Assert.assertEquals(jsonObject.get("Error").toString(), "\"Movie not found!\"", "Movie should not be displayed");
        LOG.info("Verify Movie not found is successful");
    }

}
