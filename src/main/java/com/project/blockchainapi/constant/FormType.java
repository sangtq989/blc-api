package com.project.blockchainapi.constant;

public enum FormType {
    CERT_EDU("CERT_EDU"),
    CERT_AWARD("CERT_AWARD"),
    CERT_LICENSES("CERT_LICENSES"),
    CERT_COURSE("CERT_COURSE"),
    SPECIALTY("SPECIALTY"),
    EXPERIENCE("EXPERIENCE");
    private final String type;

    FormType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
