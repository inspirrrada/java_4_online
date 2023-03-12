package ua.com.alevel.utils;

import com.diogonunes.jcolor.AnsiFormat;

import static com.diogonunes.jcolor.Attribute.*;

public class ColorUtils {
    public static final AnsiFormat BLUE_TEXT = new AnsiFormat(BRIGHT_BLUE_TEXT());
    public static final AnsiFormat RED_TEXT = new AnsiFormat(RED_TEXT());
    public static final AnsiFormat YELLOW_TEXT = new AnsiFormat(YELLOW_TEXT());
    public static final AnsiFormat REVERSE = new AnsiFormat(REVERSE());
    public static final AnsiFormat UNDERLINED = new AnsiFormat(UNDERLINE());
}
