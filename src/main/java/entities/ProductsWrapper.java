package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsWrapper {

    @SerializedName("product")
    @Expose
    private List<Product> product;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
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
        if ((other instanceof ProductsWrapper) == false) {
            return false;
        }
        ProductsWrapper rhs = ((ProductsWrapper) other);
        return new EqualsBuilder().append(product, rhs.product).isEquals();
    }

}