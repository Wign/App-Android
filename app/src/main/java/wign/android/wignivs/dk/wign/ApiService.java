package wign.android.wignivs.dk.wign;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Troels on 05/04/2017.
 */

public interface ApiService {
    @GET("/words")
    Call<List<Word>> getAllWords();

    @GET("/words/{words}")
    Call<List<Word>> words(
            @Path("words") String words
    );

    @GET("/video/{word}")
    Call<List<Video>> getTheVideos(
            @Path("word") String word
    );
}