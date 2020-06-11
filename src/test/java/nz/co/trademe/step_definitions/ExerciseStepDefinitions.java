package nz.co.trademe.step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.trademe.TestDriver;
import nz.co.trademe.entities.Car;
import nz.co.trademe.entities.Cars;
import nz.co.trademe.entities.Subcategory;
import nz.co.trademe.pages.UsedCarsPage;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class ExerciseStepDefinitions {

    String uri;
    HttpGet getRequest;

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    final String oAuthHeader = "OAuth " +
        "oauth_consumer_key=\"" + Hooks.testDriver.properties.getProperty("oauth_consumer_key") + "\", " +
        "oauth_signature_method=\"PLAINTEXT\", " +
        "oauth_signature=\"" + Hooks.testDriver.properties.getProperty("oauth_consumer_secret") + "%26\"";

    String responseString;
    Cars cars;

    final String runTarget = Hooks.testDriver.properties.getProperty("run_target");

    UsedCarsPage usedCarsPage;

    Optional<Subcategory> foundSubcategory;

    @When("^I browse the Used Cars category$")
    public void iBrowseTheUsedCarsCategory() throws IOException {

        if (runTarget.equals("api")) {

            uri = Hooks.testDriver.properties.getProperty("api_uri") + "Search/Motors/Used.json";

            getRequest = new HttpGet(uri);
            getRequest.setHeader("Authorization", oAuthHeader);
            HttpResponse response = httpClient.execute(getRequest);

            responseString = convertResponseToString(response);

            cars = Cars.fromJson(responseString);

            Assert.assertEquals(
                200,
                response.getStatusLine().getStatusCode()
            );

        }

        if (runTarget.equals("web")) {

            uri = Hooks.testDriver.properties.getProperty("web_uri") + "?cid=268";

            TestDriver.driver.get(uri);

            usedCarsPage = new UsedCarsPage(TestDriver.driver);

        }

    }

    @Then("^I will be able to count how many makes of used cars are available$")
    public void iWillBeAbleToCountHowManyMakesOfUsedCarsAreAvailable() {

        if (runTarget.equals("api")) {

            long count = cars.carList
                .stream()
                .filter(distinctByKey(Car::getMake))
                .count();

            System.out.println("Count of different makes available: " + count);

        }

        if (runTarget.equals("web")) {

            System.out.println("Count of different makes available: " + usedCarsPage.subcategoryList.size());

        }

    }

    // Found Here: https://stackoverflow.com/questions/23699371/java-8-distinct-by-property

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @When("^I search for a used car made by \"([^\"]*)\"$")
    public void iSearchForAUsedCarMadeBy(String make) throws Throwable {

        if (runTarget.equals("api")) {

            uri = Hooks.testDriver.properties.getProperty("api_uri") + "Search/Motors/Used.json";

            getRequest = new HttpGet(uri + "?make=" + encodeString(make));
            getRequest.setHeader("Authorization", oAuthHeader);
            HttpResponse response = httpClient.execute(getRequest);

            responseString = convertResponseToString(response);

            cars = Cars.fromJson(responseString);

            Assert.assertEquals(
                200,
                response.getStatusLine().getStatusCode()
            );

        }

        if (runTarget.equals("web")) {

            uri = Hooks.testDriver.properties.getProperty("web_uri") + "?cid=268";

            TestDriver.driver.get(uri);

            usedCarsPage = new UsedCarsPage(TestDriver.driver);

            foundSubcategory = usedCarsPage.subcategoryList
                .stream()
                .filter(s -> s.make.equals(make))
                .findFirst();

        }

    }

    private static String encodeString(String string) {
        try {
            return URLEncoder.encode(string, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Then("^I should see at least one \"([^\"]*)\" is available$")
    public void iShouldSeeAtLeastOneIsAvailable(String make) {

        if (runTarget.equals("api")) {
            Assert.assertTrue("I should see at least one " + make + " is available - ", cars.carList.size() > 0);
        }

        if (runTarget.equals("web")) {
            Assert.assertTrue("I should see at least one " + make + " is available - ", foundSubcategory.isPresent());
        }

    }

    @And("^I will be able to count how many are available$")
    public void iWillBeAbleToCountHowManyAreAvailable() {

        if (runTarget.equals("api")) {
            System.out.println("Count of available used cars made by Kia: " + cars.carList.size());
        }

        if (runTarget.equals("web")) {
            long count = 0;
            if (foundSubcategory.isPresent()) {
                count = foundSubcategory.get().count;
            }
            System.out.println("Count of available used cars made by Kia: " + count);
        }

    }

    @Then("^I should see that the make does not exist$")
    public void iShouldSeeThatTheMakeDoesNotExist() {

        if (runTarget.equals("api")) {
            Assert.assertEquals("I should see that the make does not exist - ", 0, cars.carList.size());
        }

        if (runTarget.equals("web")) {
            Assert.assertTrue("I should see that the make does not exist - ", !foundSubcategory.isPresent());
        }

    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

}
