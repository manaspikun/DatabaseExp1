package com.techpalle.databaseexp1;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragOne extends Fragment {
    EditText et1,et2;
    TextView tv;
    Button b1,b2;
    MyDatabase myDatabase;


    public FragOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //open database here
        myDatabase=new MyDatabase(getActivity());
        myDatabase.open();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_one, container, false);
        et1= (EditText) v.findViewById(R.id.edittext1);
        et2= (EditText) v.findViewById(R.id.edittext2);
        tv= (TextView) v.findViewById(R.id.textview1);
        b1= (Button) v.findViewById(R.id.button1);
        b2= (Button) v.findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we will insert data into table(student)
                String name=et1.getText().toString();
                String course=et2.getText().toString();
                myDatabase.insertStudent(name,course);//request for db file for insertion
                et1.setText("");
                et2.setText("");
                et1.requestFocus();
                Toast.makeText(getActivity(), "inserted a row", Toast.LENGTH_SHORT).show();


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we will read data from table(student)
                Cursor c=myDatabase.queryStudent();
                if (c!=null){
                    StringBuilder sb=new StringBuilder();
                    //that means there
                    while (c.moveToNext()){
                        int sno=c.getInt(0);
                        String  sname=c.getString(1);
                        String scourse=c.getString(2);
                        sb.append(sno+":"+sname+":"+scourse+"\n");
                    }
                    //let us apply on textview
                    tv.setText(sb.toString());
                }
            }
        });

        return v;
    }

}
