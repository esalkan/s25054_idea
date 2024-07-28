package com.s25054_idea.s25054.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum FormDurumu {
    ONAY_BEKLIYOR("onay bekliyor"),
    ONAYLANDI("onaylandi"),
    REDDEDILDI("reddedildi");

    private String value;

    FormDurumu(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class FormDurumuConverter implements AttributeConverter<FormDurumu, String> {
        @Override
        public String convertToDatabaseColumn(FormDurumu attribute) {
            return attribute == null ? null : attribute.getValue();
        }

        @Override
        public FormDurumu convertToEntityAttribute(String dbData) {
            if (dbData == null) {
                return null;
            }

            for (FormDurumu formDurumu : FormDurumu.values()) {
                if (formDurumu.getValue().equals(dbData)) {
                    return formDurumu;
                }
            }

            throw new IllegalArgumentException("Unknown value: " + dbData);
        }
    }
}
