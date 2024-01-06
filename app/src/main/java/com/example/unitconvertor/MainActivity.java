package com.example.unitconvertor;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerOptions;
    private EditText editTextValue;
    private Button btnConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerOptions = findViewById(R.id.spinnerOptions);
        editTextValue = findViewById(R.id.editTextValue);
        btnConvert = findViewById(R.id.btnConvert);
        textViewResult = findViewById(R.id.textViewResult);

        // Populate the spinner with conversion options
        String[] conversionOptions = getResources().getStringArray(R.array.length_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, conversionOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions.setAdapter(adapter);

        // Set up the listener for item selection
        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle selection if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle empty selection if needed
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        // Implement the conversion logic based on the selected option
        double inputValue = Double.parseDouble(editTextValue.getText().toString());

        if (!Double.isNaN(inputValue)) {
            double conversionResult = 0.0;

            switch (spinnerOptions.getSelectedItemPosition()) {
                case 0:
                    conversionResult = convertCmToM(inputValue);
                    break;
                case 1:
                    conversionResult = convertMToCm(inputValue);
                    break;
                case 2:
                    conversionResult = convertKgToGram(inputValue);
                    break;
                case 3:
                    conversionResult = convertGramToKg(inputValue);
                    break;
                case 4:
                    conversionResult = convertLiterToMilliliter(inputValue);
                    break;
                case 5:
                    conversionResult = convertMilliliterToLiter(inputValue);
                    break;
                case 6:
                    conversionResult = convertCelsiusToFahrenheit(inputValue);
                    break;
                case 7:
                    conversionResult = convertFahrenheitToCelsius(inputValue);
                    break;
            }

            // Set the result text
            textViewResult.setText(String.format("%.2f %s", inputValue, spinnerOptions.getSelectedItem()) +
                    " = " + String.format("%.2f", conversionResult));
        } else {
            textViewResult.setText("Invalid input");
        }
    }

    private double convertCmToM(double cmValue) {
        return cmValue / 100.0;
    }

    private double convertMToCm(double mValue) {
        return mValue * 100.0;
    }

    private double convertKgToGram(double kgValue) {
        return kgValue * 1000.0;
    }

    private double convertGramToKg(double gramValue) {
        return gramValue / 1000.0;
    }

    private double convertLiterToMilliliter(double literValue) {
        return literValue * 1000.0;
    }

    private double convertMilliliterToLiter(double milliliterValue) {
        return milliliterValue / 1000.0;
    }

    private double convertCelsiusToFahrenheit(double celsiusValue) {
        return celsiusValue * 9 / 5 + 32;
    }

    private double convertFahrenheitToCelsius(double fahrenheitValue) {
        return (fahrenheitValue - 32) * 5 / 9;
    }
}
