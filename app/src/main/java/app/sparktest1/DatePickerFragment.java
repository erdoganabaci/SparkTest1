package app.sparktest1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

   /* DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
            this,year,month,day);*/
   private String flag;

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
       // return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year,month,day);
       return new DatePickerDialog(getActivity(),AlertDialog.THEME_HOLO_LIGHT,
                this,year,month,day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        month = month +1;
        String myDateFormat = dayOfMonth + "/" + month + "/" + year;
        //String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        if (flag == SportRegister.FLAG_DATE_BIRTHDAY){
            SportRegister.sportBirthday.setText(myDateFormat);
        }else if (flag == SportRegister.FLAG_DATE_CURRENT){
            SportRegister.sportCurrentDate.setText(myDateFormat);
        }
    }
}
