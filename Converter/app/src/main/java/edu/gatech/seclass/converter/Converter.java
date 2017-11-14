package edu.gatech.seclass.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.Random;

public class Converter extends AppCompatActivity {
    private Spinner spinner1, spinner2;
    private EditText editText, editText2;
    /**unit1 and unit2 represent their indexes in the array of
      *["Stones", "Pounds", "Ounces", "Grains", "Metric tons", "Kilograms", "Grams","Milligrams"]
      *convToGram converts all units into grams for easy comparison.
      */
    private int unit1 = 0, unit2 = 0;
    private static double convToGram[] = {6350.29, 453.529, 28.3195, 0.0647989, 1000000, 1000, 1, 0.001};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        addSpinners();
        addButtons();
    }

    public void addSpinners(){
        String[] listOfUnits = {"Stones", "Pounds", "Ounces", "Grains", "Metric tons", "Kilograms", "Grams","Milligrams"};
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new UnitOfConvertFrom());
        spinner2.setOnItemSelectedListener(new UnitOfConvertTo());
    }

    public void addButtons(){
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        try {
            button1.setOnClickListener(new Convert());
            button2.setOnClickListener(new RND_Convert());
            button3.setOnClickListener(new Reset());
        }catch(Exception e) {}
    }


    public class UnitOfConvertFrom implements OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            unit1 = pos;
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {}

    }

    public class UnitOfConvertTo implements OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            unit2 = pos;
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {}

    }
    public class Convert implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            try {
                double value = Double.parseDouble(editText.getText().toString());
                double result = value * (convToGram[unit1] / convToGram[unit2]);
                editText2.setText(String.valueOf(result));
            }catch(Exception e){
                editText2.setText("");
            }
        }
    }

    public class RND_Convert implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Random rn = new Random();
            int index = rn.nextInt(8);
            spinner2.setSelection(index);
            unit2 = index;
            try {
                double value = Double.parseDouble(editText.getText().toString());
                double result = value * (convToGram[unit1] / convToGram[unit2]);
                editText2.setText(String.valueOf(result));
            }catch(Exception e) {
                editText2.setText("");
            }
        }
    }

    public class Reset implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            spinner1.setSelection(0);
            spinner2.setSelection(0);
            unit1 = 0;
            unit2 = 0;
            editText.setText("");
            editText2.setText("");
        }
    }
}
