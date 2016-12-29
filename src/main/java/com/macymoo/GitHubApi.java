package com.macymoo;

/**
 * Created by macpro2011 on 29/12/2016.
 */
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Crunchify.com
 *
 */

public class GitHubApi {

    @Value("${rulesEngineGitUrl}")
    private String rulesEngineGitUrl;


    public  GitHubApi() throws Throwable {
        URL gitHubUrl = new URL(rulesEngineGitUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) gitHubUrl.openConnection();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();

        // If URL is getting 301 and 302 redirection HTTP code then get new URL rulesEngineGitUrl.
        // This below for loop is totally optional if you are sure that your URL is not getting redirected to anywhere
        for (String header : headerFields.get(null)) {
            if (header.contains(" 302 ") || header.contains(" 301 ")) {
                rulesEngineGitUrl = headerFields.get("Location").get(0);
                gitHubUrl = new URL(rulesEngineGitUrl);
                httpURLConnection = (HttpURLConnection) gitHubUrl.openConnection();
                headerFields = httpURLConnection.getHeaderFields();
            }
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        String fromStream = getStringFromStream(inputStream);
        System.out.println(fromStream);
    }

    // ConvertStreamToString() Utility - we name it as getStringFromStream()
    private static String getStringFromStream(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer stringWriter = new StringWriter();

            char[] chars = new char[2048];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                int counter;
                while ((counter = reader.read(chars)) != -1) {
                    stringWriter.write(chars, 0, counter);
                }
            } finally {
                inputStream.close();
            }
            return stringWriter.toString();
        } else {
            return "No Contents";
        }
    }
}