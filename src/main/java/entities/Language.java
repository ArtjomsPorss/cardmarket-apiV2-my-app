package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("idLanguage")
    @Expose
    private Integer idLanguage;
    @SerializedName("languageName")
    @Expose
    private String languageName;

    public Integer getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(Integer idLanguage) {
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
        return new ToStringBuilder(this).append("idLanguage", idLanguage).append("languageName", languageName)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(idLanguage).append(languageName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Language) == false) {
            return false;
        }
        Language rhs = ((Language) other);
        return new EqualsBuilder().append(idLanguage, rhs.idLanguage).append(languageName, rhs.languageName).isEquals();
    }

}