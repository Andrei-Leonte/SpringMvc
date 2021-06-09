package com.dreamcar.andreea.utils;

import java.io.IOException;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class EmailUtil {
    public static void sendmail(
        String email, String title, String message) {
        var from = new Email("Testjava76@gmail.com");
        String subject = title;
        Email to = new Email("leonteiandrei@gmail.com");
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);
    
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
          request.setMethod(Method.POST);
          request.setEndpoint("mail/send");
          request.setBody(mail.build());
          Response response = sg.api(request);
          System.out.println(response.getStatusCode());
          System.out.println(response.getBody());
          System.out.println(response.getHeaders());
        } catch (IOException ex) {
            return;
        }
     }
}
