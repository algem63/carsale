package com.company.carsale.service;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

/**
 * @author pasha
 */
@Service(CarPurchaseService.NAME)
public class CarPurchaseServiceBean implements CarPurchaseService {

    @Inject
    private Persistence persistence;

    @Override
    public long getCarPurchaseCountForContractor(UUID contractorId) {
        long count;
        Transaction transaction = persistence.createTransaction();
        try {
            count = (long) persistence.getEntityManager()
                    .createQuery("select count(e) from carsale$CarPurchase e where e.customer.id = ?1")
                    .setParameter(1, contractorId)
                    .getSingleResult();
            transaction.commit();
        } finally {
            transaction.end();
        }
        return count;
    }
}