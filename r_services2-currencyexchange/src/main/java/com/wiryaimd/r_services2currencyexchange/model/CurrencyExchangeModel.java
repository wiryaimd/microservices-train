package com.wiryaimd.r_services2currencyexchange.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "_currency_exchange")
public class CurrencyExchangeModel {

    @Id
    private long id;

    @Column(name = "converstion_from")
    private String from;

    @Column(name = "converstion_to")
    private String to;

    private BigDecimal conversionMultiple;
    private String environment;

    public CurrencyExchangeModel() {
    }

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
