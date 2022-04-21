package com.danli.common.lang.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Login data entity class
 * This class is used to implement user login
 *
 * @author Yicong Wang
 * @date   2022
 */
@Slf4j
@Data
public class LoginDto extends AbstractMethodError implements Serializable  {

    @NotBlank(message = "Nicknames cannot be empty")
    private String username;
    @NotBlank(message = "The password cannot be empty")
    private String password;


    public static void main(String[] args) {


        InputStream inputStream = LoginDto.class.getResourceAsStream("");


    }

}
