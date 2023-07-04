package sg.edu.rp.c364.id22014057.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResult;
    ListView lv;
    EditText etDescription, etDate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResult = findViewById(R.id.tvResults);
        lv =findViewById(R.id.listView);
        etDescription = findViewById(R.id.etDescription);
        etDate= findViewById(R.id.etDate);



        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etDescription.getText().toString(), etDate.getText().toString());

            }
        });


        btnGetTasks.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // Create the DBHelper object, passing in the
                    // activity's Context
                    DBHelper db = new DBHelper(MainActivity.this);

                    ArrayList<String> data = db.getTaskContent();
                    ArrayList<Task> data1 = db.getTasks();
                    db.close();


                    String txt = "";
                    for (int i = 0; i < data.size(); i++) {
                        Log.d("Database Content", i + ". " + data.get(i));
                        txt += i + ". " + data.get(i) + "\n";
                    }
                    tvResult.setText(txt);

                    ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data1);
                    lv.setAdapter(adapter);
                }
            });
        }
    }

