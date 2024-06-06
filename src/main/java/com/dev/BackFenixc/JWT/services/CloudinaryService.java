package com.dev.BackFenixc.JWT.services;

import com.cloudinary.Cloudinary;
import com.dev.BackFenixc.JWT.controller.request.CloudinaryResponse;
import com.dev.BackFenixc.constantes.FuncErrorException;
import org.hibernate.query.sqm.produce.function.FunctionArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;
    @Transactional
    public CloudinaryResponse uploadFile (final MultipartFile file, final String fileName){
        try{
            final Map result = this.cloudinary.uploader().upload(file.getBytes(),
                    Map.of("public_id","tola/product/"+fileName));
            final String url = (String) result.get("secure_url");
            final String publicId= (String) result.get("public_id");
            return CloudinaryResponse.builder().publicId(publicId).url(url).build();
        }catch (final Exception e){
            throw new FuncErrorException("Failed to upload file");
        }
    }

}
