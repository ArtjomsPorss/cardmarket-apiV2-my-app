package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpansionWrapper {

@SerializedName("expansion")
@Expose
private List<Expansion> expansion = null;
@SerializedName("links")
@Expose
private List<Link> links = null;

public List<Expansion> getExpansion() {
return expansion;
}

public void setExpansion(List<Expansion> expansion) {
this.expansion = expansion;
}

public List<Link> getLinks() {
return links;
}

public void setLinks(List<Link> links) {
this.links = links;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("expansion", expansion).append("links", links).toString();
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(expansion).append(links).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof ExpansionWrapper) == false) {
return false;
}
ExpansionWrapper rhs = ((ExpansionWrapper) other);
return new EqualsBuilder().append(expansion, rhs.expansion).append(links, rhs.links).isEquals();
}

}