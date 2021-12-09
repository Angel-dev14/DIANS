package com.dians.navigation.service.utils;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvUtils {
    private static final CsvMapper mapper = new CsvMapper();
    public static <T> List<T> read(Class<T> modelClass, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(modelClass).withHeader().withColumnReordering(true);

        ObjectReader reader = mapper.readerFor(modelClass).with(schema);

        return reader.<T>readValues(stream).readAll();
    }
}
