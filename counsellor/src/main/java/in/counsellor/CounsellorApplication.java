package in.counsellor;

import in.counsellor.entitty.Counsellor;
import in.counsellor.service.CounsellorService;
import in.counsellor.service.impl.CounsellorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CounsellorApplication {

	public static void main(String[] args) {

        SpringApplication.run(CounsellorApplication.class, args);

//        ApplicationContext context = SpringApplication.run(CounsellorApplication.class, args);
//
//        CounsellorService service = context.getBean(CounsellorService.class);
//
//        Counsellor c = new Counsellor();
//
//        c.setName("Viswa Teja");
//        c.setEmail("viswa@example.com");
//        c.setPwd("12345");
//        c.setPhno("9876543210");
//
//        service.register(c);
//
//        System.out.println("inserting my first data to counsellor portal");

	}
}
