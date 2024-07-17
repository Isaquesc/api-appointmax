package com.br.appointmax.domain.service.serviceImpl;

import com.br.appointmax.domain.constants.StringConstants;
import com.br.appointmax.domain.model.Client;
import com.br.appointmax.domain.repository.ClientRepository;
import com.br.appointmax.domain.service.CSVService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVServiceImpl implements CSVService {

    private static final Logger logger = LoggerFactory.getLogger(CSVServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    //TODO: [Isaque Silva] - alterar o modo no qual recebemos o arquivo, para string [atualmente recebemos em MultipartFile] assim transportamos esses dados de uma maneira mais segura.
    @Override
    public String sendFile(MultipartFile file) {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<Client> clients = new ArrayList<>();
            String[] line;

            while ((line = csvReader.readNext()) != null) {
                if (isLineValid(line)) {
                    processLine(line, clients);
                } else {
                    logger.warn(StringConstants.INVALID_DATA_WARNING, (Object) line);
                }
            }

            clientRepository.saveAll(clients);
            logger.info(StringConstants.CSV_UPLOAD_SUCCESS);

            return StringConstants.CSV_UPLOAD_SUCCESS;
        } catch (IOException e) {
            logger.error(StringConstants.CSV_UPLOAD_IO_ERROR, e);

            return StringConstants.CSV_UPLOAD_IO_ERROR;
        } catch (CsvValidationException e) {
            logger.error(StringConstants.CSV_UPLOAD_VALIDATION_ERROR, e);

            return StringConstants.CSV_UPLOAD_VALIDATION_ERROR;
        }
    }

    private boolean isLineValid(String[] lineData) {
        return Arrays.stream(lineData).anyMatch(value -> isValidEmail(value) || isValidPhone(value));
    }

    private boolean isValidEmail(String email) {
        return StringConstants.EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPhone(String phone) {
        return StringConstants.PHONE_PATTERN.matcher(phone).matches();
    }

    private void processLine(String[] data, List<Client> clients) {
        //TODO ajustar
        Client client = new Client();
        client.setName(data[0]);
        client.setPhone(data[1]);
        client.setEmail(data[2]);
        clients.add(client);
    }
}
