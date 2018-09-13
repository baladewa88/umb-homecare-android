package umbandung.com.digitalhomecare;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by github/fiyyanputra on 9/13/2018.
 */

class EmployeeRecyclerView extends RecyclerView.Adapter<EmployeeRecyclerView.ViewHolder>{

    private MySharedPrefernce mySharedPrefernce;
    private static String[] namesArray, codesArray, licensesArray,
            roleArray, addressArray, emailArray,
            placeBirthArray, dateBirthArray, religionArray,
            genderArray, clinicNamesArray, employeeIDarray, clinicIDArray;



    public EmployeeRecyclerView(List<String> employeeID, List<String> names,
                                List<String> codes, List<String> license, List<String> phoneNumbers,
                                List<String> addresss, List<String> emails, List<String> placeBirths,
                                List<String> dateBirths, List<String> genders, List<String> religions,
                                List<String> clinicsID, List<String> clinicsNames, List<String> roles) {

        mySharedPrefernce = new MySharedPrefernce();
        employeeIDarray = new String[employeeID.size()];
        namesArray = new String[names.size()];
        codesArray = new String[codes.size()];
        addressArray = new String[addresss.size()];
        emailArray = new String[emails.size()];
        placeBirthArray = new String[placeBirths.size()];
        dateBirthArray = new String[dateBirths.size()];
        genderArray = new String[genders.size()];
        religionArray = new String[religions.size()];
        clinicIDArray = new String[clinicsID.size()];
        clinicNamesArray = new String[clinicsNames.size()];
        roleArray = new String[roles.size()];
        licensesArray = new String[license.size()];

        employeeIDarray = employeeID.toArray(employeeIDarray);
        namesArray = names.toArray(namesArray);
        codesArray = codes.toArray(codesArray);
        addressArray = addresss.toArray(addressArray);
        emailArray = emails.toArray(emailArray);
        placeBirthArray = placeBirths.toArray(placeBirthArray);
        dateBirthArray = dateBirths.toArray(dateBirthArray);
        genderArray = genders.toArray(genderArray);
        religionArray = religions.toArray(religionArray);
        clinicIDArray = clinicsID.toArray(clinicIDArray);
        clinicNamesArray = clinicsNames.toArray(clinicNamesArray);
        roleArray = roles.toArray(roleArray);
        licensesArray = license.toArray(licensesArray);
    }

    @Override
    public EmployeeRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeRecyclerView.ViewHolder holder, final int position) {
        holder.tvEmployeeName.setText(namesArray[position]);
        holder.tvEmployeeGender.setText(genderArray[position]);
        holder.tvEmployeeCode.setText(codesArray[position]);
        holder.tvEmployeeClinic.setText(clinicNamesArray[position]);
        holder.tvEmployeeLicense.setText(licensesArray[position]);
        holder.tvEmployeeRole.setText(roleArray[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailEmployee.class);
                mySharedPrefernce.store(v.getContext(), "EMPLOYEE_ID", employeeIDarray[position]);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return namesArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvEmployeeName, tvEmployeeGender, tvEmployeeCode,
                tvEmployeeClinic, tvEmployeeLicense, tvEmployeeRole;

        public ViewHolder(View itemView) {

            super(itemView);
            tvEmployeeName = (TextView) itemView.findViewById(R.id.employee_name);
            tvEmployeeGender = (TextView) itemView.findViewById(R.id.employee_gender);
            tvEmployeeCode = (TextView) itemView.findViewById(R.id.employee_code);
            tvEmployeeClinic = (TextView) itemView.findViewById(R.id.employee_clinic);
            tvEmployeeLicense = (TextView) itemView.findViewById(R.id.employee_license);
            tvEmployeeRole = (TextView) itemView.findViewById(R.id.label_role);
        }
    }
}
