package com.aerolinea.aerolinea.others.service;

import com.aerolinea.aerolinea.others.entity.DocumentType;
import com.aerolinea.aerolinea.others.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public List<DocumentType> getAll() {
        return documentTypeRepository.findAll();
    }
}
