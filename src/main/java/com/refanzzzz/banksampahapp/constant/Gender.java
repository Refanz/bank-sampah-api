package com.refanzzzz.banksampahapp.constant;

public enum Gender {
    LAKI_LAKI("Laki - Laki"),
    PEREMPUAN("Perempuan");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender getGenderByName(String name) {
        for (Gender gender : values()) {
            if (gender.name.equalsIgnoreCase(name))
                return gender;
        }

        return null;
    }
}
