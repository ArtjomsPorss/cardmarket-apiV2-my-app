package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleWrapper {

    @SerializedName("article")
    @Expose
    private List<Article> article = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("article", article).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(article).append(links).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ArticleWrapper) == false) {
            return false;
        }
        ArticleWrapper rhs = ((ArticleWrapper) other);
        return new EqualsBuilder().append(article, rhs.article).append(links, rhs.links).isEquals();
    }

}