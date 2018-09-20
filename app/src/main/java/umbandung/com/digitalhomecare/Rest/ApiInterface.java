package umbandung.com.digitalhomecare.Rest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import umbandung.com.digitalhomecare.KlinikUtil;
import umbandung.com.digitalhomecare.LayananUtil;
import umbandung.com.digitalhomecare.Model.Auth;
import umbandung.com.digitalhomecare.Model.EcgHasil;
import umbandung.com.digitalhomecare.Model.EcgUtil;
import umbandung.com.digitalhomecare.Model.GetOrder;
import umbandung.com.digitalhomecare.Model.Order;
import umbandung.com.digitalhomecare.Model.PostPutDelOrder;
import umbandung.com.digitalhomecare.Model.TransaksiGet;
import umbandung.com.digitalhomecare.Model.TransaksiHasil;
import umbandung.com.digitalhomecare.Transaksi;
import umbandung.com.digitalhomecare.TransaksiUtil;

/**
 * Created by Arkhan on 9/8/2018.
 */

public interface ApiInterface {

    @GET("api/transaction")
    Call<GetOrder> getOrder();

    @FormUrlEncoded
    @POST("authenticate/")
    Call<Auth> getAuth(@Query("param") String param, @Field("email") String email, @Field("password") String password);

    @POST("api/transaction")
    Call<PostPutDelOrder> postOrder(@Body Order order);

    @GET("api/listOfservices/1")
    Call<List<LayananUtil>> getSecret(@Header("Authorization")  String authToken);

    @GET("api/clinics")
    Call<List<KlinikUtil>> getKlinik(@Header("Authorization")  String authToken);

    @GET("/api/clinics")
    Call<List<KlinikUtil>> getClinics();


    @GET("/api/listOfservices/{id}")
    Call<List<LayananUtil>> getServices(@Path("id") String id);

    @GET("api/transactionWithPaginationByIdPatient?page=0&size=10&sort=ASC&sortField=id&patientId={patientId}")
    Call<TransaksiGet> historyTrx(@Header("Authorization")  String authToken, @Path("patientId") String patientId);

    @GET("api/transactionWithPaginationByIdPatient?page=0&size=10&sort=ASC&sortField=id")
    Call<TransaksiHasil> historyTrxNew(@Header("Authorization")  String authToken, @Query("patientId") String patientId);

    @GET("api/ecgWithPaginationByEcgCodeAndDate?page=0&size=200&sort=ASC&sortField=id")
    Call<EcgHasil> getEcg(@Header("Authorization")  String authToken, @Query("ecgCode") String ecgCode, @Query("date") String date);

    @GET("api/ecgWithPaginationByEcgCodeAndDate?page=0&size=200&sort=ASC&sortField=id")
    Call<EcgHasil> getEcgData(@Query("ecgCode") String ecgCode, @Query("date") String date);

    @FormUrlEncoded
    @PUT("kontak")
    Call<PostPutDelOrder> putKontak(@Field("id") String id,
                                     @Field("nama") String nama,
                                     @Field("nomor") String nomor);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
    Call<PostPutDelOrder> deleteKontak(@Field("id") String id);
}
