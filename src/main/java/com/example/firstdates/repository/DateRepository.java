package com.example.firstdates.repository;

import com.example.firstdates.model.FirstDate;
import com.example.firstdates.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateRepository extends JpaRepository<FirstDate, Integer> {

    List<FirstDate> findByUserReceiveDateIsNull();

    List<FirstDate> findByUserCreateDate(User currentUser);


    List<FirstDate> findByUserCreateDateAndUserReceiveDateIsNotNullAndStatusIsNull(User user);

    List<FirstDate> findByStatus(boolean b);
}
