package entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MetaproductsWrapper {

@SerializedName("metaproduct")
@Expose
private List<MetaproductWrapper> metaproduct = null;

public List<MetaproductWrapper> getMetaproduct() {
return metaproduct;
}

public void setMetaproduct(List<MetaproductWrapper> metaproduct) {
this.metaproduct = metaproduct;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("metaproduct", metaproduct).toString();
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(metaproduct).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof MetaproductsWrapper) == false) {
return false;
}
MetaproductsWrapper rhs = ((MetaproductsWrapper) other);
return new EqualsBuilder().append(metaproduct, rhs.metaproduct).isEquals();
}

}