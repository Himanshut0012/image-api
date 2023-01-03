package com.file.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.entity.ImageData;
import com.file.repository.StorageRepository;
import com.file.util.ImageUtils;

@Service
public class StorageService {

	@Autowired
	private StorageRepository storageRepository;
	
	
	public String uploadImage(MultipartFile file) throws IOException {
		ImageData imageData = storageRepository.save(ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.imageData(ImageUtils.compressImage(file.getBytes()))
				.build());
		if(imageData!=null)
			return "fileupload successfully"+file.getOriginalFilename();
		return "file not uploaded";
	}
	
	
	
	public byte[] downloadImage(String name) {
		Optional<ImageData> imageDataDB = storageRepository.findByName(name);
		byte[] decompressImage = ImageUtils.decompressImage(imageDataDB.get().getImageData());
		return decompressImage;
	}
}
