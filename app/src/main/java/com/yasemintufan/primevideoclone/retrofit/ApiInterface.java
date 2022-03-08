package com.yasemintufan.primevideoclone.retrofit;

import com.yasemintufan.primevideoclone.model.BannerMovies;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("similar?q=red+hot+chili+peppers%2C+pulp+fiction")
    Observable<List<BannerMovies>>getAllBanners();
}
