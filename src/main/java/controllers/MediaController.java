package controllers;

import java.io.File;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;


public class MediaController implements Initializable{

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton, pauseButton, rewatchButton;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;



    public void initialize(URL arg0, ResourceBundle arg1) {

        file = new File("src/main/resources/videos/video.mp4"
        );

        media = new Media(file.toURI().toString());

        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);

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

}
