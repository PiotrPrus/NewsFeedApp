package com.example.prusp.newsfeedapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {

    private static final int LOADER_ID = 0;
    private ArticlesAdapter adapter;
    private List<Article> parsedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticlesAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ArticlesAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Article article = parsedData.get(position);
                String url = article.getArticleUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        return new ArticlesLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        if (data != null) {
            this.parsedData = data;
            adapter.setArticleList(data);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {

    }
}
