package com.stream.app.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Video")
public class Video {
    @Id
    private  String VideoId;
    private String Title;
    private  String Description;
    private  String ContanetData;
    private  String Filepath;

//    @OneToMany(mappedBy = "Videos")
//    private Course course;

}
