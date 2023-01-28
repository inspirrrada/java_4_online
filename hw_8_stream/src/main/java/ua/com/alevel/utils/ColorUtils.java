package ua.com.alevel.utils;

import com.diogonunes.jcolor.AnsiFormat;

import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Attribute.UNDERLINE;

public class ColorUtils {
    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat redText = new AnsiFormat(RED_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());

    public static AnsiFormat getBlueText() {
        return blueText;
    }

    public static AnsiFormat getRedText() {
        return redText;
    }

    public static AnsiFormat getYellowText() {
        return yellowText;
    }

    public static AnsiFormat getReverse() {
        return reverse;
    }

    public static AnsiFormat getUnderlinedText() {
        return underlinedText;
    }
}
