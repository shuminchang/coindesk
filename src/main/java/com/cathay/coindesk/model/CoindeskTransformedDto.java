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

    @Override
    public String toString() {
        return "CoindeskTransformedDto{" +
                "updateTime='" + updateTime + '\'' +
                ", currencyList=" + currencyList +
                '}';
    }

    public static class CurrencyInfo {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        private BigDecimal rateFloat;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getRateFloat() {
            return rateFloat;
        }

        public void setRateFloat(BigDecimal rateFloat) {
            this.rateFloat = rateFloat;
        }

        @Override
        public String toString() {
            return "CurrencyInfo{" +
                    "code='" + code + '\'' +
                    ", symbol='" + symbol + '\'' +
                    ", rate='" + rate + '\'' +
                    ", description='" + description + '\'' +
                    ", rateFloat=" + rateFloat +
                    '}';
        }
    }
}
