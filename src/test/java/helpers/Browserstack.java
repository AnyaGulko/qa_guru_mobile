package helpers;


import drivers.BrowserstackDriverProvider;

import static io.restassured.RestAssured.given;

public class Browserstack extends BrowserstackDriverProvider {

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(authConfig.getUser(), authConfig.getKey())
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
