package edu.upc.dsa.andoroid_dsa;

import androidx.annotation.BinderThread;

import java.util.List;

import edu.upc.dsa.andoroid_dsa.models.Credentials;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;
import edu.upc.dsa.andoroid_dsa.models.UserId;
import edu.upc.dsa.andoroid_dsa.models.UserInformation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("shop/user/register")
    Call<User> createUser(@Body User user);

    @POST("shop/user/login")
    Call<UserId> logIn(@Body Credentials credentials);

    @GET("shop/gadget/all")
    Call<List<Gadget>> getGadgets();

    @GET("shop/user/{idUser}")
    Call<UserInformation> getUser(@Path("idUser") String idUser);
}
