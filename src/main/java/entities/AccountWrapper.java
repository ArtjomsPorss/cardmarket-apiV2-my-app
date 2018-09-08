package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountWrapper {

    @SerializedName("account")
    @Expose
    private Account account;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("account", account).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(account).append(links).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AccountWrapper) == false) {
            return false;
        }
        AccountWrapper rhs = ((AccountWrapper) other);
        return new EqualsBuilder().append(account, rhs.account).append(links, rhs.links).isEquals();
    }

}