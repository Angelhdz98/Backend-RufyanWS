package com.example.PaginaWebRufyan.DomainTest;

import com.example.PaginaWebRufyan.domain.model.ImageProcessor;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ImageProcessorTest {
    Path imagePath1;
    Path imagePath2;
    Path imagePath3;
    ClassLoader classLoader = getClass().getClassLoader();

    List<MultipartFile> images;

    MockMultipartFile mockFile1;
    MockMultipartFile mockFile2;
    MockMultipartFile mockFile3 ;

    @TempDir
    static  Path tempDirBase;

    private Path testSpecificPath;


    @BeforeEach
    public void setUp() throws IOException {

        String testId = "test-"+ UUID.randomUUID()+"-"+ Instant.now().toEpochMilli();
        testSpecificPath = tempDirBase.resolve(testId);
        Files.createDirectories(testSpecificPath);
        System.setProperty("app.upload.dir",testSpecificPath.toString());

        try {
            imagePath1 = Paths.get(classLoader.getResource("static/obra1.jpg").toURI());

            imagePath2 = Paths.get(classLoader.getResource("static/obra2.jpg").toURI());
            imagePath3 = Paths.get(classLoader.getResource("static/obra3.jpg").toURI());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        byte[] image1Bytes = Files.readAllBytes(imagePath1);
        byte[] image2Bytes = Files.readAllBytes(imagePath2);
        byte[] image3Bytes = Files.readAllBytes(imagePath3);
        mockFile1 = new MockMultipartFile("obra1", "obra1.jpg", "image/jpg", image1Bytes);
        mockFile2 = new MockMultipartFile("obra2", "obra2.jpg", "image/jpg", image2Bytes);
        mockFile3 = new MockMultipartFile("obra3", "obra3.jpg", "image/jpg", image3Bytes);
        images = List.of(mockFile1,mockFile2, mockFile3);
    }

    @Test
    void shouldSaveImagesOnDefaultPath(){

        Set<ImageDomain> nameTest = ImageProcessor.processImages(images, "nameTest");

       assertThat(nameTest.stream().findFirst().get().url()).doesNotContain("null");
    }
}
