package com.minealex2244.univtest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    // as we are making get request so we are displaying
    // GET as annotation.
    // and inside we are passing last parameter for our url.
    @GET("WO6S")

    // as we are calling data from array so we are calling
    // it with array list and naming that method as getAllCourses();
    Call<ArrayList<RecyclerData>> getAllCourses();
}