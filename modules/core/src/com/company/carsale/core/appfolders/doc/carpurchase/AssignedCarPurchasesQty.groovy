import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.UserSessionSource

def currentUserId = AppBeans.get(UserSessionSource.class).getUserSession().getUser().getId()

return persistence.getEntityManager()
            .createQuery(
                    'select count(o) ' +
                    'from carsale\$CarPurchase o ' +
                    'join o.assignments a ' +
                    'where a.user.id = ?1 and a.finished is null')
            .setParameter(1, currentUserId)
            .getSingleResult()
