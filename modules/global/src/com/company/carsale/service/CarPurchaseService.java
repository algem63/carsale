/*
 * Copyright (c) 2020 com.company.carsale.service
 */
package com.company.carsale.service;

import java.util.UUID;

/**
 * @author pasha
 */
public interface CarPurchaseService {
    String NAME = "carsale_CarPurchaseService";

    long getCarPurchaseCountForContractor(UUID contractorId);
}