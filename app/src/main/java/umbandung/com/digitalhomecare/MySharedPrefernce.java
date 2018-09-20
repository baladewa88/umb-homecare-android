package umbandung.com.digitalhomecare;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Arkhan on 9/3/2018.
 */

public class MySharedPrefernce {

    public static final String PREFS_NAME = "AOP_PREFS";
    public static final String PREFS_KEY = "token";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_SEX = "sex";
    public static final String KEY_ROLE = "role";
    public static final String KEY_DOB = "dob";
    public static final String KEY_ID = "id";
    public static final String KEY_ECG = "ecg";
    public static final String KEY_CLINIC_ID = "id";

    public MySharedPrefernce() {
        super();
    }

    public void save(Context context, String nama, String alamat, String email,String phone,
                     String sex, String role, String dob, String token, String id, String deviceCode, String idClinic) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(KEY_NAMA, nama); //3
        editor.putString(KEY_ALAMAT, alamat); //3
        editor.putString(KEY_EMAIL, email); //3
        editor.putString(KEY_PHONE, phone); //3
        editor.putString(KEY_SEX, sex); //3
        editor.putString(KEY_ROLE, role); //3
        editor.putString(KEY_DOB, dob); //3
        editor.putString(PREFS_KEY, token); //3
        editor.putString(KEY_ID, id); //3 //3
        editor.putString(KEY_ECG, deviceCode); //3
        editor.putString(KEY_CLINIC_ID, idClinic); //3

        editor.commit(); //4
    }

    public String[] getValue(Context context) {
        SharedPreferences settings;
        String[] text = new String[11];

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text[0] = settings.getString(KEY_NAMA, null);
        text[1] = settings.getString(KEY_ALAMAT, null);
        text[2] = settings.getString(KEY_EMAIL, null);
        text[3] = settings.getString(KEY_PHONE, null);
        text[4] = settings.getString(KEY_SEX, null);
        text[5] = settings.getString(KEY_ROLE, null);
        text[6] = settings.getString(KEY_DOB, null);
        text[7] = settings.getString(PREFS_KEY, null);
        text[8] = settings.getString(KEY_ID, null);
        text[9] = settings.getString(KEY_ECG, null);
        text[10] = settings.getString(KEY_CLINIC_ID, null);

        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(PREFS_KEY);
        editor.commit();
    }

    public void store(Context context, String key, String value){
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public String getValueByKey(Context context, String key) {
        SharedPreferences settings;
        String text = null;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(key, null);

        return text;
    }
}
