package appSettings;

import colorSchemes.ColorPallets;

//example   AppSettings.backgroundColor();
//example   AppSettings.setLanguage("Polski");

public class AppSettings {
    private static Boolean nightMode;
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

    public static String backgroundColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("background");
        else
            return pallet.getLightColor("background");
    }
    public static String backgroundSecondColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("backgroundSecond");
        else
            return pallet.getLightColor("backgroundSecond");
    }
    public static String textColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("text");
        else
            return pallet.getLightColor("text");
    }
    public static String buttonColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("button");
        else
            return pallet.getLightColor("button");
    }
    public static String warningColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("warning");
        else
            return pallet.getLightColor("warning");
    }
}
