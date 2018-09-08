package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Single {

    @SerializedName("idProduct")
    @Expose
    private int idProduct;
    @SerializedName("idMetaproduct")
    @Expose
    private int idMetaproduct;
    @SerializedName("countReprints")
    @Expose
    private int countReprints;
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
    @SerializedName("expansionName")
    @Expose
    private String expansionName;
    @SerializedName("expansionIcon")
    @Expose
    private int expansionIcon;
    @SerializedName("countArticles")
    @Expose
    private int countArticles;
    @SerializedName("countFoils")
    @Expose
    private int countFoils;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdMetaproduct() {
        return idMetaproduct;
    }

    public void setIdMetaproduct(int idMetaproduct) {
        this.idMetaproduct = idMetaproduct;
    }

    public int getCountReprints() {
        return countReprints;
    }

    public void setCountReprints(int countReprints) {
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

    public String getExpansionName() {
        return expansionName;
    }

    public void setExpansionName(String expansionName) {
        this.expansionName = expansionName;
    }

    public int getExpansionIcon() {
        return expansionIcon;
    }

    public void setExpansionIcon(int expansionIcon) {
        this.expansionIcon = expansionIcon;
    }

    public int getCountArticles() {
        return countArticles;
    }

    public void setCountArticles(int countArticles) {
        this.countArticles = countArticles;
    }

    public int getCountFoils() {
        return countFoils;
    }

    public void setCountFoils(int countFoils) {
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
                .append("number", number).append("rarity", rarity).append("expansionName", expansionName)
                .append("expansionIcon", expansionIcon).append("countArticles", countArticles)
                .append("countFoils", countFoils).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(countArticles).append(expansionName).append(localization).append(website)
                .append(rarity).append(image).append(links).append(number).append(idProduct).append(locName)
                .append(gameName).append(countFoils).append(idGame).append(categoryName).append(enName)
                .append(countReprints).append(idMetaproduct).append(expansionIcon).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Single) == false) {
            return false;
        }
        Single rhs = ((Single) other);
        return new EqualsBuilder().append(countArticles, rhs.countArticles).append(expansionName, rhs.expansionName)
                .append(localization, rhs.localization).append(website, rhs.website).append(rarity, rhs.rarity)
                .append(image, rhs.image).append(links, rhs.links).append(number, rhs.number)
                .append(idProduct, rhs.idProduct).append(locName, rhs.locName).append(gameName, rhs.gameName)
                .append(countFoils, rhs.countFoils).append(idGame, rhs.idGame).append(categoryName, rhs.categoryName)
                .append(enName, rhs.enName).append(countReprints, rhs.countReprints)
                .append(idMetaproduct, rhs.idMetaproduct).append(expansionIcon, rhs.expansionIcon).isEquals();
    }

}