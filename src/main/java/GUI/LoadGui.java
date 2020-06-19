package GUI;

import Data.EKGDAO;
import Data.EKGDAOSQLImpl;
import Data.EKGDTO;
import Data.SQLConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class LoadGui {
    public DatePicker datePicker;
    public TextArea EKGDataArea;

    public void LoadData(ActionEvent actionEvent) {
    LocalDateTime localDateTime = datePicker.getValue().atStartOfDay();
    Timestamp time = Timestamp.valueOf(localDateTime);
        EKGDAO ekgdao = new EKGDAOSQLImpl();
       List<EKGDTO> ekgData = ekgdao.load(time);
        String text ="";
        for(EKGDTO data: ekgData) {
           text += "Time: " + data.getTime() + " CPR: " +  data.getCPR() + " EKG: " +data.getEKG() + "\r\n";
           EKGDataArea.setText(text);
        }
        }




    public void Tilbage(ActionEvent actionEvent) {
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
}
