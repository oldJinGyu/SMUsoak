package com.smusoak.restapi.mail;

import com.smusoak.restapi.redis.RedisService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender mailSender;

	public void sendMail(String toMail, String title, String htmlContent) throws MessagingException {
		MimeMessage message = this.mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
		mimeMessageHelper.setTo(toMail);
		mimeMessageHelper.setSubject(title);
		mimeMessageHelper.setText(htmlContent, true);
		try {
			mailSender.send(message);
		} catch (RuntimeException e) {
			log.debug("MailService.sendEmail exception occur toEmail: {}, " +
					"title: {}, text: {}", toMail, title, htmlContent);
		}
	}
}
