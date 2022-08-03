package ru.learnup.learnup.spring.mvc.homework34.services;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
public class MessageBody implements Serializable {

    private String title;
    private String date;
//    private Date date;

}
