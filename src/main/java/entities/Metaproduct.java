package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metaproduct {

    @SerializedName("idMetaproduct")
    @Expose
    private Integer idMetaproduct;
    @SerializedName("enName")
    @Expose
    private String enName;
    @SerializedName("locName")
    @Expose
    private String locName;
    @SerializedName("localization")
    @Expose
    private List<Localization> localization = null;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getIdMetaproduct() {
        return idMetaproduct;
    }

    public void setIdMetaproduct(Integer idMetaproduct) {
        this.idMetaproduct = idMetaproduct;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idMetaproduct", idMetaproduct).append("enName", enName)
                .append("locName", locName).append("localization", localization).append("image", image).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(enName).append(localization).append(image).append(locName)
                .append(idMetaproduct).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metaproduct) == false) {
            return false;
        }
        Metaproduct rhs = ((Metaproduct) other);
        return new EqualsBuilder().append(enName, rhs.enName).append(localization, rhs.localization)
                .append(image, rhs.image).append(locName, rhs.locName).append(idMetaproduct, rhs.idMetaproduct)
                .isEquals();
    }

}
