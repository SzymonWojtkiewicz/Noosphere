package colorSchemes;

import java.util.HashMap;
import java.util.Map;

public class ColorPallets
{
    public final String backgroundColor = "background";
    public final String backgroundSecondColor = "backgroundSecond";
    public final String textColor = "text";
    public final String buttonColor = "button";
    public final String warningColor = "warning";

    private Map<String, String> colorLightMap = new HashMap<String, String>();
    private Map<String, String> colorDarkMap = new HashMap<String, String>();

    public ColorPallets()
    {
        colorLightMap.put(backgroundColor, "#2C3E50");
        colorLightMap.put(backgroundSecondColor, "#34495E");
        colorLightMap.put(textColor, "#BDC3C7");
        colorLightMap.put(buttonColor, "#2980B9");
        colorLightMap.put(warningColor, "#C0392B");

        colorDarkMap.put(backgroundColor, "#ECF0F1");
        colorDarkMap.put(backgroundSecondColor, "#BDC3C7");
        colorDarkMap.put(textColor, "#34495E");
        colorDarkMap.put(buttonColor, "#3498DB");
        colorDarkMap.put(warningColor, "#E74C3C");
    }

    public String getLightColor(String key){
        return colorLightMap.get(key);
    }
    public String getDarkColor(String key){
        return colorDarkMap.get(key);
    }

}