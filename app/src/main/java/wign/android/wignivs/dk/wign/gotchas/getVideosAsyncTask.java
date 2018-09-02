package wign.android.wignivs.dk.wign.gotchas;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Response;
import wign.android.wignivs.dk.wign.model.Video;
import wign.android.wignivs.dk.wign.model.Word;
import wign.android.wignivs.dk.wign.api.ApiService;

/**
 * Created by Troels on 11/04/2017.
 */

public class getVideosAsyncTask extends AsyncTask<String, Void, Void> {
    private ApiService apiService;

    public getVideosAsyncTask(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    protected Void doInBackground(String... queries) {
        Realm realm = null;

        try {
            realm = Realm.getDefaultInstance();
            for (String query : queries) {

                final Word word = realm.where(Word.class).equalTo("word", query).findFirst();
                if(word == null) { return null; }

                Call<List<Video>> call = apiService.getTheVideos(query); // Defining the call to fetch all videos from Wign API
                Response<List<Video>> response = call.execute(); // Sync download
                final List<Video> videoList = response.body(); // Get the list of videos

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        word.getList().addAll(videoList);
                        System.out.println("Video API successful. Data stored in realm.");
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        return null;
    }

}
