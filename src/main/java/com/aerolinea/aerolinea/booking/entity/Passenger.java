package com.aerolinea.aerolinea.booking.entity;

import jakarta.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String documentType;
    private String document;
    private Integer age;
    private Boolean needsSpecialAssistance;
    private String specialAssistanceDescription;

    public Passenger() {
    }

    public Passenger(Long id, String name, String documentType, String document,
                     Integer age, Boolean needsSpecialAssistance, String specialAssistanceDescription) {
        this.id = id;
        this.name = name;
        this.documentType = documentType;
        this.document = document;
        this.age = age;
        this.needsSpecialAssistance = needsSpecialAssistance;
        this.specialAssistanceDescription = specialAssistanceDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getNeedsSpecialAssistance() {
        return needsSpecialAssistance;
    }

    public void setNeedsSpecialAssistance(Boolean needsSpecialAssistance) {
        this.needsSpecialAssistance = needsSpecialAssistance;
    }

    public String getSpecialAssistanceDescription() {
        return specialAssistanceDescription;
    }

    public void setSpecialAssistanceDescription(String specialAssistanceDescription) {
        this.specialAssistanceDescription = specialAssistanceDescription;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentType='" + documentType + '\'' +
                ", document='" + document + '\'' +
                ", age=" + age +
                ", needsSpecialAssistance=" + needsSpecialAssistance +
                ", specialAssistanceDescription='" + specialAssistanceDescription + '\'' +
                '}';
    }
}