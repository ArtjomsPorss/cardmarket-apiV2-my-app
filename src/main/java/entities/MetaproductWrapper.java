package entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaproductWrapper {

    @SerializedName("metaproduct")
    @Expose
    private Metaproduct metaproduct;
    @SerializedName("product")
    @Expose
    private List<Product> product = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public Metaproduct getMetaproduct() {
        return metaproduct;
    }

    public void setMetaproduct(Metaproduct metaproduct) {
        this.metaproduct = metaproduct;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("metaproduct", metaproduct).append("product", product)
                .append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(product).append(links).append(metaproduct).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MetaproductWrapper) == false) {
            return false;
        }
        MetaproductWrapper rhs = ((MetaproductWrapper) other);
        return new EqualsBuilder().append(product, rhs.product).append(links, rhs.links)
                .append(metaproduct, rhs.metaproduct).isEquals();
    }

}
