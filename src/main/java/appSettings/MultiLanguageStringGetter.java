package appSettings;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

//example MultiLanguageStringGetter.getString("Registration")

public class MultiLanguageStringGetter {


    public MultiLanguageStringGetter(){

    }

    public static String getString (String baseName)throws Exception
    {
        File file = new File("./src/main/resources/language/" + AppSettings.getLanguage() + ".txt");
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
