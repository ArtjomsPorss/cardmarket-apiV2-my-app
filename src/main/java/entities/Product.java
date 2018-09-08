package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("idProduct")
    @Expose
    private Integer idProduct;
    @SerializedName("idMetaproduct")
    @Expose
    private Integer idMetaproduct;
    @SerializedName("countReprints")
    @Expose
    private Integer countReprints;
    @SerializedName("enName")
    @Expose
    private String enName;
    @SerializedName("locName")
    @Expose
    private String locName;
    @SerializedName("localization")
    @Expose
    private List<Localization> localization = null;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("gameName")
    @Expose
    private String gameName;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("idGame")
    @Expose
    private String idGame;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("rarity")
    @Expose
    private String rarity;
    @SerializedName("expansion")
    @Expose
    private Expansion expansion;
    @SerializedName("priceGuide")
    @Expose
    private PriceGuide priceGuide;
    @SerializedName("reprint")
    @Expose
    private List<Reprint> reprint = null;
    @SerializedName("countArticles")
    @Expose
    private Integer countArticles;
    @SerializedName("countFoils")
    @Expose
    private Integer countFoils;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdMetaproduct() {
        return idMetaproduct;
    }

    public void setIdMetaproduct(Integer idMetaproduct) {
        this.idMetaproduct = idMetaproduct;
    }

    public Integer getCountReprints() {
        return countReprints;
    }

    public void setCountReprints(Integer countReprints) {
        this.countReprints = countReprints;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public List<Localization> getLocalization() {
        return localization;
    }

    public void setLocalization(List<Localization> localization) {
        this.localization = localization;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Expansion getExpansion() {
        return expansion;
    }

    public void setExpansion(Expansion expansion) {
        this.expansion = expansion;
    }

    public PriceGuide getPriceGuide() {
        return priceGuide;
    }

    public void setPriceGuide(PriceGuide priceGuide) {
        this.priceGuide = priceGuide;
    }

    public List<Reprint> getReprint() {
        return reprint;
    }

    public void setReprint(List<Reprint> reprint) {
        this.reprint = reprint;
    }

    public Integer getCountArticles() {
        return countArticles;
    }

    public void setCountArticles(Integer countArticles) {
        this.countArticles = countArticles;
    }

    public Integer getCountFoils() {
        return countFoils;
    }

    public void setCountFoils(Integer countFoils) {
        this.countFoils = countFoils;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idProduct", idProduct).append("idMetaproduct", idMetaproduct)
                .append("countReprints", countReprints).append("enName", enName).append("locName", locName)
                .append("localization", localization).append("website", website).append("image", image)
                .append("gameName", gameName).append("categoryName", categoryName).append("idGame", idGame)
                .append("number", number).append("rarity", rarity).append("expansion", expansion)
                .append("priceGuide", priceGuide).append("reprint", reprint).append("countArticles", countArticles)
                .append("countFoils", countFoils).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(expansion).append(countArticles).append(localization).append(website)
                .append(rarity).append(image).append(links).append(number).append(idProduct).append(locName)
                .append(gameName).append(countFoils).append(idGame).append(categoryName).append(enName)
                .append(countReprints).append(priceGuide).append(reprint).append(idMetaproduct).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Product rhs = ((Product) other);
        return new EqualsBuilder().append(expansion, rhs.expansion).append(countArticles, rhs.countArticles)
                .append(localization, rhs.localization).append(website, rhs.website).append(rarity, rhs.rarity)
                .append(image, rhs.image).append(links, rhs.links).append(number, rhs.number)
                .append(idProduct, rhs.idProduct).append(locName, rhs.locName).append(gameName, rhs.gameName)
                .append(countFoils, rhs.countFoils).append(idGame, rhs.idGame).append(categoryName, rhs.categoryName)
                .append(enName, rhs.enName).append(countReprints, rhs.countReprints).append(priceGuide, rhs.priceGuide)
                .append(reprint, rhs.reprint).append(idMetaproduct, rhs.idMetaproduct).isEquals();
    }

}