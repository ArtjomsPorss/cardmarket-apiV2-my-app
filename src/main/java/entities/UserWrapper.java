package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserWrapper {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("user", user).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(links).append(user).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserWrapper) == false) {
            return false;
        }
        UserWrapper rhs = ((UserWrapper) other);
        return new EqualsBuilder().append(links, rhs.links).append(user, rhs.user).isEquals();
    }

}
