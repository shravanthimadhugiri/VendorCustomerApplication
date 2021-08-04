package com.example.vendorproject;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItems extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    DatabaseReference reff;
    Shops shop;
    String ss, phone, land, types, goods, cost;
    Bundle bundle;
    Spinner spinum;
    EditText goods1, cost1;
    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        bundle=getIntent().getBundleExtra("data");
        goods1 = (EditText)findViewById(R.id.nameofitem);
        cost1 = (EditText)findViewById(R.id.priceofitem);
        //land =bundle.getString("land");
        phone = bundle.getString("phone");
        //types = bundle.getString("type");
        b1 = (Button)findViewById(R.id.addtoshop);
        b2 = (Button)findViewById(R.id.button3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        spinum = (Spinner) findViewById(R.id.spin_num);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinum.setAdapter(adapter);
        spinum.setOnItemSelectedListener(this);
        shop = new Shops();
        reff = FirebaseDatabase.getInstance().getReference().child("Shops");

    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        Object selected = parent.getItemAtPosition(pos);
        ss = selected.toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.equals(b1)){
            cost = cost1.getText().toString();
            goods = goods1.getText().toString();
            //shop.setType(types);
            //shop.setLandmark(land);
            shop.setPrice(cost);
            shop.setItem(goods);
            shop.setPhone(Long.parseLong(phone));
            reff.push().setValue(shop);
            Toast.makeText(getBaseContext(), "Successfully added!", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putString("phone", phone);
            Intent i = new Intent(this, AddItems.class);
            i.putExtra("data", bundle);
            startActivity(i);
        }
        else{

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        finish();

    }


}