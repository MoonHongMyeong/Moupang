package moon.numble.moupang.posts.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadRequestService {

    private final FileUploadService s3Service;

    public List<String> uploadImages(List<MultipartFile> requestFiles){

        List<String> files = new ArrayList<>();

        for(MultipartFile file : requestFiles){

            String fileName = createFileName(file.getOriginalFilename());

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            try(InputStream inputStream = file.getInputStream()){

                s3Service.uploadFile(inputStream, objectMetadata, fileName);

            } catch (IOException e) {

                throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생했습니다. (%s)", file.getOriginalFilename()));

            }
            files.add(fileName);
        }
        return files;
    }

    private String createFileName(String originalFilename) {
        return UUID.randomUUID().toString().concat(originalFilename);
    }


}
