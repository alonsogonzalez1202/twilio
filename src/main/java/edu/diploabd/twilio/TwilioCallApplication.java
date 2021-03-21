package edu.diploabd.twilio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

@SpringBootApplication
public class TwilioCallApplication implements ApplicationRunner {
    static Logger LOGGER = LoggerFactory.getLogger(TwilioCallApplication.class);
    private String phoneToCall = "525547913855";
    private TwilioConfiguration twilioConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(TwilioCallApplication.class, args);
    }

    @Autowired
    public void setTwilioConfiguration(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean isPhone = args.containsOption("phone");
        if (isPhone) {
            phoneToCall = args.getNonOptionArgs().get(0);
        }
        LOGGER.info("Phone to call: {}", phoneToCall);

        Call.creator(
                new PhoneNumber(phoneToCall),
                new PhoneNumber(twilioConfiguration.getTrialNumber()),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();
    }


}
