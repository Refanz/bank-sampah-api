package com.refanzzzz.banksampahapp.constant;

import lombok.Getter;

@Getter
public enum Gender {
    LAKI_LAKI("Laki - Laki"),
    PEREMPUAN("Perempuan");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public static String getNameByGender(String gender) {
        for (Gender val : values()) {
            if (val.name().equalsIgnoreCase(gender))
                return val.getName();
        }

        return null;
    }

    public static Gender getGenderByName(String name) {
        for (Gender gender : values()) {
            if (gender.name.equalsIgnoreCase(name))
                return gender;
        }

        return null;
    }
}
