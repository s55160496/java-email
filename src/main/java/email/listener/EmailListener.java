package email.listener;

import com.example.common.EmailRequest;
import email.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class EmailListener {

    private final EmailService emailService;


    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "activation-email")
    public void listenForActivateEmail(EmailRequest request) {
        log.info("Kafka received: " + request.getTo());

        emailService.Send(request.getTo(), request.getSubject(), request.getContent());
    }
}
