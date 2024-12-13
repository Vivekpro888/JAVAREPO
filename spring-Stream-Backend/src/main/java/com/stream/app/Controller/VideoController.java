package com.stream.app.Controller;

import com.stream.app.Models.Custommass;
import com.stream.app.Models.Video;
import com.stream.app.ServicRepo.VideoSerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")  // Allows CORS for all methods in this controller
public class VideoController {

    @Autowired
    private VideoSerImp videoService;

    @GetMapping
    private String hello() {
        return "Hello from the Video Controller!";
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("Title") String title,
            @RequestParam("Description") String description) {

        // Ensure the file is present
        if (file.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Custommass.builder()
                            .msg("No file selected")
                            .scc(false)
                            .build());
        }

        // Create a Video object and set the properties
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());

        // Save the video details and the file
        Video savedVideo = videoService.save(video, file);

        // Check if the video was saved successfully
        if (savedVideo != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(savedVideo);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Custommass.builder()
                            .msg("Failed to save video")
                            .scc(false)
                            .build());
        }
    }

    @GetMapping("/Stream/{videoid}")
    public ResponseEntity<Resource> StreamVideobyId(@PathVariable String videoid) {
        Video video = videoService.get(videoid);

        String contentType=video.getContanetData();
        String path=video.getFilepath();

        Resource resource=new FileSystemResource(path);
        if (video != null) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        }
        return null;

    }

    @PostMapping("/GetByTitle")
    public ResponseEntity<?> VideoByTitle(@RequestParam("Title") String title){
        Video video = videoService.getByTitle(title);
        if (video != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(video);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("/Lists")
    public  ResponseEntity<?>ListOfVideos(){

        List<Video> video=videoService.getall();
        if(video!=null){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(video);

        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("Sorry No any videos abloded");

        }
    }

//    @GetMapping("/Stream/Range/{videoid}")
//    public ResponseEntity<Resource> StreamVideobyId(@PathVariable String videoid
//            , @RequestHeader(value = "Range", required = false) String range) {
//        System.out.println(range);
//        //
//        Video video= videoService.get(videoid);
//        Path path= Paths.get(video.getFilepath());
//
//        Resource resource=new FileSystemResource(path);
//
//        String contenttype=video.getContanetData();
//
//        //file len
//        long fileLength=path.toFile().length();
//
//        if (range==null){
//            return  ResponseEntity
//                    .ok()
//                    .contentType(MediaType.parseMediaType(contenttype))
//                    .body(resource);
//        }
//
//        long strrang,endrange;
//
//        String[] ranges= range.replace("bytes","").split("-");
//
//        endrange=Long.parseLong(ranges[0]);
//        if()
//    }



@GetMapping("/Stream/Range/{videoid}")
public ResponseEntity<Resource> StreamVideobyId(
        @PathVariable String videoid,
        @RequestHeader(value = "Range", required = false) String rangeHeader) throws Exception {

    Video video = videoService.get(videoid);
    Path path = Paths.get(video.getFilepath());
    Resource resource = new FileSystemResource(path);

    if (!resource.exists()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    long fileLength = path.toFile().length();
    String contentType = video.getContanetData();

    if (rangeHeader == null) {
        // Serve the entire file if no Range header is present
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(fileLength)
                .body(resource);
    }

    // Parse the Range header
    String[] ranges = rangeHeader.replace("bytes=", "").split("-");
    long startRange = Long.parseLong(ranges[0]);
    long endRange = ranges.length > 1 && !ranges[1].isEmpty()
            ? Long.parseLong(ranges[1])
            : fileLength - 1;

    // Validate ranges
    if (endRange >= fileLength || startRange > endRange) {
        return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
                .header("Content-Range", "bytes */" + fileLength)
                .build();
    }

    System.out.println(fileLength);
    System.out.println(startRange);
    System.out.println(endRange);
    // Serve the specified range
    long contentLength = endRange - startRange + 1;
    RandomAccessFile randomAccessFile = new RandomAccessFile(path.toFile(), "r");
    randomAccessFile.seek(startRange);

    byte[] buffer = new byte[(int) contentLength];
    randomAccessFile.readFully(buffer);
    randomAccessFile.close();

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .header("Content-Type", contentType)
            .header("Content-Range", "bytes " + startRange + "-" + endRange + "/" + fileLength)
            .contentLength(contentLength)
            .body(new ByteArrayResource(buffer));
}

}
