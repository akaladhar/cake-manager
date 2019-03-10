package com.waracle.cakemgr.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cake")
public class Cake implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITLE", length = 250, nullable = false)
    private String title;
    @Column(name = "DESCRIPTION", length = 250)
    private String desc;
    @Column(name = "URL", length = 250)
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}