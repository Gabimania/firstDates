package com.example.firstdates.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class FirstDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user1")
    private  User userCreateDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user2")
    private User userReceiveDate;

    private LocalDate date;

    private boolean status;

    public Integer getIddate() {
        return iddate;
    }

    public void setIddate(Integer iddate) {
        this.iddate = iddate;
    }

    public User getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(User userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public User getUserReceiveDate() {
        return userReceiveDate;
    }

    public void setUserReceiveDate(User userReceiveDate) {
        this.userReceiveDate = userReceiveDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Date{" +
                "iddate=" + iddate +
                ", userCreateDate=" + userCreateDate +
                ", userReceiveDate=" + userReceiveDate +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
