package com.example.PaginaWebRufyan.ControllerTests;

import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;


@Component
public class ImageInitializer {

    //class made to prepare image data for testing so will use MultipartFile


    private final ClassPathResource resource1  =new ClassPathResource("images/obra1.jpg");
    private final ClassPathResource resource2  =new ClassPathResource("images/obra2.jpg");
    private final ClassPathResource resource3  =new ClassPathResource("images/obra3.jpg");

    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;
    private MultipartFile file4;
    @Getter
    private byte[] image1Bytes = new byte[50*1024];
    @Getter
    private byte[] image2Bytes = new byte[50*1024];

    public ImageInitializer() {
    }

    public void readFiles() throws IOException {
        File retrieveFile1 = resource1.getFile();
        File retrieveFile2 = resource2.getFile();
        File retrieveFile3 = resource3.getFile();
        this.file1 =  new MockMultipartFile(retrieveFile1.getName(),"image1.jpg","image/jpeg", resource1.getInputStream());
        this.file2 =  new MockMultipartFile(retrieveFile2.getName(),"image2.jpg","image/jpeg", resource2.getInputStream());
        this.file3 =  new MockMultipartFile(retrieveFile3.getName(),"image3.jpg","image/jpeg", resource3.getInputStream());
        file4 =  new MockMultipartFile(retrieveFile1.getName(),"image1.jpg","image/jpeg", resource1.getInputStream());
        Path p1 = retrieveFile1.toPath();
        Path p2 = retrieveFile2.toPath();
        image1Bytes = Files.readAllBytes(p1);
        image2Bytes = Files.readAllBytes(p2);
    }

    public Set<MultipartFile> getSet1Files(){
        return Set.of(file1,file2);
    }
    public Set<MultipartFile> getSet2Files(){
        return Set.of(file3,file4);
    }

}
