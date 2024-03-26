package com.example.firstdates.repository;

import com.example.firstdates.model.FirstDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateRepository extends JpaRepository<FirstDate, Integer> {

    List<FirstDate> findByUserReceiveDateIsNull();

    List<FirstDate> findByUserCreateDateIduser(Integer iduser);
}
