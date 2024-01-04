// Jordan White, jordanw3@umbc.edu, This is an app that allows for a user
// to enter a restaurant bill, and get the bill and tip split evenly between the party size


package edu.umbc.is.example.split_the_tip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // Class variables
    private int partySize; // number of people in a group
    private double billAmount; // The bill to calculate the tip amount snd to be split

    private String qualityOfService;
    private DecimalFormat fmt;
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView appTitle = findViewById(R.id.tvAppTitle); // create elements, link to XML
        EditText inputBill = findViewById(R.id.etInputBill); // Want to keep the objects with
        EditText inputPeople = findViewById(R.id.etInputPeople);
        Spinner spinner = findViewById(R.id.spinnerGroup); // qos = Quality of Service
        TextView tvResults = findViewById(R.id.tvResults);
        Button btnGetTip = findViewById(R.id.btnGetSplitTip);


        btnGetTip.setOnClickListener(e -> { // handles events, want to calculate the tip and split amongst people
            billAmount = Integer.parseInt(String.valueOf(inputBill.getText())); // Retrieve inputs
            partySize = Integer.parseInt(String.valueOf(inputPeople.getText()));
            qualityOfService = spinner.getSelectedItem().toString();


            double tipPercentage; // use the nested if to for the correct tip amount; based on what's selected
            if (qualityOfService.equals("Excellent")) {
                tipPercentage = 0.22;
            } else if (qualityOfService.equals("Average")) {
                tipPercentage = 0.18;
            } else { // Poor service
                tipPercentage = 0.14;
            }

            // Calculate the tip
            double tipAmount = billAmount * tipPercentage;

            // Calculate total: Bill and tip in one price
            double totalAmount = billAmount + tipAmount;

            // Calculate individual share of the bill
            double individualShare = totalAmount / partySize;

            fmt = new DecimalFormat("###,###.00"); // format to include an acceptable # of sig figs for money
            String resultsText = "At a price of " + billAmount + " And a party of " + partySize
                    + " Everyone pays " + individualShare;
            tvResults.setText(resultsText);

        });
    }
}

