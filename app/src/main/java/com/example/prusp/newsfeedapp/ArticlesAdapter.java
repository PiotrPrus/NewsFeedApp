package com.example.prusp.newsfeedapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Piotr Prus on 20.08.2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private List<Article> articleList;
    private static ClickListener clickListener;

    public ArticlesAdapter() {
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_news_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, int position) {
        holder.titleTextView.setText(articleList.get(position).getWebTitle());
        holder.sectionNameTextView.setText(articleList.get(position).getSectionName());
        holder.authorsTextView.setText(articleList.get(position).getAuthors());
        holder.publicationDateTextView.setText(articleList.get(position).getWebPublicationDate());
    }

    @Override
    public int getItemCount() {
        if (articleList != null && !articleList.isEmpty()) {
            return articleList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.title_text_view)
        TextView titleTextView;
        @BindView(R.id.section_name_text_view)
        TextView sectionNameTextView;
        @BindView(R.id.authors_text_view)
        TextView authorsTextView;
        @BindView(R.id.publication_date_text_view)
        TextView publicationDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        ArticlesAdapter.clickListener = clickListener;
    }

public interface ClickListener {
    void onItemClick(int position, View v);
}
}
