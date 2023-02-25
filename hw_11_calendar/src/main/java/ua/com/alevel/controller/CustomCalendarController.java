package ua.com.alevel.controller;

import ua.com.alevel.calendar.CustomCalendar;
import ua.com.alevel.utils.ColorUtils;
import ua.com.alevel.utils.CustomCalendarFormats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomCalendarController {
    public void start() {
        System.out.println();
        System.out.println("WELCOME TO THE CALENDAR APP!");
        System.out.println();
        System.out.println("***Pay attention, possible formats for date in app:");
        System.out.println("==> “2023-02-02” - initializes as 2023 February 2, 0 h, 0 min, 0 sec, 0 milliseconds");
        System.out.println("==> “2023-02-02 22:09” - initializes as 2023 February 2 22 h, 9 min, 0 sec, 0 milliseconds");
        System.out.println("==> “2023-02-02 22:09:59” - initializes as 2023 February 2 22 h, 9 min, 59 sec, 0 milliseconds");
        System.out.println("==> “2023-02-02 22:09:59 999” - initializes as 2023 February 2 22 h, 9 min, 59 sec, 999 milliseconds");
        System.out.println();
        System.out.println("Please select your options:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String selectedOption;
        menu();
        try {
            while ((selectedOption = reader.readLine()) != null) {
                mainOptions(reader, selectedOption);
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Oops...Something went wrong:( Please try again later."));
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("MAIN MENU:");
        System.out.println("=======================================================================");
        System.out.println("|  If you want to get current time, please enter 1                    |");
        System.out.println("|  If you want to get some date in milliseconds, please enter 2       |");
        System.out.println("|  If you want to set exact date in your calendar, please enter 3     |");
        System.out.println("|  If you want to add some time to the date, please enter 4           |");
        System.out.println("|  If you want to subtract the dates from each other, please enter 5  |");
        System.out.println("|  If you want to get difference between dates, please enter 6        |");
        System.out.println("|  If you want to close application, please enter 7                   |");
        System.out.println("=======================================================================");
        System.out.println();
    }

    private void addSubmenu() {
        System.out.println();
        System.out.println(" ADD MENU:");
        System.out.println(" ---------------------------------------------------");
        System.out.println("| For add dates to each other, please enter 11      |");
        System.out.println("| For add years to the date, please enter 12        |");
        System.out.println("| For add months to the date, please enter 13       |");
        System.out.println("| For add days to the date, please enter 14         |");
        System.out.println("| For add hours to the date, please enter 15        |");
        System.out.println("| For add minutes to the date, please enter 16      |");
        System.out.println("| For add seconds to the date, please enter 17      |");
        System.out.println("| For add milliseconds to the date, please enter 18 |");
        System.out.println(" ---------------------------------------------------");
        System.out.println();
    }

    private void minusSubmenu() {
        System.out.println();
        System.out.println(" ----------------------------------------------------------");
        System.out.println("| For subtract dates from each other, please enter 21      |");
        System.out.println("| For subtract years from the date, please enter 22        |");
        System.out.println("| For subtract months from the date, please enter 23       |");
        System.out.println("| For subtract days from the date, please enter 24         |");
        System.out.println("| For subtract hours from the date, please enter 25        |");
        System.out.println("| For subtract minutes from the date, please enter 26      |");
        System.out.println("| For subtract seconds from the date, please enter 27      |");
        System.out.println("| For subtract milliseconds from the date, please enter 28 |");
        System.out.println(" ----------------------------------------------------------");
        System.out.println();
    }

    private void differenceSubmenu() {
        System.out.println();
        System.out.println(" -------------------------------------------------------------------------");
        System.out.println("| For receive difference in years between 2 dates, please enter 31        |");
        System.out.println("| For receive difference in months between 2 dates, please enter 32       |");
        System.out.println("| For receive difference in days between 2 dates, please enter 33         |");
        System.out.println("| For receive difference in hours between 2 dates, please enter 34        |");
        System.out.println("| For receive difference in minutes between 2 dates, please enter 35      |");
        System.out.println("| For receive difference in seconds between 2 dates, please enter 36      |");
        System.out.println("| For receive difference in milliseconds between 2 dates, please enter 37 |");
        System.out.println(" -------------------------------------------------------------------------");
        System.out.println();
    }

    private void mainOptions(BufferedReader reader, String selectedOption) throws IOException {
        switch (selectedOption) {
            case "1" -> getCurrentTime();
            case "2" -> getDateInMillis(reader);
            case "3" -> setDateInCalendar(reader);
            case "4" -> addOptions(reader);
            case "5" -> minusOptions(reader);
            case "6" -> differenceOptions(reader);
            case "7" -> stop();
            default -> System.out.println(ColorUtils.RED_TEXT.format("Wrong value! Select menu again."));
        }
        menu();
    }

    private void getCurrentTime() {
        System.out.println(ColorUtils.REVERSE.format("\nMenu 1. GET CURRENT TIME"));
        CustomCalendar calendar = new CustomCalendar();
        System.out.println(ColorUtils.BLUE_TEXT.format("Now is: " + calendar.now()));
    }

    private void getDateInMillis(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nMenu 2. GET DATE IN MILLISECONDS"));
        System.out.println();
        System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
        try {
            String date = reader.readLine();
            boolean isFormatValid = CustomCalendarFormats.isFormatValid(date);
            if (isFormatValid) {
                CustomCalendar calendar = new CustomCalendar(date);
                System.out.println(ColorUtils.BLUE_TEXT.format("This date in milliseconds has " + calendar.getTimeMillis() + " ms."));
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void setDateInCalendar(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nMenu 3. SET DATE IN CALENDAR"));
        System.out.println();
        System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
        try {
            String date = reader.readLine();
            boolean isFormatValid = CustomCalendarFormats.isFormatValid(date);
            if (isFormatValid) {
                CustomCalendar calendar = new CustomCalendar(date);
                calendar.set(date);
                System.out.println(ColorUtils.BLUE_TEXT.format("The calendar was set on date: " + calendar + "."));
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void addOptions(BufferedReader reader) throws IOException {
        addSubmenu();
        String selectedOption = reader.readLine();
        switch (selectedOption) {
            case "11" -> addDateToDate(reader);
            case "12" -> addYearsToDate(reader);
            case "13" -> addMonthsToDate(reader);
            case "14" -> addDaysToDate(reader);
            case "15" -> addHoursToDate(reader);
            case "16" -> addMinutesToDate(reader);
            case "17" -> addSecondsToDate(reader);
            case "18" -> addMillisToDate(reader);
            default -> System.out.println(ColorUtils.RED_TEXT.format("Wrong value!"));
        }
    }

    private void addDateToDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 4.1. Add dates"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date1);
//                    CustomCalendar calendar2 = new CustomCalendar(date2);
                    calendar.addDate(date2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + calendar.getTimeMillis() + "ms or " + calendar));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void addYearsToDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 4.2. Add years to the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of years:"));
                String yearsQty = reader.readLine();
                if (yearsQty.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.addYears(Integer.parseInt(yearsQty));
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For years need number NOT string!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void addMonthsToDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 4.3. Add months to the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of months:"));
                String monthsQtyS = reader.readLine();
                int monthQty = Integer.parseInt(monthsQtyS);
                if (monthsQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.addMonths(monthQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For months need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void addDaysToDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 4.4. Add days to the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of days:"));
                String daysQtyS = reader.readLine();
                int daysQty = Integer.parseInt(daysQtyS);
                if (daysQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.addDays(daysQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For days need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void addHoursToDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 4.5. Add hours to the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of hours:"));
                String hoursQtyS = reader.readLine();
                int hoursQty = Integer.parseInt(hoursQtyS);
                if (hoursQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.addHours(hoursQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For hours need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void addMinutesToDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 4.6. Add minutes to the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of minutes:"));
                String minutesQtyS = reader.readLine();
                int minutesQty = Integer.parseInt(minutesQtyS);
                if (minutesQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.addMinutes(minutesQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For minutes need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void addSecondsToDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 4.7. Add seconds to the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of seconds:"));
                String secondsQtyS = reader.readLine();
                int secondsQty = Integer.parseInt(secondsQtyS);
                if (secondsQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.addSeconds(secondsQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For seconds need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void addMillisToDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 4.7. Add milliseconds to the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of millisseconds:"));
                String millisQtyS = reader.readLine();
                int millisQty = Integer.parseInt(millisQtyS);
                if (millisQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.addMilliseconds(millisQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For milliseconds need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void minusOptions(BufferedReader reader) throws IOException {
        minusSubmenu();
        String selectedOption = reader.readLine();
        switch (selectedOption) {
            case "21" -> minusDateFromDate(reader);
            case "22" -> minusYearsFromDate(reader);
            case "23" -> minusMonthsFromDate(reader);
            case "24" -> minusDaysFromDate(reader);
            case "25" -> minusHoursFromDate(reader);
            case "26" -> minusMinutesFromDate(reader);
            case "27" -> minusSecondsFromDate(reader);
            case "28" -> minusMillisFromDate(reader);
            default -> System.out.println(ColorUtils.RED_TEXT.format("Wrong value!"));
        }
    }

    private void minusDateFromDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 5.1. Subtract dates"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date1);
                    calendar.minusDate(date2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + calendar.getTimeMillis() + "ms or " + calendar));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void minusYearsFromDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 5.2. Subtract years from the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of years:"));
                String yearsQty = reader.readLine();
                if (yearsQty.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.minusYears(Integer.parseInt(yearsQty));
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For years need number NOT string!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void minusMonthsFromDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 5.3. Subtract months from the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of months:"));
                String monthsQtyS = reader.readLine();
                int monthQty = Integer.parseInt(monthsQtyS);
                if (monthsQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.minusMonths(monthQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For months need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void minusDaysFromDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 5.4. Subtract days from the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of days:"));
                String daysQtyS = reader.readLine();
                int daysQty = Integer.parseInt(daysQtyS);
                if (daysQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.minusDays(daysQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For days need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void minusHoursFromDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 5.5. Subtract hours from the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of hours:"));
                String hoursQtyS = reader.readLine();
                int hoursQty = Integer.parseInt(hoursQtyS);
                if (hoursQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.minusHours(hoursQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For hours need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void minusMinutesFromDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 5.6. Subtract minutes from the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of minutes:"));
                String minutesQtyS = reader.readLine();
                int minutesQty = Integer.parseInt(minutesQtyS);
                if (minutesQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.minusMinutes(minutesQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For minutes need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void minusSecondsFromDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 5.7. Subtract seconds from the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of seconds:"));
                String secondsQtyS = reader.readLine();
                int secondsQty = Integer.parseInt(secondsQtyS);
                if (secondsQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.minusSeconds(secondsQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For seconds need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void minusMillisFromDate(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 5.7. Subtract milliseconds from the date"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the date in possible format:"));
            String date = reader.readLine();
            boolean isDateValid = CustomCalendarFormats.isFormatValid(date);
            if (isDateValid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the number of millisseconds:"));
                String millisQtyS = reader.readLine();
                int millisQty = Integer.parseInt(millisQtyS);
                if (millisQtyS.matches("\\d+")) {
                    System.out.println();
                    CustomCalendar calendar = new CustomCalendar(date);
                    calendar.minusMilliseconds(millisQty);
                    System.out.println("Result is: " + calendar);
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Invalid value! For milliseconds need number NOT String!"));
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void differenceOptions(BufferedReader reader) throws IOException {
        differenceSubmenu();
        String selectedOption = reader.readLine();
        switch (selectedOption) {
            case "31" -> getDifferenceInYears(reader);
            case "32" -> getDifferenceInMonths(reader);
            case "33" -> getDifferenceInDays(reader);
            case "34" -> getDifferenceInHours(reader);
            case "35" -> getDifferenceInMinutes(reader);
            case "36" -> getDifferenceInSeconds(reader);
            case "37" -> getDifferenceInMillis(reader);
            default -> System.out.println(ColorUtils.RED_TEXT.format("Wrong value!"));
        }
    }

    private void getDifferenceInYears(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 6.1. Get difference between dates in years"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar1 = new CustomCalendar(date1);
                    CustomCalendar calendar2 = new CustomCalendar(date2);
                    int diff = CustomCalendar.differenceInYears(calendar1, calendar2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + diff + " years."));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void getDifferenceInMonths(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 6.2. Get difference between dates in months"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar1 = new CustomCalendar(date1);
                    CustomCalendar calendar2 = new CustomCalendar(date2);
                    int diff = CustomCalendar.differenceInMonths(calendar1, calendar2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + diff + " months."));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void getDifferenceInDays(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 6.3. Get difference between dates in days"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar1 = new CustomCalendar(date1);
                    CustomCalendar calendar2 = new CustomCalendar(date2);
                    int diff = CustomCalendar.differenceInDays(calendar1, calendar2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + diff + " days."));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void getDifferenceInHours(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 6.4. Get difference between dates in hours"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar1 = new CustomCalendar(date1);
                    CustomCalendar calendar2 = new CustomCalendar(date2);
                    int diff = CustomCalendar.differenceInHours(calendar1, calendar2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + diff + " hours."));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void getDifferenceInMinutes(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 6.5. Get difference between dates in minutes"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar1 = new CustomCalendar(date1);
                    CustomCalendar calendar2 = new CustomCalendar(date2);
                    long diff = CustomCalendar.differenceInMinutes(calendar1, calendar2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + diff + " minutes."));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void getDifferenceInSeconds(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 6.6. Get difference between dates in seconds"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar1 = new CustomCalendar(date1);
                    CustomCalendar calendar2 = new CustomCalendar(date2);
                    long diff = CustomCalendar.differenceInSeconds(calendar1, calendar2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + diff + " seconds."));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void getDifferenceInMillis(BufferedReader reader) {
        System.out.println(ColorUtils.REVERSE.format("\nSubmenu 6.7. Get difference between dates in milliseconds"));
        System.out.println();
        try {
            System.out.println(ColorUtils.UNDERLINED.format("Please enter the first date in possible format:"));
            String date1 = reader.readLine();
            boolean isDate1Valid = CustomCalendarFormats.isFormatValid(date1);
            if (isDate1Valid) {
                System.out.println();
                System.out.println(ColorUtils.UNDERLINED.format("Please enter the second date in possible format:"));
                String date2 = reader.readLine();
                boolean isDate2Valid = CustomCalendarFormats.isFormatValid(date2);
                if (isDate2Valid) {
                    System.out.println();
                    CustomCalendar calendar1 = new CustomCalendar(date1);
                    CustomCalendar calendar2 = new CustomCalendar(date2);
                    long diff = CustomCalendar.differenceInSeconds(calendar1, calendar2);
                    System.out.println(ColorUtils.BLUE_TEXT.format("Result is: " + diff + " milliseconds."));
                    System.out.println();
                } else {
                    System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                    System.out.println("Please check and try this menu again.");
                }
            } else {
                System.out.println(ColorUtils.RED_TEXT.format("Wrong format!"));
                System.out.println("Please check and try this menu again.");
            }
        } catch (IOException e) {
            System.out.println(ColorUtils.RED_TEXT.format("Something went wrong:( Please contact our support service."));
            throw new RuntimeException(e);
        }
    }

    private void stop() {
        System.out.println("\nThe application is finished.\n");
        System.exit(0);
    }
}
