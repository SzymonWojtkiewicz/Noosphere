package appUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/** Opis tej klasy
 * Uzycie tej klasy odbywa sie poprzez odwolanie do funkcji statycznej FillGrid
 * Liczba kolumn bedzie zalezala od tego ile kolumn w siatce znajduje sie w pliku FXML
 * Przykladowe wywolanie w innej klasie `VideoGridCreator.FillGrid(this, videosGridPane)`
 *
 * Przykładowa budowa GridPane (dla 4 kolumn) (Sugerowac sie tym co jest w `mainAppWindow.fxml`)
 * <AnchorPane fx:controller="controllers.HomeController" maxHeight="Infinity" maxWidth="Infinity" prefHeight="756.0" prefWidth="1267.0" xmlns="http://javafx.com/javafx/11.0.2">
 *     <children>
 *       <ScrollPane fitToWidth="true" hbarPolicy="NEVER" prefHeight="756.0" prefWidth="1267.0">
 *          <content>
 *               <GridPane fx:id="videoGridTest" hgap="10.0" style="-fx-grid-lines-visible: true;" vgap="10.0">
 *                   <columnConstraints>
 *                       <ColumnConstraints fillWidth="true" hgrow="ALWAYS" />
 *                       <ColumnConstraints fillWidth="true" hgrow="ALWAYS" />
 *                       <ColumnConstraints fillWidth="true" hgrow="ALWAYS" />
 *                       <ColumnConstraints fillWidth="true" hgrow="ALWAYS" />
 *                   </columnConstraints>
 *                   <rowConstraints>
 *                       <RowConstraints fillHeight="true" vgrow="ALWAYS"/>
 *                   </rowConstraints>
 *               </GridPane>
 *          </content>
 *       </ScrollPane>
 *     </children>
 * </AnchorPane>
 */

public class VideoGridCreator
{
    /**
     * Funkcja uzywana do tworzenia siatki z filmami (miniaturka i tytul)
     * @param contextObject Instancja obiektu w celu uzycia `getResource()`
     * @param videosGridPane Element FXML typu GridPane, na ktorym beda rozmieszczane filmy
     */
    public static void FillGrid(Object contextObject, GridPane videosGridPane)
    {
        //maksymalna liczba kolumn dla tej siatki
        int maxColumnCount = videosGridPane.getColumnCount();

        File videosDirectory = new File("src/main/resources/videos"); //wczytuje folder z filmami
        int videosCount = videosDirectory.list().length; //odczytuje ile plikow jest w tym folderze

        //videosCount = 25; //do testow mozna sobie samemu przypisac wartosc

        int currentVideoIndex = 0;
        int currentRowCount = 0;
        do
        {
            for(int c = 0; c < maxColumnCount; c++)
            {
                //Wczytujemy plik FXML, ktory zawiera szablon tego jak ma wygladac komorka w siatce (miniaturka i tytul filmu)
                FXMLLoader outerLoader = new FXMLLoader(contextObject.getClass().getResource("/view/fxml/homeVideoTemplate.fxml"));
                AnchorPane anchorPane = null;
                try
                {
                    anchorPane = outerLoader.load();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                VBox templateVBox = (VBox) anchorPane.getChildren().get(0); //VBox zawierajacy ItemView i Label

                Image thumbnail = null;
                try
                {
                    //przykladowy sposob pobrania zdjecia z pliku i wyswietlenia go jako miniaturke
                    String imagePath = "/images/placeHolderFrames/123996041_3371344192964254_450105510026829004_n.jpg";
                    thumbnail = new Image(contextObject.getClass().getResource(imagePath).toURI().toString());
                }
                catch (URISyntaxException e)
                {
                    e.printStackTrace();
                }

                ((ImageView) templateVBox.getChildren().get(0)).setImage(thumbnail); //element ImageView z szablonu (miniaturka filmu)
                ((Label) templateVBox.getChildren().get(1)).setText("Video " + currentVideoIndex); //element Label z szablonu (tytul filmu)
                //videosDirectory.list()[currentVideoIndex] - wyswietla nazwe pliku (filmu)

                videosGridPane.add(templateVBox, c, currentRowCount);
                currentVideoIndex++;

                if(currentVideoIndex >= videosCount) //jesli wyswietlilismy wszystkie filmy to przerywamy
                    break;
            }

            currentRowCount++;

        }while(currentVideoIndex < videosCount);
    }
}
