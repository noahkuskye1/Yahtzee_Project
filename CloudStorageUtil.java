import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class CloudStorageUtil {
    private static String bucketName = "yahtzee_game_bucket";
    private static String fileName = "YahtzeeGame.java";
    private static String content = "gameData.txt";
    public static void main(String[] args)throws Exception{
        uploadScore();
    }
    public void uploadScore(String bucketName, String fileName, String content) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, content.getBytes());
    }
}
