package app.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCredentials {
    
    private static final Logger logger = LogManager.getLogger(UserCredentialsLoader.class);

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
        if ((other instanceof UserCredentials) == false) {
            return false;
        }
        UserCredentials rhs = ((UserCredentials) other);
        return new EqualsBuilder().append(mkmAppToken, rhs.mkmAppToken)
                .append(mkmAccessTokenSecret, rhs.mkmAccessTokenSecret).append(mkmAccessToken, rhs.mkmAccessToken)
                .append(mkmAppSecret, rhs.mkmAppSecret).isEquals();
    }

    
    /**
     * Verifies that credentials are not empty.
     * 
     * @return true if all fields are not empty, false otherwise.
     */
    boolean verifyCredentialsPresent() {
        if(null == mkmAppToken || mkmAppToken.trim().isEmpty()) {
            return false;
        }
        if(null == mkmAppSecret || mkmAppSecret.trim().isEmpty()) {
            return false;
        }
        if(null == mkmAccessToken || mkmAccessToken.trim().isEmpty()) {
            return false;
        }
        if(null == mkmAccessTokenSecret || mkmAccessTokenSecret.trim().isEmpty()) {
            return false;
        }
        return true;
    }
    
    
    /**
     * Verifies that credentials are not empty.
     * Use with caution. Will throw Exception if any of the credentail fields is empty.
     * 
     * @return  true if all fields are not empty.
     * @throws UserCredentialsNotPresentException if any of the credentials fields is empty.
     */
    boolean verifyCredentialsPresentWithException() throws UserCredentialsNotPresentException {
        if(!verifyCredentialsPresent()) {
            logger.warn("UserCredentials loaded and are empty!");
            throw new UserCredentialsLoadException("UserCredentials failed validation. One of the fields is empty");
        }        
        logger.debug("UserCredentials loaded and are not empty");
        return true;
    }
}
