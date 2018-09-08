package app.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Credentials {

    @SerializedName("mkmAppToken")
    @Expose
    private String mkmAppToken;
    @SerializedName("mkmAppSecret")
    @Expose
    private String mkmAppSecret;
    @SerializedName("mkmAccessToken")
    @Expose
    private String mkmAccessToken;
    @SerializedName("mkmAccessTokenSecret")
    @Expose
    private String mkmAccessTokenSecret;

    public String getMkmAppToken() {
        return mkmAppToken;
    }

    public void setMkmAppToken(String mkmAppToken) {
        this.mkmAppToken = mkmAppToken;
    }

    public String getMkmAppSecret() {
        return mkmAppSecret;
    }

    public void setMkmAppSecret(String mkmAppSecret) {
        this.mkmAppSecret = mkmAppSecret;
    }

    public String getMkmAccessToken() {
        return mkmAccessToken;
    }

    public void setMkmAccessToken(String mkmAccessToken) {
        this.mkmAccessToken = mkmAccessToken;
    }

    public String getMkmAccessTokenSecret() {
        return mkmAccessTokenSecret;
    }

    public void setMkmAccessTokenSecret(String mkmAccessTokenSecret) {
        this.mkmAccessTokenSecret = mkmAccessTokenSecret;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("mkmAppToken", mkmAppToken).append("mkmAppSecret", mkmAppSecret)
                .append("mkmAccessToken", mkmAccessToken).append("mkmAccessTokenSecret", mkmAccessTokenSecret)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(mkmAppToken).append(mkmAccessTokenSecret).append(mkmAccessToken)
                .append(mkmAppSecret).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Credentials) == false) {
            return false;
        }
        Credentials rhs = ((Credentials) other);
        return new EqualsBuilder().append(mkmAppToken, rhs.mkmAppToken)
                .append(mkmAccessTokenSecret, rhs.mkmAccessTokenSecret).append(mkmAccessToken, rhs.mkmAccessToken)
                .append(mkmAppSecret, rhs.mkmAppSecret).isEquals();
    }

}
