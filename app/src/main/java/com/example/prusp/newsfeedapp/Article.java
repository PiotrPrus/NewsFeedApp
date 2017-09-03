package com.example.prusp.newsfeedapp;

/**
 * Created by Piotr Prus on 20.08.2017.
 */

public class Article {

    String webTitle;
    String sectionName;
    String authors;
    String webPublicationDate;
    String articleUrl;

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

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
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
