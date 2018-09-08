package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reprint {

@SerializedName("idProduct")
@Expose
private Integer idProduct;
@SerializedName("expansion")
@Expose
private String expansion;
@SerializedName("expIcon")
@Expose
private Integer expIcon;

public Integer getIdProduct() {
return idProduct;
}

public void setIdProduct(Integer idProduct) {
this.idProduct = idProduct;
}

public String getExpansion() {
return expansion;
}

public void setExpansion(String expansion) {
this.expansion = expansion;
}

public Integer getExpIcon() {
return expIcon;
}

public void setExpIcon(Integer expIcon) {
this.expIcon = expIcon;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("idProduct", idProduct).append("expansion", expansion).append("expIcon", expIcon).toString();
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(expIcon).append(expansion).append(idProduct).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Reprint) == false) {
return false;
}
Reprint rhs = ((Reprint) other);
return new EqualsBuilder().append(expIcon, rhs.expIcon).append(expansion, rhs.expansion).append(idProduct, rhs.idProduct).isEquals();
}

}