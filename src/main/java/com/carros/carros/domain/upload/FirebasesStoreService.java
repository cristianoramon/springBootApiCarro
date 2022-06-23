package com.carros.carros.domain.upload;

import com.carros.carros.api.upload.UploadInput;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class FirebasesStoreService {

    @PostConstruct
    private void init() throws IOException{
        if (FirebaseApp.getApps().isEmpty()){
            InputStream in = FirebasesStoreService.class.getResourceAsStream("/serviceAccountKey.json");
            System.out.println(in);
            FirebaseOptions option = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(in))
                    .setStorageBucket("flutter-carro-9c36d.appspot.com")
                    .setDatabaseUrl("")
                    .build();
            FirebaseApp.initializeApp(option);
        }
    }

    public String upload(UploadInput uploadInput){

        Bucket bucket = StorageClient.getInstance().bucket();
        byte[] bytes = Base64.getDecoder().decode(uploadInput.getBase64());
        String fileName = uploadInput.getFileName();
        Blob blob = bucket.create(fileName,bytes,uploadInput.getMimeType());
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER));
        return String.format("https//store.google.com/%s/%s",bucket.getName(),fileName);
    }

}
