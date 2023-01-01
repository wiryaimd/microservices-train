package com.wiryaimd.r_services2currencyexchange.model;

import java.math.BigDecimal;

public class CurrencyExchangeModel {

    private long id;
    private String from, to;
    private BigDecimal conversionMultiple;
    private String environment;

    public CurrencyExchangeModel(long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
