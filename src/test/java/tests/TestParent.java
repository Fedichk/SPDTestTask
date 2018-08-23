package tests;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;

public interface TestParent {

    AsyncHttpClient asyncHttpClient = Dsl.asyncHttpClient();
}