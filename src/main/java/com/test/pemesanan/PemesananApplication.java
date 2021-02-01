package com.test.pemesanan;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;

@SpringBootApplication
public class PemesananApplication {

	public static void main(String[] args) {
		SpringApplication.run(PemesananApplication.class, args);
	}

// ocr untuk projek selanjutnya hanya testing
//	@Autowired
//	private JavaMailSender javaMailSender;
//
//	@EventListener(ApplicationReadyEvent.class)
//	void sendEmail () {
//
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setFrom("cekoke@gmail.com");
//		mailMessage.setTo("r.aprilyas@gmail.com");
//		mailMessage.setSubject("ok");
//
////		for(int i=0 ; i<=1000; i++) {
////			mailMessage.setText("tes "+i);
////			javaMailSender.send(mailMessage);
////		}
//
//		System.out.println("sukses");
//
//		String filPath = "C:/Users/Admin/Pictures/Screenshots/plat3.jpeg";
//		Tesseract image = new Tesseract();
//		image.setDatapath("C:/Users/Admin/Pictures/Screenshots/");
//
//		String str = null;
//		try {
//			str = image.doOCR(new File(filPath));
//		} catch (TesseractException e) {
//			System.out.println("Data from image"+e.getMessage());
//		}
//		System.out.println(str);
//	}
}
