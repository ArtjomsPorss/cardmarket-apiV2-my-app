package entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankAccount {

    @SerializedName("accountOwner")
    @Expose
    private String accountOwner;
    @SerializedName("iban")
    @Expose
    private String iban;
    @SerializedName("bic")
    @Expose
    private String bic;
    @SerializedName("bankName")
    @Expose
    private String bankName;

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("accountOwner", accountOwner).append("iban", iban).append("bic", bic)
                .append("bankName", bankName).toString();
    }

}
