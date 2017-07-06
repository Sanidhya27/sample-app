package com.example.sanidhyagarg.myapplication;
import  com.example.sanidhyagarg.myapplication.data.adddbhelper;
import com.example.sanidhyagarg.myapplication.data.cashContract;
import com.example.sanidhyagarg.myapplication.data.spentdbhelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import org.w3c.dom.Text;





public class MainActivity extends AppCompatActivity {
private adddbhelper a;float bal=0;private spentdbhelper s;float bal1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=new adddbhelper(this);
        Log.d("MainActivity",a.h);//setting up
        Log.v("MainActivity","create");

        ImageView add =(ImageView) findViewById(R.id.add);    //setting up the three textviews and their onclicklistners
        ImageView spent=(ImageView) findViewById(R.id.spent);
       // TextView balance=(TextView) findViewById(R.id.balance);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,addActivity.class);
                startActivity(i);



            }
        });

        spent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,spentActivity.class);
                startActivity(i);
            }
        });

       // balance.setOnClickListener(new View.OnClickListener() {
         //   @Override
           //// public void onClick(View view) {
               // Intent i=new Intent(MainActivity.this,balanceActivity.class);
           // startActivity(i);}
        //});

        s=new spentdbhelper(this);//instantiating the adddbhelper

    }
    @Override
    protected void onStart() {
        Log.v("MainActivity","start");
        TextView ap=(TextView) findViewById(R.id.addpassbook);
        TextView sp=(TextView) findViewById(R.id.spentpassbook);
        ap.setMovementMethod(new ScrollingMovementMethod());
        sp.setMovementMethod(new ScrollingMovementMethod());
        super.onStart();
        displayDatabaseInfo();   //display the database
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the  database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        Log.v("MainActivity","create");
        float added,spent;
        SQLiteDatabase db = a.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        Log.d("MainActivity",cashContract.addentry.DATE);
        String[] projection = {
                cashContract.addentry._ID,
                cashContract.addentry.MONEY_ADDED,
                cashContract.addentry.DESCRIPTION,
                cashContract.addentry.DATE
                };
        Log.d("MainActivity",projection[0]);
        Log.d("MainActivity",a.toString());
        // Perform a query on the pets table
        Cursor cursor = db.query(
                cashContract.addentry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause  selection
                null,                  // The values for the WHERE clause   selection args
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        Log.d("MainActivity",cursor.toString());
        TextView displayView = (TextView) findViewById(R.id.addpassbook);
        TextView display = (TextView) findViewById(R.id.balance);

        try {

            Log.v("MainActivity","creat5e");
            displayView.setText("\n"+cashContract.addentry._ID + " - "+cashContract.addentry.DATE +"-"+
                    cashContract.addentry.MONEY_ADDED + " - " +
                    cashContract.addentry.DESCRIPTION + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(cashContract.addentry._ID);
            int amountColumnIndex = cursor.getColumnIndex(cashContract.addentry.MONEY_ADDED);
            int descriptionColumnIndex = cursor.getColumnIndex(cashContract.addentry.DESCRIPTION);
            Log.v("MainActivity","create5");
            int dateColumnIndex= cursor.getColumnIndex(cashContract.addentry.DATE);
            Log.v("MainActivity","create");


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
               float am = Float.parseFloat(cursor.getString(amountColumnIndex));
                String description= cursor.getString(descriptionColumnIndex);
                String date=cursor.getString(dateColumnIndex);
bal+=am;
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +date+"-"+
                       am + " - " +
                        description));
            }display.setText(String.valueOf(bal));
            added=bal;
            bal=0;
        } finally {

            cursor.close();
        }


        SQLiteDatabase db1 = s.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection1 = {
                cashContract.spententry._ID,
                cashContract.spententry.MONEY_SPENT,
                cashContract.spententry.DESCRIPTION,
                cashContract.spententry.DATE
        };

        // Perform a query on the pets table
        Cursor cursor1 = db1.query(
                cashContract.spententry.TABLE_NAME,   // The table to query
                projection1,            // The columns to return
                null,                  // The columns for the WHERE clause  selection
                null,                  // The values for the WHERE clause   selection args
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView1 = (TextView) findViewById(R.id.spentpassbook);
        TextView display1 = (TextView) findViewById(R.id.balance);

        try {


            displayView1.setText("\n"+cashContract.spententry._ID + " - " +cashContract.spententry.DATE+"-"+
                    cashContract.spententry.MONEY_SPENT + " - " +
                    cashContract.spententry.DESCRIPTION + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor1.getColumnIndex(cashContract.spententry._ID);
            int amountColumnIndex = cursor1.getColumnIndex(cashContract.spententry.MONEY_SPENT);
            int descriptionColumnIndex = cursor1.getColumnIndex(cashContract.spententry.DESCRIPTION);
            int dateColumnIndex=cursor1.getColumnIndex(cashContract.spententry.DATE);


            // Iterate through all the returned rows in the cursor
            while (cursor1.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor1.getInt(idColumnIndex);
                float am = Float.parseFloat(cursor1.getString(amountColumnIndex));
                String description= cursor1.getString(descriptionColumnIndex);
                String date=cursor1.getString(dateColumnIndex);
                bal1+=am;
                // Display the values from each column of the current row in the cursor in the TextView
                displayView1.append(("\n" + currentID + " - " +date+"-"+
                        am + " - " +
                        description));
            }
            spent=bal1;
            display1.setText("CASH IN HAND = Rs."+String.valueOf(added-spent));
            bal1=0;
        } finally {

            cursor1.close();
        }

    }


    private void insertamount()
    {
SQLiteDatabase db=a.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(cashContract.addentry.MONEY_ADDED,1000);
        values.put(cashContract.addentry.DESCRIPTION,"hello");
        db.insert(cashContract.addentry.TABLE_NAME,null,values);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.dummy,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        insertamount();
        displayDatabaseInfo();
        return true;
    }
}
