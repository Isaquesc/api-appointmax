package com.br.appointmax.api.controller;

import com.br.appointmax.domain.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/csv")
    public class CSVController {

    @Autowired
    private CSVService csvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(csvService.sendFile(file));
    }
}
