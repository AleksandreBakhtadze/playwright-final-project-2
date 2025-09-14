package ge.tbc.testautomation.utils;


//import org.jspecify.annotations.NonNull;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperFunctions {


    public boolean validateTime( String text){
        if (text.contains("24/7")) {
            return true;
        }
        List<String[]> ranges = extractHourRanges(text);

        LocalTime now = LocalTime.now();

        return isWithinAnyRange(now, ranges);
    }

    private List<String[]> extractHourRanges( String text) {

        List<String[]> ranges = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d{2}:\\d{2})-(\\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String start = matcher.group(1);
            String end = matcher.group(2);
            ranges.add(new String[]{start, end});
        }
        return ranges;

    }

    private boolean isWithinAnyRange(LocalTime now,   List<String[]> ranges) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for (String[] range : ranges) {
            LocalTime start = LocalTime.parse(range[0], formatter);
            LocalTime end = LocalTime.parse(range[1], formatter);
            if (!now.isBefore(start) && !now.isAfter(end)) {
                return true;
            }
        }
        return false;
    }


    public double getExchangeRate(String text) {
        String[] parts = text.split("=")[1].trim().split(" ");
        return Double.parseDouble(parts[0]);
    }

    public String formatExpectedValue(double value) {
        if (value == (long) value) {
            return String.format("%.0f", value);
        }

        String formatted = String.format("%.3f", value);

        formatted = formatted.replaceAll("0+$", "").replaceAll("\\.$", "");

        if (!formatted.contains(".")) {
            formatted += ".0";
        }

        return formatted;
    }


}
