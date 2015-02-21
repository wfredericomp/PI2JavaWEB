package com.br.index;
import javax.annotation.PostConstruct;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import br.com.model.bean.AgendaBean;
import br.com.model.bean.ProdutoBean;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private BarChartModel animatedModel1;
 
    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
 
    public BarChartModel getAnimatedModel1() {
        return animatedModel1;
    }
 
    private void createAnimatedModels() {
        animatedModel1 = initBarModel();
        animatedModel1.setTitle("Controle de lixos eletrônicos");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(20);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries agenda = new ChartSeries();
        agenda.setLabel("Agendamentos");
        agenda.set("Agendamentos de produtos de lixo eletrônicos", AgendaBean.getContAgendamentos());
 
        ChartSeries produto = new ChartSeries();
        produto.setLabel("Produtos coletados");
        produto.set("Produtos de lixo eletrônico", ProdutoBean.getContProdutos());
 
        model.addSeries(agenda);
        model.addSeries(produto);
         
        return model;
    }
    
}