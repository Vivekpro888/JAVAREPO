package com.stream.app.REpository;

import com.stream.app.Models.Video;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,String> {

}
