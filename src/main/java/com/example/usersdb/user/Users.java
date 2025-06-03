package com.example.usersdb.user;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Users {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;
    private String login;
    private String password;
    private String type;
    @ElementCollection
    private List<Integer> saved_id;
    @ElementCollection
    private List<Integer> get_vacancy_id;

    public List<Integer> getGet_vacancy_id() {return get_vacancy_id;}

    public void setGet_vacancy_id(List<Integer> get_vacancy_id) {this.get_vacancy_id = get_vacancy_id;}

    public List<Integer> getSaved_id() {
        return saved_id;
    }

    public void setSaved_id(List<Integer> saved_id) {
        this.saved_id = saved_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
