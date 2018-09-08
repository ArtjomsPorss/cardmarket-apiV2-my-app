package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seller {

    @SerializedName("idUser")
    @Expose
    private Integer idUser;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("registrationDate")
    @Expose
    private String registrationDate;
    @SerializedName("isCommercial")
    @Expose
    private Integer isCommercial;
    @SerializedName("isSeller")
    @Expose
    private Boolean isSeller;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("vat")
    @Expose
    private String vat;
    @SerializedName("legalInformation")
    @Expose
    private String legalInformation;
    @SerializedName("riskGroup")
    @Expose
    private Integer riskGroup;
    @SerializedName("lossPercentage")
    @Expose
    private String lossPercentage;
    @SerializedName("unsentShipments")
    @Expose
    private Integer unsentShipments;
    @SerializedName("reputation")
    @Expose
    private Integer reputation;
    @SerializedName("shipsFast")
    @Expose
    private Integer shipsFast;
    @SerializedName("sellCount")
    @Expose
    private Integer sellCount;
    @SerializedName("soldItems")
    @Expose
    private Integer soldItems;
    @SerializedName("avgShippingTime")
    @Expose
    private Integer avgShippingTime;
    @SerializedName("onVacation")
    @Expose
    private Boolean onVacation;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getIsCommercial() {
        return isCommercial;
    }

    public void setIsCommercial(Integer isCommercial) {
        this.isCommercial = isCommercial;
    }

    public Boolean getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(Boolean isSeller) {
        this.isSeller = isSeller;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getLegalInformation() {
        return legalInformation;
    }

    public void setLegalInformation(String legalInformation) {
        this.legalInformation = legalInformation;
    }

    public Integer getRiskGroup() {
        return riskGroup;
    }

    public void setRiskGroup(Integer riskGroup) {
        this.riskGroup = riskGroup;
    }

    public String getLossPercentage() {
        return lossPercentage;
    }

    public void setLossPercentage(String lossPercentage) {
        this.lossPercentage = lossPercentage;
    }

    public Integer getUnsentShipments() {
        return unsentShipments;
    }

    public void setUnsentShipments(Integer unsentShipments) {
        this.unsentShipments = unsentShipments;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Integer getShipsFast() {
        return shipsFast;
    }

    public void setShipsFast(Integer shipsFast) {
        this.shipsFast = shipsFast;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public Integer getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(Integer soldItems) {
        this.soldItems = soldItems;
    }

    public Integer getAvgShippingTime() {
        return avgShippingTime;
    }

    public void setAvgShippingTime(Integer avgShippingTime) {
        this.avgShippingTime = avgShippingTime;
    }

    public Boolean getOnVacation() {
        return onVacation;
    }

    public void setOnVacation(Boolean onVacation) {
        this.onVacation = onVacation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idUser", idUser).append("username", username)
                .append("registrationDate", registrationDate).append("isCommercial", isCommercial)
                .append("isSeller", isSeller).append("name", name).append("address", address).append("phone", phone)
                .append("email", email).append("vat", vat).append("legalInformation", legalInformation)
                .append("riskGroup", riskGroup).append("lossPercentage", lossPercentage)
                .append("unsentShipments", unsentShipments).append("reputation", reputation)
                .append("shipsFast", shipsFast).append("sellCount", sellCount).append("soldItems", soldItems)
                .append("avgShippingTime", avgShippingTime).append("onVacation", onVacation).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(onVacation).append(phone).append(avgShippingTime).append(legalInformation)
                .append(vat).append(username).append(soldItems).append(registrationDate).append(shipsFast).append(email)
                .append(address).append(unsentShipments).append(reputation).append(name).append(lossPercentage)
                .append(isCommercial).append(idUser).append(sellCount).append(isSeller).append(riskGroup).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Seller) == false) {
            return false;
        }
        Seller rhs = ((Seller) other);
        return new EqualsBuilder().append(onVacation, rhs.onVacation).append(phone, rhs.phone)
                .append(avgShippingTime, rhs.avgShippingTime).append(legalInformation, rhs.legalInformation)
                .append(vat, rhs.vat).append(username, rhs.username).append(soldItems, rhs.soldItems)
                .append(registrationDate, rhs.registrationDate).append(shipsFast, rhs.shipsFast)
                .append(email, rhs.email).append(address, rhs.address).append(unsentShipments, rhs.unsentShipments)
                .append(reputation, rhs.reputation).append(name, rhs.name).append(lossPercentage, rhs.lossPercentage)
                .append(isCommercial, rhs.isCommercial).append(idUser, rhs.idUser).append(sellCount, rhs.sellCount)
                .append(isSeller, rhs.isSeller).append(riskGroup, rhs.riskGroup).isEquals();
    }

}