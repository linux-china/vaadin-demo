package org.mvnsearch.vaadin.chart;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * chart view
 *
 * @author linux_china
 */
@SpringView(name = ChartView.name)
public class ChartView extends VerticalLayout implements View {
    public final static String name = "chartview";

    @PostConstruct
    public void init() {
        Chart chart = new Chart(ChartType.COLUMN);
        chart.setWidth("400px"); // 100% by default
        chart.setHeight("300px"); // 400px by default
        addComponent(chart);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
