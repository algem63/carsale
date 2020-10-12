/*
 * Copyright (c) 2020 com.company.carsale.entity
 */
package com.company.carsale.entity;


/**
 * @author pasha
 */
import com.company.carsale.enums.CarType;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.haulmont.thesis.core.entity.TsCard;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Listeners("carsale_CarListener")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("1")
@Table(name = "CARSALE_CAR")
@Entity(name = "carsale$Car")
@EnableRestore
@TrackEditScreenHistory
public class Car extends TsCard {
    private static final long serialVersionUID = 2544824832070887261L;

    @Column(name = "NUMBER_", length = 50)
    protected String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_MODEL_ID")
    protected CarModel carModel;

    @Column(name = "NAME", length = 250)
    protected String name;

    @Column(name = "YEAR_OF_RELEASE")
    protected Integer yearOfRelease;

    @Column(name = "PRICE", length = 50)
    protected BigDecimal price;

    @Column(name = "CAR_TYPE")
    protected Integer carType;

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }


    public void setCarType(CarType carType) {
        this.carType = carType == null ? null : carType.getId();
    }

    public CarType getCarType() {
        return carType == null ? null : CarType.fromId(carType);
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }


}