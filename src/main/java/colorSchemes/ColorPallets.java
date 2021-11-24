package colorSchemes;

import java.util.HashMap;
import java.util.Map;

public class ColorPallets
{
    public final String backgroundColor = "background";
    public final String backgroundSecondColor = "backgroundSecond";
    public final String textColor = "text";
    public final String buttonColor = "button";
    public final String errorColor = "warning";
    public final String successColor = "success";

    private Map<String, String> colorLightMap = new HashMap<String, String>();
    private Map<String, String> colorDarkMap = new HashMap<String, String>();

    public ColorPallets()
    {
        colorLightMap.put(backgroundColor, "#2C3E50");
        colorLightMap.put(backgroundSecondColor, "#34495E");
        colorLightMap.put(textColor, "#BDC3C7");
        colorLightMap.put(buttonColor, "#2980B9");
        colorLightMap.put(errorColor, "#C0392B");
        colorLightMap.put(successColor, "#2ECC71");

        colorDarkMap.put(backgroundColor, "#ECF0F1");
        colorDarkMap.put(backgroundSecondColor, "#BDC3C7");
        colorDarkMap.put(textColor, "#34495E");
        colorDarkMap.put(buttonColor, "#3498DB");
        colorDarkMap.put(errorColor, "#E74C3C");
        colorDarkMap.put(successColor, "#27AE60");
    }

    public String GetColor(String type)
    {
        //zmienna wskazujacy na typ motywu
        return colorLightMap.get(type); //jasny motyw jako domyslny do testow
    }
}