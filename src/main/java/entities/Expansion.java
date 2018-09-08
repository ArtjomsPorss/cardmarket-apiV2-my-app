package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expansion {

    @SerializedName("idExpansion")
    @Expose
    private int idExpansion;
    @SerializedName("enName")
    @Expose
    private String enName;
    @SerializedName("localization")
    @Expose
    private List<Localization> localization = null;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("icon")
    @Expose
    private int icon;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("isReleased")
    @Expose
    private boolean isReleased;
    @SerializedName("idGame")
    @Expose
    private String idGame;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public int getIdExpansion() {
        return idExpansion;
    }

    public void setIdExpansion(int idExpansion) {
        this.idExpansion = idExpansion;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public List<Localization> getLocalization() {
        return localization;
    }

    public void setLocalization(List<Localization> localization) {
        this.localization = localization;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isIsReleased() {
        return isReleased;
    }

    public void setIsReleased(boolean isReleased) {
        this.isReleased = isReleased;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idExpansion", idExpansion).append("enName", enName)
                .append("localization", localization).append("abbreviation", abbreviation).append("icon", icon)
                .append("releaseDate", releaseDate).append("isReleased", isReleased).append("idGame", idGame)
                .append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(idGame).append(isReleased).append(icon).append(enName).append(releaseDate)
                .append(localization).append(links).append(abbreviation).append(idExpansion).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Expansion) == false) {
            return false;
        }
        Expansion rhs = ((Expansion) other);
        return new EqualsBuilder().append(idGame, rhs.idGame).append(isReleased, rhs.isReleased).append(icon, rhs.icon)
                .append(enName, rhs.enName).append(releaseDate, rhs.releaseDate).append(localization, rhs.localization)
                .append(links, rhs.links).append(abbreviation, rhs.abbreviation).append(idExpansion, rhs.idExpansion)
                .isEquals();
    }

}