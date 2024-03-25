package com.example.firstdates.repository;

import com.example.firstdates.model.Date;
import com.example.firstdates.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateRepository extends JpaRepository<Date, Integer> {

    List<Date> findByUserReceiveDateIsNull();

    List<Date> findByUserCreateDateIduser(Integer iduser);
}
