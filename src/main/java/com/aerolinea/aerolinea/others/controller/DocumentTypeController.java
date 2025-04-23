package com.aerolinea.aerolinea.others.controller;

import com.aerolinea.aerolinea.others.entity.DocumentType;
import com.aerolinea.aerolinea.others.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/api/document-type")
    public List<DocumentType> getDocumentTypes(){
        return documentTypeService.getAll();
    }
}