package umbandung.com.digitalhomecare.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arkhan on 9/8/2018.
 */

public class Auth {

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
