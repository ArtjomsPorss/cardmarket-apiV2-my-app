package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersWrapper {

    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("users", users).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(users).append(links).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UsersWrapper) == false) {
            return false;
        }
        UsersWrapper rhs = ((UsersWrapper) other);
        return new EqualsBuilder().append(users, rhs.users).append(links, rhs.links).isEquals();
    }

}
