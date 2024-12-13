package com.stream.app.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    private  String Id;

    private String title;

//    @OneToMany
//    @JoinColumn
//    private List<Video>Videos;
}
