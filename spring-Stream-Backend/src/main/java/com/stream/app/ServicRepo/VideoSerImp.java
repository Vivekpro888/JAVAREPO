package com.stream.app.ServicRepo;

import com.stream.app.Models.Video;
import com.stream.app.REpository.VideoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoSerImp implements VideoSerRepo {

    // Directory where videos will be stored
    @Value("${files.video}")
    private String dir;

    @Value("${filesCap}")
    private  String NEWPATH;

    // Ensure the upload directory exists
    @PostConstruct
    public void init() {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs(); // mkdirs() creates any missing parent directories as well
            System.out.println("Folder created at: " + dir);
        } else {
            System.out.println("Folder already exists at: " + dir);
        }

        File file1=new File(NEWPATH);
        if (!file1.exists()){
            file1.mkdir();
            System.out.println("Folder created at: " + NEWPATH);
        } else {
            System.out.println("Folder already exists at: " + NEWPATH);
        }
    }

    @Autowired
    private VideoRepository videoRepository;

    // Save video and file to the server
    @Override
    public Video save(Video video, MultipartFile file) {
        try {


            // Get file details
            String filename = file.getOriginalFilename();
            String fileContentType = file.getContentType();

            // Input stream to read the file data
            InputStream inputStream = file.getInputStream();

            // Sanitize the folder path
            String cleanFolderPath = StringUtils.cleanPath(dir);

            // Sanitize the filename (avoid any unwanted path traversal)
            String cleanFileName = StringUtils.cleanPath(filename);

            // Create the full path to store the file
            Path path = Path.of(cleanFolderPath, cleanFileName);

            //console show
            System.out.println(cleanFolderPath);
            System.out.println(cleanFileName);
            System.out.println(path.toString());

            // Ensure the file is saved with proper file extension
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            // Set the video metadata (content type and file path)
            video.setContanetData(fileContentType);
            video.setFilepath(path.toString());

            // Save the video metadata to the database
            return videoRepository.save(video);

        } catch (IOException e) {
            // Log error and return null if something goes wrong
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Video get(String videoId) {
        // Add implementation if needed
        Video video=videoRepository.findById(videoId).orElse(null);
        if(video!=null){
            return video;
        }

        return null;
    }

    @Override
    public Video NewSave(Video video,MultipartFile file) {
        String fileName = file.getName();
        String FileContent = file.getContentType();
        String FileNameClean = StringUtils.cleanPath(fileName);
        String FileContentClean = StringUtils.cleanPath(FileContent);


        try {
            InputStream inputStream = file.getInputStream();

           Path path=Paths.get(NEWPATH,fileName);
            System.out.println(path.toString());

            Files.copy(inputStream,path,StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Video getByTitle(String videoTitle) {

      for(Video v:videoRepository.findAll()){
          if(v.getTitle().equals(videoTitle)){
              return v;
          }
      }
//        Video video=videoRepository.findById(videoId).orElse(null);
//        if(video!=null){
//            return video;
//        }
        // Add implementation if needed
        return null;
    }

    @Override
    public List<Video> getall() {
        // Add implementation to fetch all videos if needed
        return videoRepository.findAll();
    }

    @Override
    public String ProccesVideo(String fileId, MultipartFile file) {
        Video file1 = this.get(fileId); // Assuming 'get' fetches the video metadata from the database or storage
        String filepath = file1.getFilepath();
        Path path = Paths.get(filepath);

        String Output360p = NEWPATH + fileId + "/360p/";
        String Output720p = NEWPATH + fileId + "/720p/";
        String Output1080p = NEWPATH + fileId + "/1080p/";



        try {
            // Create directories for each resolution if they don't already exist
            Files.createDirectories(Paths.get(Output360p));
            Files.createDirectories(Paths.get(Output720p));
            Files.createDirectories(Paths.get(Output1080p));





            System.out.println("Video processing completed successfully.");
        } catch (IOException e) {
            System.err.println("Error processing video: " + e.getMessage());
            return "Video processing failed.";
        }

        return "Video processing completed successfully for file ID: " + fileId;
    }

}
