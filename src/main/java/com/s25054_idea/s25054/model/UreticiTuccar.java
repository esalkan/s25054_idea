package com.s25054_idea.s25054.model;

import jakarta.persistence.*;

@Entity
@Table(name = "uretici_tuccar")
public class UreticiTuccar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "uretici_id")
    private int ureticiId;

    @Column(name = "tuccar_id")
    private int tuccarId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUreticiId() {
        return ureticiId;
    }

    public void setUreticiId(int ureticiId) {
        this.ureticiId = ureticiId;
    }

    public int getTuccarId() {
        return tuccarId;
    }

    public void setTuccarId(int tuccarId) {
        this.tuccarId = tuccarId;
    }
}
