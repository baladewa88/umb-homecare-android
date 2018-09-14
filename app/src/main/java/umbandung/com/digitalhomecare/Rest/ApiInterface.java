package umbandung.com.digitalhomecare.Rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import umbandung.com.digitalhomecare.KlinikUtil;
import umbandung.com.digitalhomecare.LayananUtil;
import umbandung.com.digitalhomecare.Model.GetOrder;
import umbandung.com.digitalhomecare.Model.PostPutDelOrder;

/**
 * Created by Arkhan on 9/8/2018.
 */

public interface ApiInterface {

    @GET("order_android")
    Call<GetOrder> getOrder();
    @FormUrlEncoded
    @POST("order")
    Call<PostPutDelOrder> postOrder(@Field("id") String id,
                                     @Field("transactionTypeId") String trxTypeId);

    @FormUrlEncoded
    @PUT("kontak")
    Call<PostPutDelOrder> putKontak(@Field("id") String id,
                                     @Field("nama") String nama,
                                     @Field("nomor") String nomor);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
    Call<PostPutDelOrder> deleteKontak(@Field("id") String id);

    @GET("/api/clinics")
    Call<List<KlinikUtil>> getClinics();

    @GET("/api/listOfservices/{id}")
    Call<List<LayananUtil>> getServices(@Path("id") String id);

    @POST("/api/transaction")
    Call<List<>>


}
