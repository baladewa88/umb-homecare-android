
package umbandung.com.digitalhomecare.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserPatient {

    @SerializedName("accountNonExpired")
    private Boolean mAccountNonExpired;
    @SerializedName("accountNonLocked")
    private Boolean mAccountNonLocked;
    @SerializedName("address")
    private Object mAddress;
    @SerializedName("clinic")
    private Clinic mClinic;
    @SerializedName("credentialsNonExpired")
    private Boolean mCredentialsNonExpired;
    @SerializedName("dateBirth")
    private String mDateBirth;
    @SerializedName("deviceCode")
    private String mDeviceCode;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("enabled")
    private Boolean mEnabled;
    @SerializedName("firstRegistrationDate")
    private Object mFirstRegistrationDate;
    @SerializedName("fullName")
    private String mFullName;
    @SerializedName("gender")
    private Object mGender;
    @SerializedName("id")
    private Long mId;
    @SerializedName("latitude")
    private Object mLatitude;
    @SerializedName("longitude")
    private Object mLongitude;
    @SerializedName("occupation")
    private Object mOccupation;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("patientCode")
    private String mPatientCode;
    @SerializedName("phoneNumber")
    private Object mPhoneNumber;
    @SerializedName("photoPath")
    private Object mPhotoPath;
    @SerializedName("placeBirth")
    private Object mPlaceBirth;
    @SerializedName("religion")
    private Object mReligion;
    @SerializedName("roles")
    private List<String> mRoles;
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

    public Object getAddress() {
        return mAddress;
    }

    public void setAddress(Object address) {
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

    public String getDeviceCode() {
        return mDeviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        mDeviceCode = deviceCode;
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

    public Object getGender() {
        return mGender;
    }

    public void setGender(Object gender) {
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

    public Object getOccupation() {
        return mOccupation;
    }

    public void setOccupation(Object occupation) {
        mOccupation = occupation;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPatientCode() {
        return mPatientCode;
    }

    public void setPatientCode(String patientCode) {
        mPatientCode = patientCode;
    }

    public Object getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public Object getPhotoPath() {
        return mPhotoPath;
    }

    public void setPhotoPath(Object photoPath) {
        mPhotoPath = photoPath;
    }

    public Object getPlaceBirth() {
        return mPlaceBirth;
    }

    public void setPlaceBirth(Object placeBirth) {
        mPlaceBirth = placeBirth;
    }

    public Object getReligion() {
        return mReligion;
    }

    public void setReligion(Object religion) {
        mReligion = religion;
    }

    public List<String> getRoles() {
        return mRoles;
    }

    public void setRoles(List<String> roles) {
        mRoles = roles;
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
