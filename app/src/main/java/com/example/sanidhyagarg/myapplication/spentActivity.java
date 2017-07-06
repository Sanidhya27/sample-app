package com.example.sanidhyagarg.myapplication;
import com.example.sanidhyagarg.myapplication.data.adddbhelper;
import  com.example.sanidhyagarg.myapplication.data.spentdbhelper;
import com.example.sanidhyagarg.myapplication.data.cashContract;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class spentActivity extends AppCompatActivity {
    private spentdbhelper a;float bal=0;

    private DatePicker datePicker;
    private Calendar calendar;
    private EditText dateView;
    private int year, month, day;
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spent);
        a=new spentdbhelper(this);
        dateView = (EditText) findViewById(R.id.datespent);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        Log.v("SpentActivity",String.valueOf(month));
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);



    }
    private void showDate(int year, int month, int day) {
        //dateView=(EditText) findViewById(R.id.datespent) ;
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    public void spenttransaction(){
        EditText am=(EditText) findViewById(R.id.spentmoney);
        am.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );
        EditText dat=(EditText) findViewById(R.id.datespent);
        EditText ad=(EditText) findViewById(R.id.spentdescription);
        float amount=Integer.parseInt(am.getText().toString());
        String date=dat.getText().toString();
        String des=ad.getText().toString();
        ContentValues v=new ContentValues();
        SQLiteDatabase db=a.getWritableDatabase();
        v.put(cashContract.spententry.MONEY_SPENT,amount);
        v.put(cashContract.spententry.DESCRIPTION,des);
        v.put(cashContract.spententry.DATE,date);
        db.insert(cashContract.spententry.TABLE_NAME,null,v);
        bal+=amount;

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.save,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){


        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                spenttransaction();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option

            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }





    public void setDate(View view) {
        showDialog(999);
        // Toast.makeText(getApplicationContext(), "ca",
        //       Toast.LENGTH_SHORT)
        //     .show();V
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

}



