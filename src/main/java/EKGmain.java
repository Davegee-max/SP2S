
import SerialPort.EKGSensor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EKGmain extends Application {
    private static EKGSensor ekgSensor;


    public static void main(String[] args) throws InterruptedException {
        launch(args);
        ekgSensor = new EKGSensor();
        while (true) {
            ekgSensor.hentData();

            /*
                final PC pc = new PC();

                // Create producer thread
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            pc.produce();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

                // Create consumer thread
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            pc.consume();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

                // Start both threads
                t1.start();
                t2.start();

                // t1 finishes before t2
                t1.join();
                t2.join();*/
            }
        }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Startside.fxml"));
        Parent load = null;
        try {
            load = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("EKG LAUNCHER 2000");
        primaryStage.setScene(new Scene(load));
        primaryStage.show();

    }
}


