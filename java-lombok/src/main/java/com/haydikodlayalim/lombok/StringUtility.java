package com.haydikodlayalim.lombok;

import lombok.experimental.UtilityClass;

@UtilityClass  //@UtilityClass annotation which creates a private constructor that throws an exception, makes the class final, and makes all methods static.
public class StringUtility {

    public String trim(String value) {
        return value.trim();
    }

    public String trimPerson(Person value) {
        return value.toString();
    }
}
