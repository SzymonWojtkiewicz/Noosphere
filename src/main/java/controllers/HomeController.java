package controllers;

import appUtils.VideoGridCreator;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class HomeController
{
    @FXML
    private GridPane videosGridPane;

    public void initialize()
    {

        //SW
        /*
        URL website = new URL("http://www.website.com/information.asp");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("information.html");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

         */

        VideoGridCreator.FillGrid(this, videosGridPane);
    }
}
