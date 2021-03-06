package tests.get;

import org.asynchttpclient.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.TestParent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetMethodTest implements TestParent {

    @Test
    @Tag("syntax")
    @DisplayName("should work when required parameter 'expr' is present")
    void testCorrectExpression() throws Exception {

        Response response = asyncHttpClient.prepareGet("http://api.mathjs.org/v4/?expr=2*2").execute().get();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Tag("syntax")
    @DisplayName("shouldn't work when required parameter 'expr' is absent")
    void testWrongExpression() throws Exception {

        Response response = asyncHttpClient.prepareGet("http://api.mathjs.org/v4/").execute().get();
        assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @Tag("functionality")
    @DisplayName("should sum two numbers")
    void testAddition() throws Exception {

        Response response = asyncHttpClient.prepareGet("http://api.mathjs.org/v4/?expr=2%2B2").execute().get();
        assertEquals(response.getResponseBody(), "4");
    }

    @Test
    @Tag("functionality")
    @DisplayName("should subtract two numbers")
    void testSubtraction() throws Exception {

        Response response = asyncHttpClient.prepareGet("http://api.mathjs.org/v4/?expr=12-5").execute().get();
        assertEquals(response.getResponseBody(), "7");
    }

    @Test
    @Tag("functionality")
    @DisplayName("should multiplication two numbers")
    void testMultiplication() throws Exception {

        Response response = asyncHttpClient.prepareGet("http://api.mathjs.org/v4/?expr=5*5").execute().get();
        assertEquals(response.getResponseBody(), "25");
    }

    @Test
    @Tag("functionality")
    @DisplayName("should divide two numbers")
    void testDivision() throws Exception {

        Response response = asyncHttpClient.prepareGet("http://api.mathjs.org/v4/?expr=24%2F8").execute().get();
        assertEquals(response.getResponseBody(), "3");
    }

    @Test
    @Tag("functionality")
    @DisplayName("should divide and precision two numbers")
    void testPrecision() throws Exception {

        Response response = asyncHttpClient.prepareGet("http://api.mathjs.org/v4/?expr=100%2F6&precision=4").execute().get();
        assertEquals(response.getResponseBody(), "16.67");
    }

    @ParameterizedTest
    @CsvSource({"http://api.mathjs.org/v4/?expr=9%2B15, 24", "http://api.mathjs.org/v4/?expr=8%2F2, 4", "http://api.mathjs.org/v4/?expr=7*7, 49"})
    @Tag("functionality")
    @DisplayName("should should make few mathematics operations")
    void testPrecision(String request, String result) throws Exception {

        Response response = asyncHttpClient.prepareGet(request).execute().get();
        assertEquals(response.getResponseBody(), result);
    }
}