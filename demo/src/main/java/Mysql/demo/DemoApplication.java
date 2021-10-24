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
	public XmlsReaderService xmlsReaderService;

	@Autowired
	public LiczarkiRepository liczarkiRepository;
//	@Autowired
//	public ServiceSellReport serviceSellReport;

	public static void main(String[] args){
		SpringApplication.run(DemoApplication.class, args);

	}





	@Override
	public void run(String... args) throws Exception {
		//xmlsReaderService.readXmls();
//		serviceSellReport.createReport();
// 		System.out.println(serviceSellReport.createReport());
	}
}
