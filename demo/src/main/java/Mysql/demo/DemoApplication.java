package Mysql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	public LiczarkiRepository liczarkiRepository;

	public static void main(String[] args){
		SpringApplication.run(DemoApplication.class, args);
	}





	@Override
	public void run(String... args) throws Exception {
		Liczarki l = new Liczarki();
		l.setTyp("Gfs-130");
		Liczarki l1 = new Liczarki();
		l1.setTyp("Gfs-140");
		Liczarki l2 = new Liczarki();
		l2.setTyp("Gfs-120");
		System.out.println("DUPA");
		liczarkiRepository.save(l);
		liczarkiRepository.save(l1);
		liczarkiRepository.save(l2);


		ArrayList<Liczarki> liczarki = (ArrayList<Liczarki>) liczarkiRepository.findAll();

		liczarki.forEach(System.out :: println);

	}
}
