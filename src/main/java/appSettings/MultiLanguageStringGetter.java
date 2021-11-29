package appSettings;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;

public class MultiLanguageStringGetter {


    public MultiLanguageStringGetter(){

    }

    public String getString (String language, String baseName)throws Exception
    {
        File file = new File("./src/main/resources/language/" + language + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null) {
            if(st.contains(baseName))
                break;
        }
        if(st == null)
            return baseName;

        boolean a = false;
        String returnString = "";
        for (int i = 1;i < st.length(); i++){
            if(a)
                returnString = returnString + st.charAt(i);
            if(st.charAt(i) == '$')
                a = true;


        }
        return returnString;
    }
}
