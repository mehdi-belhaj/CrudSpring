package com.example.demo.services.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.Candidate;
import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Poste;
import com.example.demo.services.UploadService;
import com.example.demo.utils.Util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImp implements UploadService {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "firstname", "lastname", "username", "email", "dateOfBirth", "phone", "poste",
            "activityArea" };

    @Override
    public List<Candidate> uploadCandidates(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Candidate> Candidates = new ArrayList<Candidate>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            // Iterable<CSVRecord> records =
            // CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fileReader);

            for (CSVRecord csvRecord : csvRecords) {

                Candidate candidate = new Candidate(csvRecord.get("firstname"), csvRecord.get("lastname"),
                        csvRecord.get("username"),
                        csvRecord.get("email"), Util.GeneratePassword(),
                        Util.convertStringToDate(csvRecord.get("dateOfBirth")), csvRecord.get("phone"),
                        Poste.valueOf(csvRecord.get("poste")), ActivityArea.valueOf(csvRecord.get("activityArea")));

                Candidates.add(candidate);
            }

            return Candidates;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
