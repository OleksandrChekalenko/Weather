import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//Фігури
//Arc
//Circle
//CubicCurve
//Ellipse
//Line
//Path
//Polygon
//Polyline
//QuadCurve
//Rectangle
//SVGPath
//Text

public class App extends Application {

    public static Group root = new Group();

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        creatingBtn();

        Scene scene = new Scene(root, 600, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void creatingBtn() {
        Button button = new Button("Погода");
        button.setLayoutX(275);
        button.setLayoutY(200);
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parser.main1();
            }
        });
        root.getChildren().add(button);
    }
}
