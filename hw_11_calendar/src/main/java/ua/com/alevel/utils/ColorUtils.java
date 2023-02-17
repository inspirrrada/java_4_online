package ua.com.alevel.utils;

import com.diogonunes.jcolor.AnsiFormat;

import static com.diogonunes.jcolor.Attribute.*;

public class ColorUtils {

    private static final AnsiFormat BLUE_TEXT = new AnsiFormat(BRIGHT_BLUE_TEXT());
    public static final AnsiFormat RED_TEXT = new AnsiFormat(RED_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());

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
