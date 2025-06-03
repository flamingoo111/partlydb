package com.example.usersdb.user;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserRepository personRepository;
    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private CustomUserVacancyRepository customUserVacancyRepository;
    @Autowired
    private GetVacancyUserRepository getUserVacancyRepository;
    @Autowired
    private SavedRepository savedRepository;
    @Autowired
    private AcceptRepositorySaved acceptSavedRepository;
    @RequestMapping(path="/")
    public String index() {
        return "index";
    }
    @GetMapping(path="/addperson")
    public @ResponseBody
    String addNewPerson (@RequestParam String login, @RequestParam String password, @RequestParam String type) {
        Users person = new Users();
        Iterable<Users> server_JSONResponse = personRepository.findUsersByLogin(login);
        long size = server_JSONResponse.spliterator().getExactSizeIfKnown();
        if (size == 0) {
            if (login != null && !login.isEmpty() && password != null && !password.isEmpty() && type != null && !type.isEmpty()) {
                System.out.println(login + " " + password + " " + type + " " );
                person.setLogin(login);
                person.setPassword(password);
                person.setType(type);
                personRepository.save(person);
                return "Person saved!";
            } else {
                return "Enter all fields";
            }
        }else {
            return "Login Существует!";
        }
    }
//    @GetMapping(path="/allpersons")
//    public @ResponseBody
//    Iterable<Users> getAllUsers() {
//        return personRepository.findAll();
//    }
    @GetMapping(path="/findbylogin")
    public @ResponseBody
    String getbyLogin(@RequestParam String login, @RequestParam String password) {
        Iterable<Users> server_JSONResponse = personRepository.findUsersByLogin(login);
        for(Users user : server_JSONResponse) {
            if(password.equals(user.getPassword())){
                return "True";
            }else{return "False";}
        }
        return "Usersnotfound";
    }
    @GetMapping(path="/allvacancy")
    public @ResponseBody
    Iterable<Vacancy> getAllVacancy() {
        return vacancyRepository.findAll();
    }
    @PostMapping(path="/addvacancy")
    String addNewVacancy(@RequestBody Vacancy vacancy) {
        System.out.println("123123123");
        vacancyRepository.save(vacancy);
        return "Vacancy saved!";
    }
    @GetMapping(path="/findvacancy")
    public @ResponseBody Iterable<Vacancy> findVacancyByName(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            return (Iterable<Vacancy>) vacancyRepository.findAll();
        }
        return vacancyRepository.findByNameMatchingOrStartingWith(name.trim());
    }
    @GetMapping(path="/findvacanciesbylogin")
    public @ResponseBody List<Vacancy> findVacanciesByUserLogin(@RequestParam String login) {
        return customUserVacancyRepository.findVacanciesByUserLogin(login);
    }
    @GetMapping(path="/findgetvacanciesbylogin")
    public @ResponseBody List<Vacancy> findGetVacanciesByUserLogin(@RequestParam String login) {
        return getUserVacancyRepository.findVacanciesByUserLogin(login);
    }
    @GetMapping(path="/updateSavedIdByLogin")
    public @ResponseBody String updateSavedIdByLogin(@RequestParam Integer[] id, @RequestParam String login) {
        savedRepository.updateSavedIdByLogin(id, login);
        return "Vacancy saved!";
    }
    @GetMapping(path="/updateAcceptIdBylogin")
    public @ResponseBody String updateAcceptIdByLogin(@RequestParam Integer[] id, @RequestParam String login) {
        acceptSavedRepository.updateAcceptIdByLogin(id, login);
        return "Vacancy saved!";
    }
}