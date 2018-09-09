package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("idArticle")
    @Expose
    private Integer idArticle;
    @SerializedName("idProduct")
    @Expose
    private Integer idProduct;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("inShoppingCart")
    @Expose
    private Boolean inShoppingCart;
    @SerializedName("seller")
    @Expose
    private User seller;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("isFoil")
    @Expose
    private Boolean isFoil;
    @SerializedName("isSigned")
    @Expose
    private Boolean isSigned;
    @SerializedName("isPlayset")
    @Expose
    private Boolean isPlayset;
    @SerializedName("isAltered")
    @Expose
    private Boolean isAltered;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getInShoppingCart() {
        return inShoppingCart;
    }

    public void setInShoppingCart(Boolean inShoppingCart) {
        this.inShoppingCart = inShoppingCart;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean getIsFoil() {
        return isFoil;
    }

    public void setIsFoil(Boolean isFoil) {
        this.isFoil = isFoil;
    }

    public Boolean getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(Boolean isSigned) {
        this.isSigned = isSigned;
    }

    public Boolean getIsPlayset() {
        return isPlayset;
    }

    public void setIsPlayset(Boolean isPlayset) {
        this.isPlayset = isPlayset;
    }

    public Boolean getIsAltered() {
        return isAltered;
    }

    public void setIsAltered(Boolean isAltered) {
        this.isAltered = isAltered;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idArticle", idArticle).append("idProduct", idProduct)
                .append("language", language).append("comments", comments).append("price", price).append("count", count)
                .append("inShoppingCart", inShoppingCart).append("seller", seller).append("condition", condition)
                .append("isFoil", isFoil).append("isSigned", isSigned).append("isPlayset", isPlayset)
                .append("isAltered", isAltered).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isPlayset).append(count).append(condition).append(inShoppingCart)
                .append(links).append(isFoil).append(idProduct).append(isAltered).append(isSigned).append(seller)
                .append(price).append(idArticle).append(language).append(comments).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Article) == false) {
            return false;
        }
        Article rhs = ((Article) other);
        return new EqualsBuilder().append(isPlayset, rhs.isPlayset).append(count, rhs.count)
                .append(condition, rhs.condition).append(inShoppingCart, rhs.inShoppingCart).append(links, rhs.links)
                .append(isFoil, rhs.isFoil).append(idProduct, rhs.idProduct).append(isAltered, rhs.isAltered)
                .append(isSigned, rhs.isSigned).append(seller, rhs.seller).append(price, rhs.price)
                .append(idArticle, rhs.idArticle).append(language, rhs.language).append(comments, rhs.comments)
                .isEquals();
    }

}