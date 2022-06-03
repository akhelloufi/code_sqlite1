package com.example.projet2022_212052022;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import databasePersonnel.DatabaseAide;

public class GererClientActivity extends AppCompatActivity {
EditText tcodedb,tnomdb,tsalairedb;
Button bajoutdb, bafficherdb ;
TextView tafficherinfodb;
DatabaseAide dbaide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_client);
        tcodedb=findViewById(R.id.tcodedb);
        tnomdb=findViewById(R.id.tnomdb);
        tsalairedb=findViewById(R.id.tsalairedb);
        bajoutdb=findViewById(R.id.bajoutdb);
        bafficherdb=findViewById(R.id.bafficherdb);
        tafficherinfodb=findViewById(R.id.tafficherinfodb);
        dbaide=new DatabaseAide(this.getApplicationContext());


       bajoutdb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               try {
                   Integer code = Integer.valueOf(tcodedb.getText().toString());
                   String nomclient = tnomdb.getText().toString();
                   Double salaire=Double.parseDouble(tsalairedb.getText().toString());
                   String sql="insert  into client values("+code.toString()
                           +" ,'"+nomclient+"',"+salaire.toString()+")";

                   dbaide.getWritableDatabase().execSQL(sql);

                   Toast.makeText(GererClientActivity.this.getApplicationContext(),
                           "client ajouter avec succe",Toast.LENGTH_SHORT).show();



               }
               catch(Exception ex){
                   Toast.makeText(GererClientActivity.this.getApplicationContext(),
                           ex.getMessage(),Toast.LENGTH_SHORT).show();

               }
           }
       });
        bafficherdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder s=new StringBuilder();//au lieu String s="" collect des info client

                String[] cols={"code","nom","salaire"};
                Cursor c=dbaide.getWritableDatabase().query("client",cols,null,null,null,null,null,null);
                //select code,nom ,salaire from client
                while(c.moveToNext()==true){
                   Integer code= c.getInt(0);
                   String nom= c.getString(1);
                   Double salaire=  c.getDouble(2);

                  s.append(code.toString()+" "+nom+" "+salaire.toString()+"\n");



                }
                c.close();
                tafficherinfodb.setText(s.toString());
            }
        });




    }
}