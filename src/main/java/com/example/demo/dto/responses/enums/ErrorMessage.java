package com.example.demo.dto.responses.enums;

public enum ErrorMessage {
    MISSING_REQUIRED_FIELD("Champ obligatoire manquant."),
    RECORD_ALREADY_EXISTS("L'enregistrement existe déjà."),
    INTERNAL_SERVER_ERROR("Erreur interne du serveur."),
    NO_RECORD_FOUND("L'enregistrement avec l'identifiant fourni est introuvable.");

    private String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
