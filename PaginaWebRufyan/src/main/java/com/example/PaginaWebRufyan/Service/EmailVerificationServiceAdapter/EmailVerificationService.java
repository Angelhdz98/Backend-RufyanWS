package com.example.PaginaWebRufyan.Service.EmailVerificationServiceAdapter;


import com.example.PaginaWebRufyan.domain.port.out.VerificationTokenRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {
    @Value("${spring.datasource.app-port}")
    private Integer port;
    private final JavaMailSender javaMailSender;

    public EmailVerificationService(JavaMailSender javaMailSender, VerificationTokenRepositoryPort verificationTokenRepositoryPort) {
        this.javaMailSender = javaMailSender;

    }

    public void sendVerificationEmail(String to,
                                      String token){
            //for using requestParam
        String url = "http://localhost:"+port+"/auth/verify?token="+token;
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Verifica tu cuenta ");
        message.setText("Haz click para verificar tu cuenta: "+ url
        );
        javaMailSender.send(message);

    }
}
