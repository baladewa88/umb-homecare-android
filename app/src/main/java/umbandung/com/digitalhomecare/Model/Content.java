package umbandung.com.digitalhomecare.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import umbandung.com.digitalhomecare.Clinic;

public class Content {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("dateBirth")
    @Expose
    private Object dateBirth;
    @SerializedName("placeBirth")
    @Expose
    private Object placeBirth;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("photoPath")
    @Expose
    private Object photoPath;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("firstRegistrationDate")
    @Expose
    private Object firstRegistrationDate;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("doctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("religion")
    @Expose
    private Object religion;
    @SerializedName("registerNumber")
    @Expose
    private Object registerNumber;
    @SerializedName("specialist")
    @Expose
    private Object specialist;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("clinic")
    @Expose
    private Clinic clinic;
    @SerializedName("roles")
    @Expose
    private List<String> roles = null;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("accountNonExpired")
    @Expose
    private Boolean accountNonExpired;
    @SerializedName("credentialsNonExpired")
    @Expose
    private Boolean credentialsNonExpired;
    @SerializedName("accountNonLocked")
    @Expose
    private Boolean accountNonLocked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Object dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Object getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(Object placeBirth) {
        this.placeBirth = placeBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(Object photoPath) {
        this.photoPath = photoPath;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public void setFirstRegistrationDate(Object firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getReligion() {
        return religion;
    }

    public void setReligion(Object religion) {
        this.religion = religion;
    }

    public Object getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Object registerNumber) {
        this.registerNumber = registerNumber;
    }

    public Object getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Object specialist) {
        this.specialist = specialist;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

}
