package com.company.carsale.web.ui.carpurchase;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.DatasourceListener;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.workflow.core.app.WfUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import com.haulmont.cuba.core.entity.Entity;

import java.util.Map;

import com.company.carsale.entity.CarPurchase;

import javax.inject.Inject;
import javax.inject.Named;

public class CarPurchaseEdit extends AbstractDocEditor<CarPurchase> {

    /*@Inject
    private Datasource cardDs;*/

    @Inject
    private Datasource<CarPurchase> cardDs;

    @Named("fieldGroup.isPaid")
    private Field isPaid;


    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        cardDs.addListener(new DatasourceListener<CarPurchase>() {
            @Override
            public void itemChanged(Datasource<CarPurchase> ds, CarPurchase prevItem, CarPurchase item) {
            }

            @Override
            public void stateChanged(Datasource<CarPurchase> ds, Datasource.State prevState, Datasource.State state) {
            }

            @Override
            public void valueChanged(CarPurchase source, String property, Object prevValue, Object value) {
                if ("isPaid".equals(property)) {
                    Boolean isPaid = (Boolean) value;
                    Action proverenoAct = actionsFrame.getButtonAction("Proverka_zayavki.Provereno");
                    if (BooleanUtils.isTrue(isPaid) && proverenoAct != null) {
                        proverenoAct.setEnabled(true);
                    }
                }
            }
        });
    }

    @Override
    public void ready() {
        super.ready();
        Action proverenoAct = actionsFrame.getButtonAction("Proverka_zayavki.Provereno");
        if (BooleanUtils.isNotTrue(getItem().getIsPaid()) && proverenoAct != null) {
            proverenoAct.setEnabled(false);
        }
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "correspondenceHistoryTab,docLogTab,cardLinksTab,processTab,securityTab,docTransferLogTab,cardProjectsTab,versionsTab,openHistoryTab";
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        printButton.addAction(new PrintReportAction("printExecutionList", this, "printDocExecutionListReportName"));
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

    @Override
    protected void fillHiddenTabs() {
        hiddenTabs.put("office", getMessage("office"));
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        hiddenTabs.put("docTreeTab", getMessage("docTreeTab"));
        hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        super.fillHiddenTabs();
    }
}