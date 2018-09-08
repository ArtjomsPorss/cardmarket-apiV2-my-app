package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("idUser")
    @Expose
    private Integer idUser;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("isCommercial")
    @Expose
    private Integer isCommercial;
    @SerializedName("maySell")
    @Expose
    private Boolean maySell;
    @SerializedName("sellerActivation")
    @Expose
    private Integer sellerActivation;
    @SerializedName("riskGroup")
    @Expose
    private Integer riskGroup;
    @SerializedName("lossPercentage")
    @Expose
    private String lossPercentage;
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
    @SerializedName("idDisplayLanguage")
    @Expose
    private Integer idDisplayLanguage;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("homeAddress")
    @Expose
    private HomeAddress homeAddress;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("vat")
    @Expose
    private String vat;
    @SerializedName("legalInformation")
    @Expose
    private String legalInformation;
    @SerializedName("registerDate")
    @Expose
    private String registerDate;
    @SerializedName("isActivated")
    @Expose
    private Boolean isActivated;
    @SerializedName("moneyDetails")
    @Expose
    private MoneyDetails moneyDetails;
    @SerializedName("bankAccount")
    @Expose
    private BankAccount bankAccount;
    @SerializedName("articlesInShoppingCart")
    @Expose
    private Integer articlesInShoppingCart;
    @SerializedName("unreadMessages")
    @Expose
    private Integer unreadMessages;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getIsCommercial() {
        return isCommercial;
    }

    public void setIsCommercial(Integer isCommercial) {
        this.isCommercial = isCommercial;
    }

    public Boolean getMaySell() {
        return maySell;
    }

    public void setMaySell(Boolean maySell) {
        this.maySell = maySell;
    }

    public Integer getSellerActivation() {
        return sellerActivation;
    }

    public void setSellerActivation(Integer sellerActivation) {
        this.sellerActivation = sellerActivation;
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

    public Integer getIdDisplayLanguage() {
        return idDisplayLanguage;
    }

    public void setIdDisplayLanguage(Integer idDisplayLanguage) {
        this.idDisplayLanguage = idDisplayLanguage;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public MoneyDetails getMoneyDetails() {
        return moneyDetails;
    }

    public void setMoneyDetails(MoneyDetails moneyDetails) {
        this.moneyDetails = moneyDetails;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getArticlesInShoppingCart() {
        return articlesInShoppingCart;
    }

    public void setArticlesInShoppingCart(Integer articlesInShoppingCart) {
        this.articlesInShoppingCart = articlesInShoppingCart;
    }

    public Integer getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(Integer unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("idUser", idUser).append("username", username)
                .append("country", country).append("isCommercial", isCommercial).append("maySell", maySell)
                .append("sellerActivation", sellerActivation).append("riskGroup", riskGroup)
                .append("lossPercentage", lossPercentage).append("reputation", reputation)
                .append("shipsFast", shipsFast).append("sellCount", sellCount).append("soldItems", soldItems)
                .append("avgShippingTime", avgShippingTime).append("onVacation", onVacation)
                .append("idDisplayLanguage", idDisplayLanguage).append("name", name).append("homeAddress", homeAddress)
                .append("email", email).append("phoneNumber", phoneNumber).append("vat", vat)
                .append("legalInformation", legalInformation).append("registerDate", registerDate)
                .append("isActivated", isActivated).append("moneyDetails", moneyDetails)
                .append("bankAccount", bankAccount).append("articlesInShoppingCart", articlesInShoppingCart)
                .append("unreadMessages", unreadMessages).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(legalInformation).append(avgShippingTime).append(registerDate).append(vat)
                .append(username).append(soldItems).append(phoneNumber).append(homeAddress).append(name)
                .append(reputation).append(lossPercentage).append(isCommercial).append(unreadMessages)
                .append(onVacation).append(articlesInShoppingCart).append(idDisplayLanguage).append(country)
                .append(sellerActivation).append(isActivated).append(maySell).append(shipsFast).append(email)
                .append(idUser).append(moneyDetails).append(sellCount).append(riskGroup).append(bankAccount)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Account) == false) {
            return false;
        }
        Account rhs = ((Account) other);
        return new EqualsBuilder().append(legalInformation, rhs.legalInformation)
                .append(avgShippingTime, rhs.avgShippingTime).append(registerDate, rhs.registerDate)
                .append(vat, rhs.vat).append(username, rhs.username).append(soldItems, rhs.soldItems)
                .append(phoneNumber, rhs.phoneNumber).append(homeAddress, rhs.homeAddress).append(name, rhs.name)
                .append(reputation, rhs.reputation).append(lossPercentage, rhs.lossPercentage)
                .append(isCommercial, rhs.isCommercial).append(unreadMessages, rhs.unreadMessages)
                .append(onVacation, rhs.onVacation).append(articlesInShoppingCart, rhs.articlesInShoppingCart)
                .append(idDisplayLanguage, rhs.idDisplayLanguage).append(country, rhs.country)
                .append(sellerActivation, rhs.sellerActivation).append(isActivated, rhs.isActivated)
                .append(maySell, rhs.maySell).append(shipsFast, rhs.shipsFast).append(email, rhs.email)
                .append(idUser, rhs.idUser).append(moneyDetails, rhs.moneyDetails).append(sellCount, rhs.sellCount)
                .append(riskGroup, rhs.riskGroup).append(bankAccount, rhs.bankAccount).isEquals();
    }

}