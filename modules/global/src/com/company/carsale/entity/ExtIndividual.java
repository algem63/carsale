/*
 * Copyright (c) 2020 com.haulmont.thesis.core.entity
 */
package com.company.carsale.entity;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import com.haulmont.cuba.core.entity.annotation.Extends;
import javax.persistence.Column;

import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.chile.core.annotations.MetaProperty;
import javax.persistence.Transient;

/**
 * @author pasha
 */
@DiscriminatorValue("J")
@Extends(Individual.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "carsale$Individual")
@Listeners("carsale_ContractorListener")
public class ExtIndividual extends Individual {
    private static final long serialVersionUID = 6912286135612117523L;

    @Transient
    @MetaProperty
    protected Long carPurchaseCount;

    public void setCarPurchaseCount(Long carPurchaseCount) {
        this.carPurchaseCount = carPurchaseCount;
    }

    public Long getCarPurchaseCount() {
        return carPurchaseCount;
    }
}