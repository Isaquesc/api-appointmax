package com.br.appointmax.domain.constants;

import java.util.regex.Pattern;

public class CSVServiceConstants {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    public static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");

    public static final String CSV_UPLOAD_SUCCESS = "CSV upload successful";
    public static final String CSV_UPLOAD_IO_ERROR = "Failed to upload CSV due to an IO error";
    public static final String CSV_UPLOAD_VALIDATION_ERROR = "Failed to upload CSV due to a validation error";
    public static final String INVALID_DATA_WARNING = "Invalid data in line: {}";

}
