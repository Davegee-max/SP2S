package GUI;

import Data.*;
import SerialPort.EKGSensor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Math.*;

public class GUIController implements EKGObserver {
    public TextArea EKGDataOutput;
    public TextField CPRField;
    public TextArea ÆgteEKGDataOutput;
    private boolean record;
    private EKGDAO ekgdao = new EKGDAOSQLImpl();
    public Polyline polyline;
    public boolean jatjak = true;

    public void StartDataCollection(ActionEvent actionEvent) {
        EKGObservable ekgSystem = new EKGGenerator();
        new Thread(ekgSystem).start();
        ekgSystem.register(this);

    }

    public void StartRecording(ActionEvent actionEvent) {
        this.record = !this.record;
    }

    public void notify(EKGDTO ekgdto) {
        // viser data
        String text = EKGDataOutput.getText();
        text +="New Data! Time: " +ekgdto.getTime() + ", EkgData: " + ekgdto.getEKG() + "\r\n";
        EKGDataOutput.setText(text);

        //gemmer data
        if(this.record){
            ekgdto.setCPR(CPRField.getText());
            ekgdao.save(ekgdto);
        }

    }

    public void TrykStart(ActionEvent actionEvent) {
        Parent startside = null;
        try {
            startside = FXMLLoader.load(getClass().getResource("/CollectorGui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startscene = new Scene(startside);
        Stage stagestart = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stagestart.setScene(startscene);
        stagestart.show();
    }

    public void TilbageTilStart(ActionEvent actionEvent) {
        Parent startside = null;
        try {
            startside = FXMLLoader.load(getClass().getResource("/Startside.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startscene = new Scene(startside);
        Stage stagestart = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stagestart.setScene(startscene);
        stagestart.show();
    }

    public void loadData(ActionEvent actionEvent) {
        Parent startside = null;
        try {
            startside = FXMLLoader.load(getClass().getResource("/LoadGui.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene startscene = new Scene(startside);
        Stage stagestart = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stagestart.setScene(startscene);
        stagestart.show();
    }

    public void ekgKnap(MouseEvent mouseEvent) {

        new Thread(new Runnable() {
            public void run() {
                if (jatjak){
                for (int i = 0; i < 40; i++) {

                    double y = 200 * Math.random()*cos(PI*2.45 - 50);


                    polyline.getPoints().addAll(i * 10.0,y) ;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }).start();
    }
    public void StopEKg(ActionEvent actionEvent) {
        jatjak = false;
    }

    public void HentEkgData(ActionEvent actionEvent) {
        EKGSensor ekgSensor = new EKGSensor();
        String text = ÆgteEKGDataOutput.getText();
        text +=  ekgSensor.hentData();
        ÆgteEKGDataOutput.setText(text);
    }
}
