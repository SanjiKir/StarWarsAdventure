/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
public class PanelPlanet implements ObserverZmenyProstoru {

    private HerniPlan plan;
    ListView<String> list;
    ObservableList<String> data;
    String[] oddeleneVychody;
   // ComboBox<String> listVychodu;

    public PanelPlanet(HerniPlan plan) {
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        init();
    }

    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(100);
       // listVychodu.setItems(data);
        String vychody = plan.getPlanet().seznamVychodu();

        oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
        aktualizuj();
    }

    public ListView<String> getList() {
        return list;
    }

    public void nastaveniHernihoPlanu(HerniPlan plan) {
        this.plan = plan;
        plan.zaregistrujPozorovatele(this);
        aktualizuj();
    }
/*
    Undefined method. Working on it
    
    public ComboBox<String> getComboBox() {
        return listVychodu;
    }
*/
    @Override
    public void aktualizuj() {
        String vychody = plan.getPlanet().seznamVychodu();
        data.clear();
        oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
    }

    public String[] getOddeleneVychody() {
        return oddeleneVychody;
    }

}
