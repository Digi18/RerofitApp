package com.app.retrofitapp.Retrofit;

import com.app.retrofitapp.Pojo.BioData;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("retrofitUsers")
    @FormUrlEncoded
    Observable<String> saveData(@Field("name") String name,
                                 @Field("age") String age);

    //Implementation using Flowable

    @GET("getUsers")
    Flowable<List<BioData>> getData();

    /**
     * Implementation using Observable
     */


   /* @GET("getUsers")
    Observable<List<BioData>> getData(); */

}
