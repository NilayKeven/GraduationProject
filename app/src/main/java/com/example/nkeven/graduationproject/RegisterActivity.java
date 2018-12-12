package com.example.nkeven.graduationproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText userName;
    EditText userSurname;
    EditText emailAddress;
    EditText phoneNumber;
    Button registerButton;

    DatabaseReference databaseUsersReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseUsersReference = FirebaseDatabase.getInstance().getReference("users");

        userName = findViewById(R.id.edit_username);
        userSurname = findViewById(R.id.edit_user_surname);
        emailAddress = findViewById(R.id.edit_email);
        phoneNumber = findViewById(R.id.edit_phone_number);
        registerButton = findViewById(R.id.button_register);

        registerButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().toString(), "Register button on click");
                addUserToDatabase();
            }
        });

    }

    public boolean validationUserName(String userName) {
        // Control the user name or surname is valid.
        return (!userName.equals("") && userName.length() > 1 && userName.matches("^[a-zA-Z]*$"));
    }

    public String addRegionCodePhoneNumber(String phoneNumber) {
        // Region code for Turkey
        final String regionCode = "+90";
        return (phoneNumber + regionCode);
    }

    public boolean validationPhoneNumber(String phoneNumber) {
        phoneNumber = addRegionCodePhoneNumber(phoneNumber);
        return phoneNumber.length() == 13;
    }

    public boolean validationEmail(String email) {
        return (email.contains("@") || (email.contains(".")));
    }

    public void addUserToDatabase() {
        String name = userName.getText().toString();
        String surname = userSurname.getText().toString();
        String email = emailAddress.getText().toString();
        String phone = phoneNumber.getText().toString();

        if (!validationUserName(name)) {
            showAlert("Uyarı", "Geçersiz isim girdiniz.", "TAMAM", userName);
        } else if (!validationUserName(surname)) {
            showAlert("Uyarı", "Geçersiz soyisim girdiniz.", "TAMAM", userSurname);
        } else if (!validationEmail(email)) {
            showAlert("Uyarı", "Geçersiz e-mail adresi girdiniz.", "TAMAM", emailAddress);
        } else if (!validationPhoneNumber(phone)) {
            showAlert("Uyarı", "Geçersiz telefon numarası girdiniz.", "TAMAM", phoneNumber);
        } else {
            Person person = new Person(name, surname, email, phone);
            String keyID = databaseUsersReference.push().getKey();
            databaseUsersReference.child(keyID).setValue(person);
        }
    }

    public void showAlert(String title, String message, String buttonText, final EditText editText) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editText.setText("");
            }
        });
        alert.show();
    }
}
