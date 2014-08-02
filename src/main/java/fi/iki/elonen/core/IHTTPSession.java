package fi.iki.elonen.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Handles one session, i.e. parses the HTTP request and returns the response.
 */
public interface IHTTPSession {
    void execute() throws IOException;

    Map<String, String> getParms();

    Map<String, String> getHeaders();

    /**
     * @return the path part of the URL.
     */
    String getUri();

    String getQueryParameterString();

    Method getMethod();

    InputStream getInputStream();

    NanoHTTPD.CookieHandler getCookies();

    /**
     * Adds the files in the request body to the files map.
     * @arg files - map to modify
     */
    void parseBody(Map<String, String> files) throws IOException, NanoHTTPD.ResponseException;
}
