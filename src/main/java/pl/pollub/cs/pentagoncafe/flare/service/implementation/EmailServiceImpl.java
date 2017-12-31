package pl.pollub.cs.pentagoncafe.flare.service.implementation;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private MailjetClient client;
    private MailjetRequest email;
    private MailjetResponse response;

   // @Value("${spring.mail.username}")
    private String apiKey;
   // @Value(("${spring.mail.password}"))
    private String apiSecret;

    @Override
    public void send() {

        client = new MailjetClient(apiKey, apiSecret, new ClientOptions("v3.1"));

        JSONObject message = new JSONObject();
        message.put(Emailv31.Message.FROM, new JSONObject()
                .put(Emailv31.Message.EMAIL, "example.from@gmail.com")
                .put(Emailv31.Message.NAME, "Mailjet Pilot")
        )
                .put(Emailv31.Message.SUBJECT, "Hello !")
                .put(Emailv31.Message.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
                .put(Emailv31.Message.HTMLPART, "<h3>Dear passenger, welcome to Mailjet</h3><br/>May the delivery force be with you!")
                .put(Emailv31.Message.TO, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.EMAIL, "example.to@gmail.com")));

        email = new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES, (new JSONArray()).put(message));

        try {
            response = client.post(email);
            System.out.println(response.getStatus());
            System.out.println(response.getData());

        } catch (MailjetException | MailjetSocketTimeoutException e) {
            e.printStackTrace();
        }
    }
}
