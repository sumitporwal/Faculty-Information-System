package Upload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import Model.Document;

public class File_Upload {
	
	@Value("${file.upload-dir}")
	private static String uploadDirectory;

	public static void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save file: " + fileName, ioe);
        }      
    }
	
/*	public static Document getFile(String fileName) throws IOException {
        Path uploadPath = getUploadDirLocation().resolve(fileName).normalize();
         
        try {
        	Resource resource = new UrlResource(uploadPath.toUri());
        	MetaData metadata = getFileMetaDataInfo(resource);
        	if(resource.exists()) {
        		Document document = new Document();
        		document.setResource(resource);
        		document.setFileName(fileName);
        		document.setSize(metadata.size());
        	}
        }
    }
	
	private static Path getUploadDirLocation() {
		return Paths.get(uploadDirectory).toAbsolutePath().normalize();
	}
	
	private static Metadata getFileMetaDataInfo(Resource resource) {
        AutoDetectParser parser = new AutoDetectParser();
        Detector detector = parser.getDetector();
        Metadata metadata = new Metadata();
        try {
            metadata.set(Metadata.RESOURCE_NAME_KEY, resource.getFile().getName());
            TikaInputStream stream = TikaInputStream.get(resource.getInputStream());
            MediaType mediaType = detector.detect(stream, metadata);
            metadata.set(Metadata.CONTENT_TYPE, mediaType.toString());
        } catch (IOException e) {
            e.printStackTrace();
            //fallback to default content type
            metadata.set(Metadata.CONTENT_TYPE, "application/octet-stream");

        }
        return metadata;
    }*/
}
