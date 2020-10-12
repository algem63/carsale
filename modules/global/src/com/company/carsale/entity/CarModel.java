/*
 * Copyright (c) 2020 com.company.carsale.entity
 */
package com.company.carsale.entity;


/**
 * @author pasha
 */
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;

@NamePattern("%s|name")
@Table(name = "CARSALE_CAR_MODEL")
@Entity(name = "carsale$CarModel")
@EnableRestore
@TrackEditScreenHistory
public class CarModel extends StandardEntity {
    private static final long serialVersionUID = -7552106200750995450L;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Column(name = "CODE", nullable = false, length = 50)
    protected Integer code;

    @Column(name = "DESCRIPTION", length = 250)
    protected String description;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}