package in.counsellor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
