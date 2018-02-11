package sample;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;
import sample.model.Pos;
import sample.service.TrackingService;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Main extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {


        final Group group = new Group();

        final Label inputX = new Label("Input target x:");
        final Label inputY = new Label("Input target y:");
        final TextField valueX = new TextField();
        final TextField valueY = new TextField();
        final Button ok = new Button("ok");
        final VBox vBox = new VBox();

        final Pane pane = new Pane();
        pane.setMinSize(600,300);
        pane.setMaxSize(600,300);
        final Circle circle = new Circle();
        circle.setRadius(5);
        circle.setFill(Color.RED);
        pane.getChildren().add(circle);

        vBox.getChildren().addAll(inputX, valueX, inputY, valueY, ok, pane);
        group.getChildren().add(vBox);

        final Scene scene = new Scene(group, 600, 400);

        stage.setScene(scene);
        stage.show();

        final TrackingService trackingService = new TrackingService();

        ok.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                final String valX = valueX.getText();
                final String valY = valueX.getText();
                if (!isEmpty(valX) && !isEmpty(valY)) {
                    try {
                        trackingService.justDoIt(Float.parseFloat(valX), Float.parseFloat(valY), circle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
