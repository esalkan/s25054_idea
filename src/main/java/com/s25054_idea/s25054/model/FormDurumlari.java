package com.s25054_idea.s25054.model;

import jakarta.persistence.*;

@Entity
@Table(name = "form_durumlari")
public class FormDurumlari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long durumId;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private UrunAlimFormu form;

    @Convert(converter = FormDurumu.FormDurumuConverter.class)
    private FormDurumu durum;

    public Long getDurumId() {
        return durumId;
    }

    public void setDurumId(Long durumId) {
        this.durumId = durumId;
    }

    public UrunAlimFormu getForm() {
        return form;
    }

    public void setForm(UrunAlimFormu form) {
        this.form = form;
    }

    public FormDurumu getDurum() {
        return durum;
    }

    public void setDurum(FormDurumu durum) {
        this.durum = durum;
    }
}
