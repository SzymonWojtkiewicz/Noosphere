package controllers;

import appUtils.VideoGridCreator;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class HomeController
{
    @FXML
    private GridPane videosGridPane;

    public void initialize()
    {
        VideoGridCreator.FillGrid(this, videosGridPane);
    }
}
