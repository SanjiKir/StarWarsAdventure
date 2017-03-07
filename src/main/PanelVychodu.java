/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import logika.HerniPlan;
import util.ObserverZmenyProstoru;

/**
 *
 * @author wooow
 */
public class PanelVychodu implements ObserverZmenyProstoru{
    private HerniPlan plan;
    ListView<String> list;
    ObservableList<String> data;
    String[] oddeleneVychody;
  //  ComboBox<String> listVychodu;
    
    public PanelVychodu(HerniPlan plan) {
       this.plan = plan;
       plan.zaregistrujPozorovatele(this);
       init();
    }

    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
     //   listVychodu = new ComboBox<>();
        list.setItems(data);
        list.setPrefWidth(150);
      //  listVychodu.setItems(data);
         
        String vychody = plan.getAktualniProstor().seznamVychodu();
        oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
                        }
       
      // aktualizuj();
    }
    
     public ListView<String> getList() {
        return list;
    }
     /*
     public ComboBox<String> getComboBox(){
        return listVychodu;
    }
*/
     public void nastaveniHernihoPlanu(HerniPlan plan){
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        aktualizuj();
    }

    @Override
    public void aktualizuj() {
        String vychody = plan.getAktualniProstor().seznamVychodu();
        data.clear();
        oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }

    }
    public String[] getOddeleneVychody(){
        return oddeleneVychody;
    }

}

