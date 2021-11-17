package Mysql.demo.FileImport;//package Mysql.demo;
//
import Mysql.demo.Entities.Liczarki;
import Mysql.demo.Repositories.LiczarkiRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class XmlsReaderServiceImpl implements XmlsReaderService {

//@Autowired
//public Liczarki list;
    @Autowired
LiczarkiRepository liczarkiRepository;


    @Override
    public void readXmls() {
        List<Liczarki> list = new ArrayList<Liczarki>();
        try
        {


            File file = new File("c:\\Users\\mzako\\OneDrive\\Dokumenty\\employee.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext())
            {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column

                Liczarki l = new Liczarki();
                int  i=0;
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                         switch (i){
                                case 0: l.setTyp(cell.getStringCellValue());
                                    i++;
                                    break;
                                case 1:
                                    l.setSerial(cell.getStringCellValue());
                                    i++;
                                    break;
                                case 2:
                                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    DataFormatter dataFormatter = new DataFormatter();
                                    String date = dataFormatter.formatCellValue(cell);
                                    LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
                                    l.setDateStamp(localDate);
                                    i++;
                                    break;

                                case 3: l.setState(cell.getStringCellValue());
                                    i++;
                                    break;
                                case 4:
                                    dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    dataFormatter = new DataFormatter();
                                    String sell = dataFormatter.formatCellValue(cell);
                                    LocalDate sellDate = LocalDate.parse(sell, dateTimeFormatter);
                                    l.setSellDate(sellDate);
                                    i++;
                                    break;




                         }

                        list.add(l);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        liczarkiRepository.saveAll(list);
    }

}
