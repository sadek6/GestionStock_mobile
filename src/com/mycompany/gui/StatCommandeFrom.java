/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.entities.Commande;
import com.mycompany.services.ServiceCommande;
import java.util.ArrayList;

/**
 *
 * @author Mohamed
 */
public class StatCommandeFrom extends Form {

    Form f;
    
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
//        int k = 0;
//        for (double value : values) {
//            series.add("Project " + ++k, value);
//        }
        
        series.add("Commande en cours",values[0]);
        series.add("Commande traite",values[1]);

        return series;
    }
    
    public Form createPieChartForm() {
        // Generate the values
        System.out.println("stt");
      

         ArrayList<Commande> commandes = ServiceCommande.getInstance().getAllCommandeClient(1);
         int qtedispo=0,qtevendu=0;
         for (Commande commande : commandes) {
             if(commande.getEtat().equals("en cours"))
                 qtedispo+=1;
             else
                 qtevendu+=1;
             
             //qtedispo+=produit.getQteDispoProduit();
             //qtevendu+=produit.getQteVenduProduit();
            
        }
         
           double[] values = new double[]{qtedispo, qtevendu};
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN, 2};
        DefaultRenderer renderer = buildCategoryRenderer(colors);

        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Etat Commande", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        f = new Form("Mes Commandes", new BorderLayout());
        f.add(BorderLayout.CENTER, c);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm().show());
        return f;

    }
    
    public void StatistiqueTest() {

        f = createPieChartForm();
        f.show();

    }

}
