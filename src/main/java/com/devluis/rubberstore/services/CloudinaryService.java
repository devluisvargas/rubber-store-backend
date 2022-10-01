package com.devluis.rubberstore.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {


    private Map<String, Object> valueMap = new HashMap<>();

    private Cloudinary cloudinary;

    public CloudinaryService(
            @Value("${cloudinary.name}") String name,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret
    ) {
        valueMap.put("cloud_name", name);
        valueMap.put("api_key", apiKey);
        valueMap.put("api_secret", apiSecret);
        valueMap.put("secure", true);
        cloudinary = new Cloudinary(valueMap);
    }

    public Map<String, String> upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map<String, String> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    public Map<String, String> deleteImg(String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }


    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }

}
