
package com.example.momen.justjava;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void displayOrderSummary(String message) {
        TextView orderSummary = findViewById(R.id.order_summary_text_view);
        orderSummary.setText(message);
    }

    private void displayQuantity(int number) {
        TextView quantity = findViewById(R.id.quantity_text_view);
        quantity.setText("" + number);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity < 1) {
            Toast.makeText(this, R.string.javalessthanone, Toast.LENGTH_SHORT).show();
            quantity = 1;
        }
        displayQuantity(quantity);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity > 10) {
            Toast.makeText(this, R.string.javamoreThanOne, Toast.LENGTH_SHORT).show();
            quantity = 10;
        }
        displayQuantity(quantity);
    }

    public int calculatePrice(boolean addWhippedCream, boolean AddChocolate) {
        int basePrice = 5; //basePrice with not toppings
        //adding Whipped cream should increase the base price by 1 dollar so the price will be 6 for one cup
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        //adding chocolate will increase the price by 2 dollars so the price will be 7 for one cup
        if (AddChocolate) {
            basePrice = basePrice + 2;
        }
        //finish method how many cups ordered * the base price after added the toppings if oly selected bu the user
        return quantity * basePrice;
    }

    public String createOrderSummary() {

        //Check for the Whipped cream checkbox
        CheckBox checkBox = findViewById(R.id.whippped_cream_checkbox);
        boolean addWhippedCream = checkBox.isChecked();

        //Check for the adding chocolate or not checkbox
        CheckBox chocolateCheckbox = findViewById(R.id.chocolate_checkbox);
        boolean AddChocolate = chocolateCheckbox.isChecked();

        //the below method to get the use input from the edit filed
        EditText getUserName = findViewById(R.id.EditTextId);
        String whatIsYourName = getUserName.getText().toString();

        // price variable contain the quantity value and multiply it by 5

        int price = calculatePrice(addWhippedCream, AddChocolate);
        String report = getString(R.string.javaName) + whatIsYourName
                + getString(R.string.javawippedCream) + addWhippedCream
                + getString(R.string.javaChocolate) + AddChocolate
                + getString(R.string.javaQuantity) + quantity
                + getString(R.string.javaTotal) + price
                + getString(R.string.javaThanks)
                + getString(R.string.javaMessage1)
                + getString(R.string.javaMessage2);
        String subject = getString(R.string.javaMailSubject);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, report);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        return report;
    }
}

