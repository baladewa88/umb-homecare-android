
package umbandung.com.digitalhomecare.Model.nurse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class IdNurse {

    @SerializedName("accountNonExpired")
    private Boolean mAccountNonExpired;
    @SerializedName("accountNonLocked")
    private Boolean mAccountNonLocked;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("clinic")
    private Clinic mClinic;
    @SerializedName("credentialsNonExpired")
    private Boolean mCredentialsNonExpired;
    @SerializedName("dateBirth")
    private String mDateBirth;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("enabled")
    private Boolean mEnabled;
    @SerializedName("firstRegistrationDate")
    private Object mFirstRegistrationDate;
    @SerializedName("fullName")
    private String mFullName;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("id")
    private Long mId;
    @SerializedName("latitude")
    private Object mLatitude;
    @SerializedName("longitude")
    private Object mLongitude;
    @SerializedName("nurseCode")
    private String mNurseCode;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("photoPath")
    private Object mPhotoPath;
    @SerializedName("placeBirth")
    private String mPlaceBirth;
    @SerializedName("religion")
    private String mReligion;
    @SerializedName("roles")
    private List<String> mRoles;
    @SerializedName("sipp")
    private String mSipp;
    @SerializedName("status")
    private Status mStatus;
    @SerializedName("username")
    private Object mUsername;

    public Boolean getAccountNonExpired() {
        return mAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        mAccountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return mAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        mAccountNonLocked = accountNonLocked;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Clinic getClinic() {
        return mClinic;
    }

    public void setClinic(Clinic clinic) {
        mClinic = clinic;
    }

    public Boolean getCredentialsNonExpired() {
        return mCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        mCredentialsNonExpired = credentialsNonExpired;
    }

    public String getDateBirth() {
        return mDateBirth;
    }

    public void setDateBirth(String dateBirth) {
        mDateBirth = dateBirth;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Boolean getEnabled() {
        return mEnabled;
    }

    public void setEnabled(Boolean enabled) {
        mEnabled = enabled;
    }

    public Object getFirstRegistrationDate() {
        return mFirstRegistrationDate;
    }

    public void setFirstRegistrationDate(Object firstRegistrationDate) {
        mFirstRegistrationDate = firstRegistrationDate;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Object getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Object latitude) {
        mLatitude = latitude;
    }

    public Object getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Object longitude) {
        mLongitude = longitude;
    }

    public String getNurseCode() {
        return mNurseCode;
    }

    public void setNurseCode(String nurseCode) {
        mNurseCode = nurseCode;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public Object getPhotoPath() {
        return mPhotoPath;
    }

    public void setPhotoPath(Object photoPath) {
        mPhotoPath = photoPath;
    }

    public String getPlaceBirth() {
        return mPlaceBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        mPlaceBirth = placeBirth;
    }

    public String getReligion() {
        return mReligion;
    }

    public void setReligion(String religion) {
        mReligion = religion;
    }

    public List<String> getRoles() {
        return mRoles;
    }

    public void setRoles(List<String> roles) {
        mRoles = roles;
    }

    public String getSipp() {
        return mSipp;
    }

    public void setSipp(String sipp) {
        mSipp = sipp;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public Object getUsername() {
        return mUsername;
    }

    public void setUsername(Object username) {
        mUsername = username;
    }

}
