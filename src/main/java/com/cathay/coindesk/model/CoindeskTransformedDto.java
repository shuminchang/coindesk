package com.cathay.coindesk.model;

import java.math.BigDecimal;
import java.util.List;

public class CoindeskTransformedDto {
    private String updateTime;
    private List<CurrencyInfo> currencyList;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<CurrencyInfo> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyInfo> currencyList) {
        this.currencyList = currencyList;
    }

    public static class CurrencyInfo {
        private String code;

        private String name;

        private BigDecimal rateFloat;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public BigDecimal getRateFloat() {
            return rateFloat;
        }

        public void setRateFloat(BigDecimal rateFloat) {
            this.rateFloat = rateFloat;
        }

    }
}
