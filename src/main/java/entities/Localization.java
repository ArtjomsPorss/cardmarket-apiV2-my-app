package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Localization {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("idLanguage")
    @Expose
    private String idLanguage;
    @SerializedName("languageName")
    @Expose
    private String languageName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(String idLanguage) {
        this.idLanguage = idLanguage;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("idLanguage", idLanguage)
                .append("languageName", languageName).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(idLanguage).append(name).append(languageName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Localization) == false) {
            return false;
        }
        Localization rhs = ((Localization) other);
        return new EqualsBuilder().append(idLanguage, rhs.idLanguage).append(name, rhs.name)
                .append(languageName, rhs.languageName).isEquals();
    }

}
