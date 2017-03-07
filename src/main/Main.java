/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.*;
import javafx.scene.web.WebView;

/**
 *
 * @author wooow
 */
public class Main extends Application {

    private TextArea centerTextArea;
    private IHra hra;
    private TextField prikazTextField;
    private BorderPane border;
    private OknoProstoru oknoProstoru;
    private PanelVychodu panelVychodu;
    private OknoBatohu oknoBatohu;
    private MenuBar menuBar;
    private PanelPlanet panelPlanet;
    private HBox hbox;
    private ComboBox<String> comboBox;
    private ListView<String> prostory;
    private ListView<String> planety;

    @Override
    public void start(Stage primaryStage) {
        hra = new Hra();
        border = new BorderPane();

        nastavTextArea();
        border.setCenter(centerTextArea);
       

        oknoProstoru = new OknoProstoru(hra.getHerniPlan());
        border.setTop(oknoProstoru.getPanel());

        panelVychodu = new PanelVychodu(hra.getHerniPlan());
        prostory = panelVychodu.getList();
        prostory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    String currentItemSelected = prostory.getSelectionModel()
                            .getSelectedItem();
                    String text = hra.zpracujPrikaz("go " + currentItemSelected);
                    centerTextArea.appendText("\n\n" + "go " + currentItemSelected + "\n");
                    centerTextArea.appendText("\n" + text + "\n");
                    prostory = panelVychodu.getList();
                 //   border.setRight(prostory);
                    prikazTextField.setText("");
                    if (hra.konecHry()) {
                        prikazTextField.setEditable(false);
                        comboBox.setDisable(true);
                    }

                }
            }
        });
        panelPlanet = new PanelPlanet(hra.getHerniPlan());

        planety = panelPlanet.getList();
        planety.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    String currentItemSelected = planety.getSelectionModel()
                            .getSelectedItem();
                    String text = hra.zpracujPrikaz("fly " + currentItemSelected);
                    centerTextArea.appendText("\n\n" + "fly " + currentItemSelected + "\n");
                    centerTextArea.appendText("\n" + text + "\n");
                    planety = panelPlanet.getList();
                  //  border.setRight(hbox);
                    prikazTextField.setText("");
                    if (hra.konecHry()) {
                        prikazTextField.setEditable(false);
                        comboBox.setDisable(true);
                    }

                }
            }
        });

        hbox = new HBox();
        hbox.getChildren().addAll(prostory, planety);
        border.setRight(hbox);

        oknoBatohu = new OknoBatohu(hra.getHerniPlan());
       // border.setLeft(oknoBatohu.getFlowPane());
     //   border.setBottom(nastavDolniPanel());
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(nastavDolniPanel(), oknoBatohu.getFlowPane());
        border.setBottom(vBox1);
         
        initMenu();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, border);

        Scene scene = new Scene(vBox, 1200, 720);

        primaryStage.setTitle("Star Wars");
        primaryStage.setScene(scene);
        prikazTextField.requestFocus();
        primaryStage.show();
    }

    private FlowPane nastavDolniPanel() {

        FlowPane dolniFlowPane = new FlowPane();
        dolniFlowPane.setAlignment(Pos.CENTER);
        comboBox = new ComboBox<>();

        comboBox.setPromptText("Choose command");
        List<String> prikazList = hra.getPrikazy();
        for (String s : prikazList) {
            comboBox.getItems().add(s);
        }

        prikazTextField = new TextField();
        prikazTextField.setPromptText("Enter next part here");
        prikazTextField.setOnAction(e -> {
            String radek = comboBox.getValue();
            String radek2 = prikazTextField.getText();
            String text = hra.zpracujPrikaz(radek + " " + radek2);
            centerTextArea.appendText("\n\n" + radek + "\n");
            centerTextArea.appendText("\n" + text + "\n");
            hbox.getChildren().clear();
            prostory = panelVychodu.getList();
            planety = panelPlanet.getList();
            hbox.getChildren().addAll(prostory, planety);
            border.setRight(hbox);

            prikazTextField.setText("");
            if (hra.konecHry()) {
                prikazTextField.setEditable(false);
                comboBox.setDisable(true);
                hbox.getChildren().get(0).setDisable(true);
                hbox.getChildren().get(1).setDisable(true);
            }
        });

        dolniFlowPane.getChildren().addAll(comboBox, prikazTextField);
        return dolniFlowPane;
    }

    private void nastavTextArea() {
        centerTextArea = new TextArea();
        centerTextArea.setText(hra.vratUvitani());
        centerTextArea.setEditable(false);
    }

    private void initMenu() {
        menuBar = new MenuBar();

        // --- Menu Soubor
        Menu menuSoubor = new Menu("File");

        //  MenuItem novaHra = new MenuItem("Nová hra");
        MenuItem novaHra = new MenuItem("New Game");
        //   new ImageView(new Image(AdventuraZakladni.class.getResourceAsStream("../images/sword.jpg"))));

        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        MenuItem konec = new MenuItem("_Quit");
        konec.setMnemonicParsing(true);
        konec.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                hra = new Hra();
                oknoProstoru.nastaveniHernihoPlanu(hra.getHerniPlan());
                panelVychodu.nastaveniHernihoPlanu(hra.getHerniPlan());
                panelPlanet.nastaveniHernihoPlanu(hra.getHerniPlan());
                oknoBatohu.nastaveniHernihoPlanu(hra.getHerniPlan());
                centerTextArea.setText(hra.vratUvitani());
                prikazTextField.setEditable(true);
                comboBox.setDisable(false);
                hbox.getChildren().get(0).setDisable(false);
                hbox.getChildren().get(1).setDisable(false);
                prikazTextField.requestFocus();
            }
        });

        menuSoubor.getItems().addAll(novaHra, new SeparatorMenuItem(), konec);

        Menu menuNapoveda = new Menu("Help");
        MenuItem oProgramu = new MenuItem("About program");
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události O programu
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Graphic game");
                alert.setHeaderText("JavaFX adventura");
                alert.setContentText("version ZS 2016");

                alert.showAndWait();
            }
        });

        MenuItem napovedaKAplikaci = new MenuItem("Help");
        napovedaKAplikaci.setAccelerator(KeyCombination.keyCombination("F1"));
        napovedaKAplikaci.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // obsluha události Nápověda k aplikaci
                // sekundární okno
                Stage stage = new Stage();
                stage.setTitle("Help");
                WebView webview = new WebView();
                webview.getEngine().load(Main.class.getResource("/images/napoveda.htm").toExternalForm()
                );
                stage.setScene(new Scene(webview, 500, 500));
                stage.show();
            }
        });

        menuNapoveda.getItems().addAll(oProgramu, new SeparatorMenuItem(), napovedaKAplikaci);

        menuBar.getMenus().addAll(menuSoubor, menuNapoveda);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else if (args[0].equals("-text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            System.out.println("wrong param");
        }

    }

}
