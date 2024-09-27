package com.example.crimejava.Utils;

import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;
import javafx.scene.control.Alert;

public class Notification {

    private static final String EMAIL_FROM = "info@trial-vywj2lppk2pl7oqz.mlsender.net";
    private static final String MAILER_SEND_TOKEN = "mlsn.13bca99943b880576b7a662120d99bfa46207fc7d3069cfc9836aa260371409d";

    public static void sendVerification(String emailAddress, String verificationCode) {
        String htmlContentPasswordReset = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 80%; max-width: 600px; margin: 0 auto; background-color: #fff; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); padding: 20px; }" +
                ".header { background-color: #007bff; color: #fff; padding: 20px; text-align: center; font-size: 24px; font-weight: bold; }" +
                ".content { padding: 20px; }" +
                ".content p { margin: 0 0 10px; }" +
                ".verification-code { font-size: 18px; font-weight: bold; color: #007bff; padding: 10px; background-color: #e9ecef; border-radius: 4px; }" +
                ".footer { background-color: #f7f7f7; padding: 10px; text-align: center; font-size: 12px; color: #777; border-top: 1px solid #ddd; }" +
                ".button { display: inline-block; padding: 10px 20px; font-size: 16px; color: #fff; background-color: #007bff; text-decoration: none; border-radius: 5px; margin-top: 20px; }" +
                ".button:hover { background-color: #0056b3; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<div class=\"header\">Password Reset Verification Code</div>" +
                "<div class=\"content\">" +
                "<p>Dear Officer,</p>" +
                "<p>We have received a request to reset your password for the Criminal Tracker system. Please use the verification code below to proceed with resetting your password:</p>" +
                "<p class=\"verification-code\">" + verificationCode + "</p>" +
                "<p>This code is valid for 15 minutes. If you did not make this request, please disregard this email.</p>" +
                "<p>For further assistance, feel free to contact the support team.</p>" +
                "<p>Thank you for using Criminal Tracker.</p>" +
                "<p>Best regards,<br><b>Criminal Tracker Support Team</b></p>" +
                "</div>" +
                "<div class=\"footer\">&copy; 2024 Criminal Tracker. All rights reserved.</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        sendEmail(emailAddress, "Criminal Tracker Verification Notification", htmlContentPasswordReset);
    }

    public static void sendBugReport(String systemBuilderEmail, String userEmail, String bugTitle, String description, String submissionDate) {
        String emailContent = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 80%; max-width: 600px; margin: 0 auto; background-color: #fff; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); padding: 20px; }" +
                ".header { background-color: #007bff; color: #fff; padding: 20px; text-align: center; font-size: 24px; font-weight: bold; }" +
                ".content { padding: 20px; }" +
                ".content p { margin: 0 0 10px; }" +
                ".footer { background-color: #f7f7f7; padding: 10px; text-align: center; font-size: 12px; color: #777; border-top: 1px solid #ddd; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<div class=\"header\">Bug Report Received</div>" +
                "<div class=\"content\">" +
                "<p><b>Bug Title:</b> " + bugTitle + "</p>" +
                "<p><b>Description:</b> " + description + "</p>" +
                "<p><b>Report Submitted By:</b> " + userEmail + "</p>" +
                "<p><b>Submission Date:</b> " + submissionDate + "</p>" +
                "<p>Dear System Builder Team,</p>" +
                "<p>A new bug report has been submitted for the Criminal Tracker system. Please review the details provided and take appropriate action to address the issue. Your prompt attention to this matter is greatly appreciated.</p>" +
                "<p>Thank you for your ongoing efforts in maintaining and improving the system.</p>" +
                "<p>Best regards,<br>The Criminal Tracker Team</p>" +
                "</div>" +
                "<div class=\"footer\">&copy; 2024 Criminal Tracker. All rights reserved.</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        sendEmail(systemBuilderEmail, "Criminal Tracker Bug Report", emailContent);
    }

    public static void sendUserFeedbackEmail(String userEmail, String submissionDate) {
        String emailContent = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 80%; max-width: 600px; margin: 0 auto; background-color: #fff; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); padding: 20px; }" +
                ".header { background-color: #007bff; color: #fff; padding: 20px; text-align: center; font-size: 24px; font-weight: bold; }" +
                ".content { padding: 20px; }" +
                ".content p { margin: 0 0 10px; }" +
                ".footer { background-color: #f7f7f7; padding: 10px; text-align: center; font-size: 12px; color: #777; border-top: 1px solid #ddd; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<div class=\"header\">Thank You for Your Feedback</div>" +
                "<div class=\"content\">" +
                "<p>Dear User,</p>" +
                "<p>Thank you for submitting your bug report. We have received your feedback and appreciate your input. Our team will review the issue and work on fixing it as soon as possible.</p>" +
                "<p><b>Submission Date:</b> " + submissionDate + "</p>" +
                "<p>If you have any further questions or concerns, feel free to reach out to us.</p>" +
                "<p>Thank you for your valuable feedback!</p>" +
                "<p>Best regards,<br>The Criminal Tracker Team</p>" +
                "</div>" +
                "<div class=\"footer\">&copy; 2024 Criminal Tracker. All rights reserved.</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        sendEmail(userEmail, "Thank You for Your Feedback", emailContent);
    }

    private static void sendEmail(String to, String subject, String htmlContent) {
        Email email = new Email();
        email.setFrom("Criminal Tracker", EMAIL_FROM);
        email.addRecipient("Recipient", to);
        email.setSubject(subject);
        email.setHtml(htmlContent);

        MailerSend mailerSend = new MailerSend();
        mailerSend.setToken(MAILER_SEND_TOKEN);

        try {
            MailerSendResponse response = mailerSend.emails().send(email);
        } catch (MailerSendException e) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Email Sending Failed", "Failed to send the email: " + e.getMessage());
        }
    }
}
