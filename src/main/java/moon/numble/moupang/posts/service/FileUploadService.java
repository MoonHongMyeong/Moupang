package moon.numble.moupang.posts.service;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface FileUploadService {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    String getFileUrl(String fileName);
}
