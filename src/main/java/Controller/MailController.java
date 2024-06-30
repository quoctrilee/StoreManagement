package Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailController {

    private static final String FROM_EMAIL = "trilq.23it@vku.udn.vn";
    private static final String FROM_PASSWORD = "mrlee123."; // Thay bằng mật khẩu của bạn hoặc sử dụng mật khẩu ứng dụng
    private static Map<String, String> otpStorage = new HashMap<>();

    public static void sendOTP(String toEmail) {
        String otp = generateOTP();
        otpStorage.put(toEmail, otp); // Lưu mã OTP với email
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Mã xác nhận OTP của bạn");
            message.setText("Mã OTP của bạn là: " + otp);
            Transport.send(message);

            System.out.println("Gửi email thành công!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyOTP(String email, String otp) {
        return otp.equals(otpStorage.get(email));
    }

    private static String generateOTP() {
        Random random = new Random();
        int otp = 10000 + random.nextInt(90000);
        return String.valueOf(otp);
    }
}
