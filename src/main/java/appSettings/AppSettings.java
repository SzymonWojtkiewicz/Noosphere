package appSettings;

import colorSchemes.ColorPallets;

public class AppSettings {
    private Boolean nightMode;
    private int highOfWindow;
    private int widthOfWindow;

    public AppSettings(){
    }

    public String backgroundColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("background");
        else
            return pallet.getLightColor("background");
    }
    public String backgroundSecondColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("backgroundSecond");
        else
            return pallet.getLightColor("backgroundSecond");
    }
    public String textColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("text");
        else
            return pallet.getLightColor("text");
    }
    public String buttonColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("button");
        else
            return pallet.getLightColor("button");
    }
    public String warningColor(){
        ColorPallets pallet = new ColorPallets();
        if(nightMode)
            return pallet.getDarkColor("warning");
        else
            return pallet.getLightColor("warning");
    }
}
