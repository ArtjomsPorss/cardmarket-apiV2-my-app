package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceGuide {

    @SerializedName("SELL")
    @Expose
    private Double SELL;
    @SerializedName("LOW")
    @Expose
    private Double LOW;
    @SerializedName("LOWEX")
    @Expose
    private Double LOWEX;
    @SerializedName("LOWFOIL")
    @Expose
    private Integer LOWFOIL;
    @SerializedName("AVG")
    @Expose
    private Double AVG;
    @SerializedName("TREND")
    @Expose
    private Double TREND;

    public Double getSELL() {
        return SELL;
    }

    public void setSELL(Double sELL) {
        this.SELL = sELL;
    }

    public Double getLOW() {
        return LOW;
    }

    public void setLOW(Double lOW) {
        this.LOW = lOW;
    }

    public Double getLOWEX() {
        return LOWEX;
    }

    public void setLOWEX(Double lOWEX) {
        this.LOWEX = lOWEX;
    }

    public Integer getLOWFOIL() {
        return LOWFOIL;
    }

    public void setLOWFOIL(Integer lOWFOIL) {
        this.LOWFOIL = lOWFOIL;
    }

    public Double getAVG() {
        return AVG;
    }

    public void setAVG(Double aVG) {
        this.AVG = aVG;
    }

    public Double getTREND() {
        return TREND;
    }

    public void setTREND(Double tREND) {
        this.TREND = tREND;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("SELL", SELL).append("LOW", LOW).append("LOWEX", LOWEX)
                .append("LOWFOIL", LOWFOIL).append("AVG", AVG).append("TREND", TREND).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(LOWEX).append(SELL).append(AVG).append(LOWFOIL).append(LOW).append(TREND)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PriceGuide) == false) {
            return false;
        }
        PriceGuide rhs = ((PriceGuide) other);
        return new EqualsBuilder().append(LOWEX, rhs.LOWEX).append(SELL, rhs.SELL).append(AVG, rhs.AVG)
                .append(LOWFOIL, rhs.LOWFOIL).append(LOW, rhs.LOW).append(TREND, rhs.TREND).isEquals();
    }

}