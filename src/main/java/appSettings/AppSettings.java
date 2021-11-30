package appSettings;

import colorSchemes.ColorPallets;

//example   AppSettings.backgroundColor();
//example   AppSettings.setLanguage("Polski");

public class AppSettings {
    private static Boolean nightMode = true;
    private static int heightOfWindow;
    private static int widthOfWindow;
    private static String language = "English";

    public static Boolean getNightMode(){return nightMode;}
    public static void setNightMode(){
        nightMode = !nightMode;
    }

    public static String getLanguage(){
        return language;
    }
    public void setLanguage(String language){  //add check if file for requested language exists
        this.language = language;
    }

    //I way to get color
    public static String getColorByKey(String key){
        if(nightMode)
            return ColorPallets.getDarkColor(key);
        else
            return ColorPallets.getLightColor(key);
    }
    //II way to get color
    public static String backgroundColor(){
        if(nightMode)
            return ColorPallets.getDarkColor("background");
        else
            return ColorPallets.getLightColor("background");
    }
    public static String backgroundSecondColor(){
        if(nightMode)
            return ColorPallets.getDarkColor("backgroundSecond");
        else
            return ColorPallets.getLightColor("backgroundSecond");
    }
    public static String textColor(){
        if(nightMode)
            return ColorPallets.getDarkColor("text");
        else
            return ColorPallets.getLightColor("text");
    }
    public static String buttonColor(){
        if(nightMode)
            return ColorPallets.getDarkColor("button");
        else
            return ColorPallets.getLightColor("button");
    }
    public static String warningColor(){
        if(nightMode)
            return ColorPallets.getDarkColor("warning");
        else
            return ColorPallets.getLightColor("warning");
    }
    public static String errorColor(){
        if(nightMode)
            return ColorPallets.getDarkColor("error");
        else
            return ColorPallets.getLightColor("error");
    }
    public static String successColor(){
        if(nightMode)
            return ColorPallets.getDarkColor("success");
        else
            return ColorPallets.getLightColor("success");
    }
}
