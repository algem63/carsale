/*
 * Copyright (c) 2020 com.company.carsale.core.app.reassignment.commands
 */
package com.company.carsale.core.app.reassignment.commands;


import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.company.carsale.entity.CarPurchase;

/**
 * @author pasha
 */
@ManagedBean(CarPurchaseReassignmentCommand.NAME)
public class CarPurchaseReassignmentCommand extends AbstractDocReassignmentCommand<CarPurchase> {
    protected static final String NAME = "carpurchase_reassignment_command";

    @PostConstruct
    protected void postInit() {
        type = "CarPurchase";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "carsale$CarPurchase");
    }

    @Override
    public String getName() {
        return NAME;
    }
}