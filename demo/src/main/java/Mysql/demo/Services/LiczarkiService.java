package Mysql.demo.Services;

import Mysql.demo.Entities.Liczarki;
import Mysql.demo.Repositories.LiczarkiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LiczarkiService {

    @Autowired
    LiczarkiRepository liczarkiRepository;


    public boolean saveToRepo(Liczarki licz) {
        liczarkiRepository.save(licz);
        return true;
    }

    public List<Liczarki> findAll() {
        List<Liczarki> listLiczarki = (List<Liczarki>) liczarkiRepository.findAll();
        return listLiczarki;
    }

    public  List<Liczarki> search(String keyword){
        return liczarkiRepository.search(keyword);
    }

    public Liczarki searchByID(Long id){
        return liczarkiRepository.findById(id).get();
    }

    public void deleteByID(Long id){

        liczarkiRepository.deleteById(id);}
}
