package com.example.demo.services;

import java.io.InputStream;
import java.util.List;

import com.example.demo.entities.Candidate;

public interface UploadService {
    List<Candidate> uploadCandidates(InputStream is);
}
