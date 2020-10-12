package com.company.carsale.web.ui.car;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.thesis.core.entity.TsCard;
import com.haulmont.thesis.web.ui.basic.editor.AbstractCardEditor;
import com.haulmont.workflow.core.app.WfAssignmentService;
import com.haulmont.workflow.core.app.WfService;
import com.haulmont.workflow.core.app.WfUtils;
import com.haulmont.workflow.core.global.WfConfig;
import com.haulmont.workflow.gui.base.action.WfForm;
import org.apache.commons.lang.StringUtils;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.thesis.core.entity.Numerator;
import com.haulmont.thesis.core.app.NumerationService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.PersistenceHelper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CarEdit<T extends TsCard> extends AbstractCardEditor<T> {

    @Inject
    protected NumerationService numerationService;
    @Inject
    protected Messages messages;
    @Named("fieldGroup.yearOfRelease")
    protected Field yearOfRelease;
    WfService wfService;
    WfUtils wfUtils;
    WfAssignmentService wfAssignmentService;
    WfConfig wfConfig;
    WfForm form;


    @Override
    protected void postInit() {
        yearOfRelease.addValidator(new Field.Validator() {
            @Override
            public void validate(Object value) throws ValidationException {
                Integer releaseYear = (Integer) value;
                Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
                if (releaseYear.compareTo(currentYear) > 0) {
                    throw new ValidationException(
                            messages.getMessage(this.getClass(), "currentYearValidationErrorMessage")
                    );
                }
            }
        });
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "securityTab,cardLogTab,processTab";
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        if (isNumberAssignNeeded())
            setNumber(item);
        
    }

    protected boolean isNumberAssignNeeded() {
        return true;
    }

    protected void setNumber(Entity item) {
        LoadContext ctx = new LoadContext(Numerator.class);
        if (PersistenceHelper.isNew(item) && item.getMetaClass().getProperty("number") != null) {
            ctx.setQueryString("select n from df$Numerator n where n.code=:name").setParameter("name",
                    getItem().getClass().getSimpleName() + "Numerator");
            List<Numerator> numeratorList = getDsContext().getDataSupplier().loadList(ctx);
            if (!numeratorList.isEmpty()) {
                Numerator numerator = numeratorList.get(0);
                HashMap<String, Object> params = new HashMap<>();
                params.put("entity", getItem());
                item.setValue("number", numerationService.getNextNumber(numerator, params));
            }
        }
    }

    @Override
    protected Container getStateHolder() {
        return getComponent("stateHolder");
    }

    @Override
    protected Component createState() {
        if (WfUtils.isCardInState(getItem(), "New") || StringUtils.isEmpty(getItem().getState())) {
            Label label = componentsFactory.createComponent(Label.NAME);
            label.setValue(StringUtils.isEmpty(getItem().getState()) ? "" : getItem().getLocState());
            return label;
        } else {
            return super.createState();
        }
     }
}