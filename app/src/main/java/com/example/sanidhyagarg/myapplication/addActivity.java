package com.example.sanidhyagarg.myapplication;
import  com.example.sanidhyagarg.myapplication.data.adddbhelper;
import com.example.sanidhyagarg.myapplication.data.cashContract;
import android.content.Intent;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class addActivity extends AppCompatActivity {
    private adddbhelper a;float bal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        a=new adddbhelper(this);


    }
    public void addtransaction(){
        EditText am=(EditText) findViewById(R.id.addmoney);
        EditText ad=(EditText) findViewById(R.id.adddescription);
        float amount=Integer.parseInt(am.getText().toString());
        String des=ad.getText().toString();
        ContentValues v=new ContentValues();
        SQLiteDatabase db=a.getWritableDatabase();
        v.put(cashContract.addentry.MONEY_ADDED,amount);
        v.put(cashContract.addentry.DESCRIPTION,des);
        db.insert(cashContract.addentry.TABLE_NAME,null,v);
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
                addtransaction();
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
    }

