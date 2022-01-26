package com.dians.navigation.placesservice.utils;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

public class Mapper {
    private static CsvMapper mapperInstance;

    private Mapper(){}

    public static synchronized CsvMapper getInstance(){
        if(mapperInstance == null){
            mapperInstance = new CsvMapper();
        }
        return mapperInstance;
    }
}
