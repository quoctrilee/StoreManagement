package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateController {

    // Phương thức lấy ngày hiện tại
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    // Phương thức lấy ngày và giờ hiện tại
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    // Phương thức lấy ngày hiện tại dưới dạng chuỗi định dạng
    public static String getFormattedCurrentDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.now().format(formatter);
    }

    // Phương thức lấy ngày và giờ hiện tại dưới dạng chuỗi định dạng
    public String getFormattedCurrentDateTime(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(formatter);
    }

}
