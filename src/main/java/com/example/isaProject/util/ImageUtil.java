package com.example.isaProject.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageUtil {
    private final Path root;
    private final String picturesDirectory = "src/main/resources/static/pictures";

    public ImageUtil() {
        try {
            root = Paths.get(picturesDirectory).toAbsolutePath().normalize();
            Files.createDirectories(root);  // Ensure the pictures directory exists
        } catch (IOException e) {
            throw new RuntimeException("Could not create or access the upload folder!", e);
        }
    }

    public String saveMultipartFileToFolder(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }

            // Resolve destination path
            Path destinationFile = this.root.resolve(file.getOriginalFilename()).normalize().toAbsolutePath();

            // Transfer the file to the destination
            file.transferTo(destinationFile);
            return destinationFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    public Resource getPicture(String pictureFileName) {
        try {
            Path picturePath = root.resolve(pictureFileName).normalize();
            Resource pictureResource = new UrlResource(picturePath.toUri());

            if (pictureResource.exists() && pictureResource.isReadable()) {
                return pictureResource;
            } else {
                return null;
            }

        } catch (MalformedURLException ex) {
            return null;
        }
    }
}
