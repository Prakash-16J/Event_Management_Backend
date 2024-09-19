package com.example.event_management_service.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;


    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    public TwilioService() {
        String accountSid = "AC28b7c10ffe3b038f15f4ff6d9004f0e9";
        String authToken = "20ca90a051fc3db95ee8db1fc595cb65";

        Twilio.init(accountSid, authToken);
    }

    public void sendSms(String toPhoneNumber, String messageBody) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    messageBody
            ).create();
            System.out.println("Message sent successfully with SID: " + message.getSid());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send message.");
        }
    }
}
