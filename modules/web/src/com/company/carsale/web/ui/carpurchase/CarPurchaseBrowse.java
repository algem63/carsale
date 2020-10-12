package com.company.carsale.web.ui.carpurchase;

import java.util.Map;

import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.carsale.entity.CarPurchase;

public class CarPurchaseBrowse extends AbstractDocBrowser<CarPurchase> {

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "carsale$CarPurchase";
    }
}