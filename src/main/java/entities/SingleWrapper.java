package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleWrapper {

@SerializedName("expansion")
@Expose
private Expansion expansion;
@SerializedName("single")
@Expose
private List<Single> single = null;
@SerializedName("links")
@Expose
private List<Link> links = null;

public Expansion getExpansion() {
return expansion;
}

public void setExpansion(Expansion expansion) {
this.expansion = expansion;
}

public List<Single> getSingle() {
return single;
}

public void setSingle(List<Single> single) {
this.single = single;
}

public List<Link> getLinks() {
return links;
}

public void setLinks(List<Link> links) {
this.links = links;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("expansion", expansion).append("single", single).append("links", links).toString();
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(single).append(expansion).append(links).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof SingleWrapper) == false) {
return false;
}
SingleWrapper rhs = ((SingleWrapper) other);
return new EqualsBuilder().append(single, rhs.single).append(expansion, rhs.expansion).append(links, rhs.links).isEquals();
}

}