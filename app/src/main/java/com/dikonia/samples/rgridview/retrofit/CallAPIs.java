package com.dikonia.samples.rgridview.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CallAPIs {


    @GET("albums/1/photos")
    Call<ArrayList<ItemPojo>> getImages();

}
