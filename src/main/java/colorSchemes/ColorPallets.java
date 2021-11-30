package colorSchemes;

import java.util.HashMap;
import java.util.Map;

public class ColorPallets
{
    private static String backgroundColor = "background";
    private static String backgroundSecondColor = "backgroundSecond";
    private static String textColor = "text";
    private static String buttonColor = "button";
    private static String warningColor = "warning";
    private static String errorColor = "error";
    private static String successColor = "success";

    private static Map<String, String> colorLightMap = new HashMap<String, String>();
    static {
        colorLightMap.put(backgroundColor, "#2C3E50");
        colorLightMap.put(backgroundSecondColor, "#34495E");
        colorLightMap.put(textColor, "#BDC3C7");
        colorLightMap.put(buttonColor, "#2980B9");
        colorLightMap.put(warningColor, "#C0392B");
        colorLightMap.put(errorColor, "#C0392B");
        colorLightMap.put(successColor, "#2ECC71");
    }
    private static Map<String, String> colorDarkMap = new HashMap<String, String>();
    static {
        colorDarkMap.put(backgroundColor, "#ECF0F1");
        colorDarkMap.put(backgroundSecondColor, "#BDC3C7");
        colorDarkMap.put(textColor, "#34495E");
        colorDarkMap.put(buttonColor, "#3498DB");
        colorDarkMap.put(warningColor, "#E74C3C");
        colorDarkMap.put(errorColor, "#E74C3C");
        colorDarkMap.put(successColor, "#27AE60");
    }
    public static String getLightColor(String key) {
        return colorLightMap.get(key);
    }
    public static String getDarkColor(String key) {
        return colorDarkMap.get(key);
    }

}