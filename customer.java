package com.example.vendorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customer extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    //DatabaseReference reff, reff1, reff2, reff3, reff4, reff5, reff6, reff7;
    Button btn;
    String ss, phoneno;
    EditText lnd;
    /*Vegetables vegie;
    Fruits fru;
    userinfo info;
    Leafy_Vegetables leaf;
    Coconut_Water coco;
    Flower flo;
    Chaats chat;
    Dosa dosa;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        lnd = (EditText)findViewById(R.id.landmark2);
        btn = (Button)findViewById(R.id.button2);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.businesstypes, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        btn.setOnClickListener(this);

       /* info = new userinfo();
        reff = FirebaseDatabase.getInstance().getReference().child("userinfo");
        vegie = new Vegetables();
        reff1 = FirebaseDatabase.getInstance().getReference().child("Vegetables");
        fru = new Fruits();
        reff2 = FirebaseDatabase.getInstance().getReference().child("Fruits");
        leaf = new Leafy_Vegetables();
        reff3 = FirebaseDatabase.getInstance().getReference().child("Leafy_Vegetables");
        flo = new Flower();
        reff4 = FirebaseDatabase.getInstance().getReference().child("Flower");
        chat = new Chaats();
        reff5 = FirebaseDatabase.getInstance().getReference().child("Chaats");
        dosa = new Dosa();
        reff6 = FirebaseDatabase.getInstance().getReference().child("Dosa");
        coco = new Coconut_Water();
        reff7 = FirebaseDatabase.getInstance().getReference().child("Coconut_Water");*/

        Bundle bundle=getIntent().getBundleExtra("data");
        phoneno = bundle.getString("phone");

    }



    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Object selected = parent.getItemAtPosition(pos);
        ss = selected.toString();
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        TextView tv = (TextView) findViewById(R.id.subtype);
        Toast.makeText(getBaseContext(), ss, Toast.LENGTH_SHORT).show();
        if(ss.equals("Groceries"))
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.grocerysubtypes, android.R.layout.simple_dropdown_item_1line);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner2.setAdapter(adapter);
            spinner2.setOnItemSelectedListener(this);
        }
        else if(ss.equals("Food Truck"))
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.foodsubtypes, android.R.layout.simple_dropdown_item_1line);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner2.setAdapter(adapter);
            spinner2.setOnItemSelectedListener(this);
        }
        else if(ss.equals("Flowers"))
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.flowersubtypes, android.R.layout.simple_dropdown_item_1line);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner2.setAdapter(adapter);
            spinner2.setOnItemSelectedListener(this);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {


        /*reff = FirebaseDatabase.getInstance().getReference().child("userinfo").child(phoneno);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pin = snapshot.child("pincode").getValue().toString();
                pincode = pin;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        /*switch(ss){
            case "Vegetables":
                vegie.setPhone(phoneno);
                reff1.push().setValue(vegie.phone);

                break;
            case "Fruits":
                fru.setPhone(phoneno);
                reff2.push().setValue(fru.phone);
                break;
            case "Leafy_Vegetables":
                leaf.setPhone(phoneno);
                reff3.push().setValue(leaf.phone);
                break;
            case "Flower":
                flo.setPhone(phoneno);
                reff4.push().setValue(flo.phone);
                break;
            case "Chaats":
                chat.setPhone(phoneno);
                reff5.push().setValue(chat.phone);
                break;
            case "Dosa":
                dosa.setPhone(phoneno);
                reff6.push().setValue(dosa.phone);
                break;
            case "Coconut_Water":
                coco.setPhone(phoneno);
                reff7.push().setValue(coco.phone);
                break;
            default:
                Toast.makeText(getBaseContext(), ss, Toast.LENGTH_SHORT).show();
                break;
        }*/
        Bundle bundle = new Bundle();
        bundle.putString("ss",ss);
        bundle.putString("phone",phoneno);
        Intent i = new Intent(this, AddItems.class);
        i.putExtra("data", bundle);
        startActivity(i);
    }
}