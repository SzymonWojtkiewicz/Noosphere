package controllers;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import javafx.scene.input.MouseEvent;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.util.Duration;


public class MediaController implements Initializable{

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton, pauseButton, rewatchButton;

    @FXML
    private Slider progressBar;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;



    public void initialize(URL arg0, ResourceBundle arg1) {


        file = new File("src/main/resources/videos/video.mp4");

        media = new Media(file.toURI().toString());

        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);


        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                progressBar.setValue(newValue.toSeconds());

            }




        });

        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
            }
        });

        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
            }
        });

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total = media.getDuration();
                progressBar.setMax(total.toSeconds());
            }
        });

    }

    public void playMedia(ActionEvent event) {

        mediaPlayer.play();

    }

    public void pauseMedia(ActionEvent event) {

        mediaPlayer.pause();

    }

    public void rewatchMedia(ActionEvent event) {

        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {

            mediaPlayer.seek(Duration.seconds(0.0));

        }

    }

    public void progressBarDrag(MouseEvent event) {

    }

}
