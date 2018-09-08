package entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoneyDetails {

    @SerializedName("totalBalance")
    @Expose
    private Double totalBalance;
    @SerializedName("moneyBalance")
    @Expose
    private Double moneyBalance;
    @SerializedName("bonusBalance")
    @Expose
    private Double bonusBalance;
    @SerializedName("unpaidAmount")
    @Expose
    private Double unpaidAmount;
    @SerializedName("providerRechargeAmount")
    @Expose
    private Double providerRechargeAmount;

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(Double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public Double getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(Double bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public Double getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(Double unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public Double getProviderRechargeAmount() {
        return providerRechargeAmount;
    }

    public void setProviderRechargeAmount(Double providerRechargeAmount) {
        this.providerRechargeAmount = providerRechargeAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("totalBalance", totalBalance).append("moneyBalance", moneyBalance)
                .append("bonusBalance", bonusBalance).append("unpaidAmount", unpaidAmount)
                .append("providerRechargeAmount", providerRechargeAmount).toString();
    }

}
