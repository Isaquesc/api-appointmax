package com.br.appointmax.service.serviceImpl;

import com.br.appointmax.model.Client;
import com.br.appointmax.repository.ClientRepository;
import com.br.appointmax.service.CSVService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CSVServiceImpl implements CSVService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public String sendFile(MultipartFile file) {
        // Usando CSVReader do OpenCSV para ler o arquivo CSV
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<Client> clients = new ArrayList<>();
            String[] line;

            // Lê cada linha do CSV
            while ((line = csvReader.readNext()) != null) {
                // Verifica se a linha contém dados válidos (email ou telefone)
                if (containsValidData(line)) {
                    // Processa a linha e adiciona um objeto Client à lista
                    processLineAsClient(line, clients);
                }
            }

            // Salva todos os clientes no repositório de uma vez
            clientRepository.saveAll(clients);
            return "CSV upload successful";
        } catch (IOException | CsvValidationException e) {
            // Trata as exceções de IO e validação de CSV
            e.printStackTrace();
            return "Failed to upload CSV";
        }
    }

    // Método que verifica se a linha contém pelo menos um email ou telefone válido
    private boolean containsValidData(String[] lineData) {
        for (String value : lineData) {
            if (isValidEmail(value) || isValidPhone(value)) {
                return true;
            }
        }
        return false;
    }

    // Método que valida se a string é um email válido usando regex
    private boolean isValidEmail(String str) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        return emailPattern.matcher(str).matches();
    }

    // Método que valida se a string é um telefone válido usando regex
    private boolean isValidPhone(String str) {
        Pattern phonePattern = Pattern.compile("^\\+?[0-9]{10,15}$");
        return phonePattern.matcher(str).matches();
    }

    // Método que processa uma linha do CSV e adiciona um objeto Client à lista
    private void processLineAsClient(String[] data, List<Client> clients) {
        // Cria um novo objeto Client e define seus atributos
        Client client = new Client();
        client.setName(data[0]);
        client.setPhone(data[1]);
        client.setEmail(data[2]);
        // Adiciona o cliente à lista de clientes
        clients.add(client);
    }
}
