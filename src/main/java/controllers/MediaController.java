package controllers;

import Database.DatabaseVideos;


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

    private DatabaseVideos movie = new DatabaseVideos();
    //Uses Database methods
    //Labeled as movie

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton, pauseButton, rewatchButton;

    @FXML
    private Slider progressBar;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    //Implementation of interactive elements



    public void initialize(URL arg0, ResourceBundle arg1) {
        //Initializing method

        media = new Media(movie.displaySourceVideo("walka", "nieznany"));
        //Takes link to a movie from a Database

        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);
        //Implements movie to a mediaPlayer


        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                progressBar.setValue(newValue.toSeconds());
                //Implements listener logic into sliderBar
            }




        });

        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
               //Seeks and takes duration of a movie in seconds inside progressBar while mouse clicking
            }
        });

        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                //Seeks and takes duration of a movie in seconds inside progressBar while mouse dragging
            }
        });

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total = media.getDuration();
                progressBar.setMax(total.toSeconds());
                //Spreads seconds equally into progessBar
            }
        });

    }

    public void playMedia(ActionEvent event) {
        mediaPlayer.play();
        //Allows movie inside mediaPlayer object to play while mouse clicking
    }

    public void pauseMedia(ActionEvent event) {
        mediaPlayer.pause();
        //Allows movie inside mediaPlayer object to pause while mouse clicking
    }

    public void rewatchMedia(ActionEvent event) {

        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {

            mediaPlayer.seek(Duration.seconds(0.0));
            //Allows movie inside mediaPlayer object to replay (start at 0 seconds) while mouse clicking

        }

    }

    public void progressBarDrag(MouseEvent event) {

    }

}
