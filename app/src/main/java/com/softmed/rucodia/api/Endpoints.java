package com.softmed.rucodia.api;

import com.softmed.rucodia.Dom.entities.TransactionType;
import com.softmed.rucodia.Dom.entities.Transactions;
import com.softmed.rucodia.Dom.responces.BalancesResponse;
import com.softmed.rucodia.Dom.responces.CategoriesResponse;
import com.softmed.rucodia.Dom.responces.LoginResponse;
import com.softmed.rucodia.Dom.responces.ProductsPostResponse;
import com.softmed.rucodia.Dom.responces.ProductsResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


public class Endpoints {

    private String HFUUID = "";

    public interface LoginService {
        @GET("auth")
        Call<List<LoginResponse>> basicLogin();

        @GET("users")
        Call<List<LoginResponse>> getAllUsers();

    }

    public interface CategoriesService {
        @GET("categories")
        Call<List<CategoriesResponse>> getCategories();

    }

    public interface ProductsService {
        @GET("products")
        Call<List<ProductsResponse>> getProducts();

        @POST("products")
        Call<ProductsPostResponse> postProducts(@Body RequestBody e);
    }

    public interface TransactionServices{
        @GET
        Call<List<Transactions>> getTransactions(@Url String url);

        @GET
        Call<BalancesResponse> getBalances(@Url String url);

        @GET("transactiontypes")
        Call<List<TransactionType>> getTransactionTypes();

        @POST("transactions")
        Call<Transactions> postTransaction(@Body RequestBody e);

    }

    public interface NotificationServices{
        @POST("save-push-notification-token")
        Call<String> registerDevice(@Body RequestBody u);
    }
    public interface OrdersServices{
        @POST("orders")
        Call<BalancesResponse> sendOrder(@Body RequestBody u);
    }

}
