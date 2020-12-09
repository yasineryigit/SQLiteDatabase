package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //db adında database varsa aç, yoksa oluştur
            SQLiteDatabase db = this.openOrCreateDatabase("Fighters",MODE_PRIVATE,null);

            db.execSQL("CREATE TABLE IF NOT EXISTS fighters (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");

            //Her defasında aynı veriyi eklemesin diye comment haline getirdik
            //db.execSQL("INSERT INTO fighters (name,age) VALUES ('McGregor',32)");
            //db.execSQL("INSERT INTO fighters (name,age) VALUES ('Alvarez',36)");
            //db.execSQL("INSERT INTO fighters (name,age) VALUES ('Ferguson',36)");

            //db.execSQL("UPDATE fighters SET name='Jose Aldo' WHERE name='Alvarez'");
            //db.execSQL("UPDATE fighters SET name='George StPierre' WHERE age=36");

            //db.execSQL("INSERT INTO fighters (name,age) VALUES ('Dustin Poirier',25)");

            //db.execSQL("DELETE FROM fighters WHERE id=4");

            Cursor c = db.rawQuery("SELECT * FROM fighters WHERE name LIKE 'M%'",null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            while(c.moveToNext()){
                System.out.println("Name: " + c.getString(nameIndex));
                System.out.println("Age: " +c.getInt(ageIndex));
                System.out.println("ID:" + c.getInt(idIndex));
            }

            c.close();



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
