import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.UserSessionSource

def login = AppBeans.get(UserSessionSource.class).getUserSession().getUser().getLogin()

return persistence.getEntityManager()
        .createQuery(
                'select count(c) ' +
                'from carsale\$CarPurchase c ' +
                'where (c.proc is null or c.proc.name <> ?1) ' +
                '    and c.createdBy = ?2')
        .setParameter(1, "Утверждение заявки")
        .setParameter(2, login)
        .getSingleResult()