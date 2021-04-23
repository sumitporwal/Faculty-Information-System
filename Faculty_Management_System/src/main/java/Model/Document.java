package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Document {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long document_id;
	   
	   private String fileName;
	   private String newFileName;
	   private String fileDownloadUri;
	   private String fileType;
	   private long size;
	   
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Document(long document_id, String fileName, String newFileName, String fileDownloadUri, String fileType,
			long size) {
		super();
		this.document_id = document_id;
		this.fileName = fileName;
		this.newFileName = newFileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}
	
	public long getDocument_id() {
		return document_id;
	}
	public void setDocument_id(long document_id) {
		this.document_id = document_id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getNewFileName() {
		return newFileName;
	}
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	   
	   
}
