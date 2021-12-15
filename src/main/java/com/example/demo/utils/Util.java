package com.example.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import com.example.demo.utils.passwordGenerator.PasswordGenerator;

public class Util {
    public static String GeneratePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String password = passwordGenerator.generate(8);
        return password;
    }

    public static LocalDate convertStringToDate(String dateString) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(dateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }
}
