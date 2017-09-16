package com.example.prusp.newsfeedapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Piotr Prus on 21.08.2017.
 */

public class ParserUtils {

    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECT_TIMEOUT = 15000;
    private static final int SUCCESS_RESPONSE = 200;

    static String createUrlToParseData() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("show-references", "author")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("q", "android")
                .appendQueryParameter("api-key", "38a6e553-1904-4084-82a9-b0c08169af8b");
        return builder.build().toString();
    }

    static URL createUrl() {
        String stringUrl = createUrlToParseData();
        try {
            return new URL(stringUrl);
        }catch (MalformedURLException e) {
            Log.e("ParserUtils", "Error creating URL: ", e);
            return null;
        }
    }

    private static String formatPublishDate(String publishDate) {
        String jsonDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat jsonConverter = new SimpleDateFormat(jsonDatePattern, Locale.US);
        try{
            Date parsedDate = jsonConverter.parse(publishDate);
            String finalDatePattern = "dd-MM-yyyy";
            SimpleDateFormat finalDateFormatter = new SimpleDateFormat(finalDatePattern, Locale.US);
            return finalDateFormatter.format(parsedDate);
        } catch (ParseException e) {
            Log.e("ParserUtils", "Error parsing JSON date: ", e);
            return "";
        }
    }

    static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if(url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == SUCCESS_RESPONSE){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("ParserUtils", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("ParserUtils", "Error making http request: ", e);
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line !=null){
                sb.append(line);
                line = reader.readLine();
            }
        }
        return sb.toString();
    }

    static List<Article> parseJson(String response){
        List<Article> articleList = new ArrayList<>();
        try{
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject jsonResults = jsonResponse.getJSONObject("response");
            JSONArray resultsArray = jsonResults.getJSONArray("results");

            for(int i=0;i<resultsArray.length(); i++) {
                JSONObject oneArticle = resultsArray.getJSONObject(i);
                String articleTitle = oneArticle.getString("webTitle");
                String url = oneArticle.getString("webUrl");
                String date = oneArticle.getString("webPublicationDate");
                date = formatPublishDate(date);
                String section = oneArticle.getString("sectionName");
                JSONArray tagsArray = oneArticle.getJSONArray("tags");
                String author = "";

                if (tagsArray.length() == 0) {
                    author = "";
                } else {
                    for (int j = 0; j<tagsArray.length(); j++) {
                        JSONObject tagsArrayJSONObject = tagsArray.getJSONObject(j);
                        author += tagsArrayJSONObject.getString("webTitle") + ", ";
                    }
                    author = (author.substring(0, author.length() - 2));
                }
                articleList.add(new Article(articleTitle, section, author, date, url));
            }
        } catch (JSONException e) {
            Log.e("ParserUtils", "Error parsing JSON response", e);
        }
        return articleList;
    }
}
