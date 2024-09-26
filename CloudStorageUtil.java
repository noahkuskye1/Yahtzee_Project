package com.google.cloud;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CloudStorageUtil extends YahtzeeGame{
    private static String bucketName = "yahtzee_game_bucket";
    private static String fileName = "C:\\Users\\noah_kuskye\\Downloads\\Yahtzee_Project\\Yahtzee_Project\\gameData.txt";

    public static void uploadScore() throws IOException{
        Storage storage = StorageOptions.getDefaultInstance().getService();

        File file = new File(fileName);
        if(file.exists()){
            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            byte[] bytes = Files.readAllBytes(Paths.get(fileName));
            storage.create(blobInfo, bytes);
        }
    }
}
