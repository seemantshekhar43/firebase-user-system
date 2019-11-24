package comandydevo.wixsite.seemantshekhar43.fireusersystem;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    // variables
    Button button;
    EditText email, username, name;

    //database elements
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference userRef = rootRef.child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //conecting to the widgets
        button = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.userName);
        name = (EditText) findViewById(R.id.name);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the input
                String myName = name.getText().toString().trim();
                String myUserName = username.getText().toString().trim();
                String myEmail = email.getText().toString().trim();

                //creating a hashmap to store multiple data
                HashMap<String,String> userMap = new HashMap<String,String>();
                userMap.put("Name",myName);
                userMap.put("Username",myUserName);
                userMap.put("Email",myEmail);

                //exporting to database (push is used to generate new id everytime. we can use uid too.
                userRef.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Couldn't Register",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


    }
}
