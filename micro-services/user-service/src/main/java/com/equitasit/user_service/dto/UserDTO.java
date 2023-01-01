package com.equitasit.user_service.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class UserDTO {

    private Integer id;

    private String email;

    private String fname;

    private String lname;


    private Date lastLoginTime;
}
