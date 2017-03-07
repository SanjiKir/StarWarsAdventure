/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.HerniPlan;
import logika.IHra;
import logika.Inventory;
import logika.Vec;
import util.ObserverZmenyProstoru;

/**
 *
 * @author wooow
 */
public class OknoBatohu implements ObserverZmenyProstoru {

    private Inventory batoh;
    private HerniPlan plan;
    private ObservableList<String> data;
    private List<ImageView> imageView;
    private FlowPane flowPane;


    public OknoBatohu(HerniPlan plan) {
        this.plan = plan;
        flowPane = new FlowPane();
        imageView = new ArrayList<>();
        batoh = plan.getInventory();
        plan.zaregistrujPozorovatele(this);
        initGUI();
 
    }

    @Override
    public void aktualizuj() {
        batoh = plan.getInventory();
        flowPane.getChildren().clear();
        imageView.clear();
        List<Vec> listVeci = batoh.getInventoryList();
        for (Vec i : listVeci) {
            // System.out.println(i.getCestaKObrazku());
            ImageView img = new ImageView(new Image(i.getCestaKObrazku(), 100, 100, false, false));
            imageView.add(img);

        }

        flowPane.getChildren().addAll(imageView);
        
    }

    public void nastaveniHernihoPlanu(HerniPlan plan) {
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        this.aktualizuj();
    }

    private void initGUI() {
        List<Vec> listVeci = batoh.getInventoryList();
        for (Vec i : listVeci) {
            ImageView m = new ImageView(new Image(i.getCestaKObrazku()));
            imageView.add(m);
        }

        flowPane.getChildren().addAll(imageView);
        //   aktualizuj();
    }

  
    
    public FlowPane getFlowPane() {
        return flowPane;
    }

}
