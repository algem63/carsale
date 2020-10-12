/*
 * Copyright (c) 2020 com.haulmont.thesis.core.entity
 */
package com.company.carsale.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.thesis.core.entity.Company;

/**
 * @author pasha
 */
@DiscriminatorValue("С")
@Extends(Company.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "carsale$Company")
@Listeners("carsale_ContractorListener")
public class ExtCompany extends Company {
    private static final long serialVersionUID = 5970039901280529642L;

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