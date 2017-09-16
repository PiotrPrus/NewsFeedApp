package com.example.prusp.newsfeedapp;

/**
 * Created by Piotr Prus on 20.08.2017.
 */

public class Article {

    private String webTitle;
    private String sectionName;
    private String authors;
    private String webPublicationDate;
    private String articleUrl;

    public Article(String webTitle, String sectionName, String authors, String webPublicationDate, String articleUrl) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.authors = authors;
        this.webPublicationDate = webPublicationDate;
        this.articleUrl = articleUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthors() {
        return authors;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Article{");
        sb.append("webTitle='").append(webTitle).append('\'');
        sb.append(", sectionName='").append(sectionName).append('\'');
        sb.append(", authors='").append(authors).append('\'');
        sb.append(", webPublicationDate='").append(webPublicationDate).append('\'');
        sb.append(", articleUrl='").append(articleUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
