package com.example.prusp.newsfeedapp;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Piotr Prus on 21.08.2017.
 */

public class ArticlesLoader extends AsyncTaskLoader<List<Article>>{
    public ArticlesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        List<Article> listOfArticles = null;
        try{
            URL url = ParserUtils.createUrl();
            String jsonResponse = ParserUtils.makeHttpRequest(url);
            listOfArticles = ParserUtils.parseJson(jsonResponse);
        } catch (IOException e) {
            Log.e("ParserUtils", "Error Loader LoadInBackground: "+e);
        }
        return listOfArticles;
    }
}
