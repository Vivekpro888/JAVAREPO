package com.stream.app.ServicRepo;

import com.stream.app.Models.Video;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface VideoSerRepo {
    //save v

    Video save(Video vi, MultipartFile file);
    //get v by id

    Video get(String videoId);



     Video NewSave(Video video,MultipartFile file);

    Video getByTitle(String videoTitle);

    List<Video>getall();

    String ProccesVideo(String fileId ,MultipartFile file);
}
