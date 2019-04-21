package pub.spring;

import org.springframework.web.multipart.MultipartFile;
import pub.types.NotImplementedException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
 
public class MultipartFileHolder implements MultipartFile {

	private byte[] bytes;
	private String name;
	private String originalFilename;
	private String contentType;

	public MultipartFileHolder() {
		 
	}

	public MultipartFileHolder(MultipartFile file) {
		if(file == null || file.isEmpty()) {
			bytes = new byte[0];
			return;
		}
		try {
			bytes = file.getBytes();
			name = file.getName();
			originalFilename = file.getOriginalFilename();
			contentType = file.getContentType();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
 
	public String getName() {
		return name;
	}
 
	public String getOriginalFilename() {
		return originalFilename;
	}
 
	public String getContentType() {
		return contentType;
	}
 
	public boolean isEmpty() {
		return bytes.length == 0;
	}
 
	public long getSize() {
		return bytes.length;
	}
 
	public byte[] getBytes() {
		return bytes;
	}
 
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(getBytes());
	}
 
	public void transferTo(File dest) throws IOException, IllegalStateException {
		throw new NotImplementedException();
	}
}
