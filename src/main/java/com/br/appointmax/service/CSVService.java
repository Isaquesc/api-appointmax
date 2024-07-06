package com.br.appointmax.service;

import org.springframework.web.multipart.MultipartFile;

public interface CSVService {

    String sendFile(MultipartFile file);
}
