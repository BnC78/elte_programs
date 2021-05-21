package helper;

public class Helper {
    
    public static String alignMiddle(String text, int length) {
        double freeSpace = length - text.length();
        String result = "";
        for (int i = 0; i < Math.floor(freeSpace/2); i++){
            result += " ";
        }
        result += text;
        for (int i = 0; i < Math.ceil(freeSpace/2); i++){
            result += " ";
        }
        return result;
    }

}
