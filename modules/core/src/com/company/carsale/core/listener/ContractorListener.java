/*
 * Copyright (c) 2020 com.company.carsale.core.listener
 */
package com.company.carsale.core.listener;

import com.company.carsale.entity.ExtCompany;
import com.company.carsale.entity.ExtIndividual;
import com.company.carsale.service.CarPurchaseService;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.BeforeDetachEntityListener;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.thesis.core.entity.Contractor;

import javax.inject.Inject;
import java.util.UUID;

/**
 * @author pasha
 */
@Component("carsale_ContractorListener")
public class ContractorListener implements BeforeDetachEntityListener<Contractor> {

    @Inject
    private CarPurchaseService carPurchaseService;

    @Override
    public void onBeforeDetach(Contractor entity, EntityManager entityManager) {
        UUID customerId = entity.getId();
        long count = carPurchaseService.getCarPurchaseCountForContractor(customerId);
        if (entity instanceof ExtCompany) {
            ExtCompany company = (ExtCompany) entity;
            company.setCarPurchaseCount(count);
        } else if (entity instanceof ExtIndividual) {
            ExtIndividual individual = (ExtIndividual) entity;
            individual.setCarPurchaseCount(count);
        }
    }
}