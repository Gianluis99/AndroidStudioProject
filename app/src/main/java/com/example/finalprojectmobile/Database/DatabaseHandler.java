package com.example.finalprojectmobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.finalprojectmobile.Model.Cliente;
import com.example.finalprojectmobile.Model.Prenotazione;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private String emailUser="";
    public  static  final String nameDB= "clientihandler.db";


    public String getEmailUser() {
        return emailUser;
    }

    public DatabaseHandler(@Nullable Context context) {
        super(context, "Clientihandler.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Users(email TEXT primary key, password TEXT)");
        db.execSQL("create Table Clienti(email TEXT , id TEXT , nome TEXT, cognome TEXT,eta TEXT,primary key(email,id))");
        db.execSQL("create Table Prenotazioni(email TEXT , id TEXT , ora TEXT,giorno TEXT,idCliente TEXT,primary key(email,id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Users");
        db.execSQL("drop Table if exists Clienti");
        db.execSQL("drop Table if exists Prenotazioni");


    }

    public boolean insertNewUsers(String email,String password){
        SQLiteDatabase mydb=this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);

        long result=((SQLiteDatabase) mydb).insert("Users", null,contentValues);

        if(result==1){
            emailUser=email;
            DatabaseDate.getInstance().setEmail(email);
            return true;
        }

        return false;

    }
    public boolean checkEmail(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Users where email =?",new String[] {email});

         return cursor.getCount() >0;


    }

    public boolean checkUsers(String email,String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Users where email =? and password=? ",new String[]{email,pass});

        if(cursor.getCount() >0)
        {
            emailUser=email;
            DatabaseDate.getInstance().setEmail(email);
            return true;
        }
        return false;

    }


    public boolean insertNewCliente(String ema,String id,String nome,String cognome,String eta){
        SQLiteDatabase mydb=this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put("email",ema);
        contentValues.put("id",id);
        contentValues.put("nome",nome);
        contentValues.put("cognome",cognome);
        contentValues.put("eta",eta);

        long result=((SQLiteDatabase) mydb).insert("Clienti", null,contentValues);

        if(result==1)
            return true;

        return false;


    }
    public boolean checkCliente(String email,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Clienti where email =? and id=? ",new String[]{email,id});

        if(cursor.getCount() >0)
        {
            return true;
        }
        return false;

    }

    public boolean deleteCliente(String email,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("delete  from Clienti where email =? and id=? ",new String[]{email,id});

        if(cursor.getCount() >0)
        {
            return true;
        }
        return false;

    }

    public boolean editCliente(String email,String id,String nome,String cognome,String eta){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("update  Clienti set nome=?,cognome=?,eta=?" +
                " where email =? and id=? ",new String[]{nome,cognome,eta,email,id});

        if(cursor.getCount() >0)
        {
            return true;
        }
        return false;

    }


    public boolean insertNewPrenotazione(String ema,String id, String data, String ora, String idCliente){
        SQLiteDatabase mydb=this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put("email",ema);
        contentValues.put("id",id);
        contentValues.put("ora",ora);
        contentValues.put("giorno",data);
        contentValues.put("idCliente",idCliente);

        long result=((SQLiteDatabase) mydb).insert("Prenotazioni", null,contentValues);

        return result==1;


    }

    public boolean checkPrenotazione(String email,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Prenotazioni where email =? and id=? ",new String[]{email,id});

        if(cursor.getCount() >0)
        {
            return true;
        }
        return false;

    }


    public boolean deletePrenotazione(String email,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("delete  from Prenotazioni where email =? and id=? ",new String[]{email,id});

        if(cursor.getCount() >0)
        {
            return true;
        }
        return false;

    }

    public ArrayList<Prenotazione> getPrenotazioni (String email){
        ArrayList<Prenotazione> prenotazioni =new ArrayList<Prenotazione>();

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Prenotazioni where email =?",new String[]{email});

        while(cursor.moveToNext()){
            Prenotazione prenotazione=new Prenotazione(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4));

             prenotazioni.add(prenotazione);
        }

        return prenotazioni;
    }

    public Prenotazione getPrenotazione (String email,String id){
        Prenotazione prenotazione=null;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Prenotazioni where email =? and id=?",new String[]{email,id});

        while(cursor.moveToNext()){
             prenotazione=new Prenotazione(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4));

        }
        if(prenotazione!=null)
            DatabaseDate.getInstance().setPrenotazione(prenotazione);

        return prenotazione;
    }





    public ArrayList<Cliente> getClienti (String email){
        ArrayList<Cliente> clienti =new ArrayList<Cliente>();

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Clienti where email =?",new String[]{email});

        while(cursor.moveToNext()){
            Cliente cliente=new Cliente(cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(1));

            clienti.add(cliente);
        }

        return clienti;
    }



    public Cliente getCliente (String email,String id){
        Cliente cliente=null;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Clienti where email=? and id=?",new String[]{email,id});

        while(cursor.moveToNext()){
             cliente=new Cliente(cursor.getString(4),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3));

        }
        if(cliente!=null)
            DatabaseDate.getInstance().setCliente(cliente);

        return cliente;
    }

}