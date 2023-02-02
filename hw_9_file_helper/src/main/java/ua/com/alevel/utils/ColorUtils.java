package ua.com.alevel.utils;

import com.diogonunes.jcolor.AnsiFormat;

import static com.diogonunes.jcolor.Attribute.*;

public class ColorUtils {
    private static AnsiFormat blueText = new AnsiFormat(BRIGHT_BLUE_TEXT());
    private static AnsiFormat redText = new AnsiFormat(RED_TEXT());
    private static AnsiFormat yellowText = new AnsiFormat(YELLOW_TEXT());
    private static AnsiFormat cyanText = new AnsiFormat(CYAN_TEXT());
    private static AnsiFormat magentaText = new AnsiFormat(MAGENTA_TEXT());
    private static AnsiFormat reverse = new AnsiFormat(REVERSE());
    private static AnsiFormat underlinedText = new AnsiFormat(UNDERLINE());
    private static AnsiFormat italicText = new AnsiFormat(ITALIC());

    public static AnsiFormat getBlueText() {
        return blueText;
    }

    public static AnsiFormat getRedText() {
        return redText;
    }

    public static AnsiFormat getYellowText() {
        return yellowText;
    }

    public static AnsiFormat getCyanText() {
        return cyanText;
    }

    public static AnsiFormat getMagentaText() {
        return magentaText;
    }

    public static AnsiFormat getReverse() {
        return reverse;
    }

    public static AnsiFormat getItalicText() {
        return italicText;
    }

    public static AnsiFormat getUnderlinedText() {
        return underlinedText;
    }
}
