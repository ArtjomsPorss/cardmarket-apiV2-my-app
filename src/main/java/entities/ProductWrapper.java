package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductWrapper {

    @SerializedName("product")
    @Expose
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("product", product).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(product).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductWrapper) == false) {
            return false;
        }
        ProductWrapper rhs = ((ProductWrapper) other);
        return new EqualsBuilder().append(product, rhs.product).isEquals();
    }

}