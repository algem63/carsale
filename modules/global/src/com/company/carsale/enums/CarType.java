/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */
package com.company.carsale.enums;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;

/**
 * @author pasha
 */
public enum CarType implements EnumClass<Integer> {

    CROSSOVER(1),
    UNIVERSAL(2),
    SEDAN(3);

    private Integer id;

    CarType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static CarType fromId(Integer id) {
        for (CarType at : CarType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}