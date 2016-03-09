package ctec.smileactivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.telephony.SmsManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SmileActivity extends AppCompatActivity
{
    private TextView smileMessageTextView;
    private Button sendTextButton;
    private Button addNumberButton;
    private EditText addNumbEditText;
    private Button randomMessageButton;
    private ArrayList<String> smileNumbers;
    private ArrayList<String> smileMessage;
    private EditText addMessageEditText;
    private Button addMessageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        smileNumbers = new ArrayList<String>();
        buildSmileNumbers();
        smileMessage = new ArrayList<String>();
        buildSmileMessage();
        smileMessageTextView = (TextView) findViewById(R.id.smileMessageTextView);
        sendTextButton = (Button) findViewById(R.id.sendTextButton);
        addNumberButton = (Button) findViewById(R.id.addNumberButton);
        addNumbEditText = (EditText) findViewById(R.id.addNumbEditText);
        randomMessageButton = (Button) findViewById(R.id.randomMessageButton);
        addMessageEditText = (EditText) findViewById(R.id.addMessageEditText);
        addMessageButton = (Button) findViewById(R.id.addMessageButton);

        setupListeners();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void buildSmileNumbers()
    {
        smileNumbers.add("3852161020");
        smileNumbers.add("8018339810");
        smileNumbers.add("8018356366");
        smileNumbers.add("8014555376");
        smileNumbers.add("8012050112");
        smileNumbers.add("8016948781");
        smileNumbers.add("8019317120");
    }
    private void buildSmileMessage()
    {
        smileMessage.add("You look great today!");
        smileMessage.add("You can do anything you set your mind to");
        smileMessage.add("Have a great day");
        smileMessage.add("Don't let your dreams be dreams");
        smileMessage.add("You're worth it");
        smileMessage.add("You're alright");
        smileMessage.add("You are going to do well today ^>^)");
    }

    private String randomMessage()
    {
        String randomSmile = "";
        int random = (int) (Math.random() * smileMessage.size());
        randomSmile = smileMessage.get(random);
        return randomSmile;
    }

    private void sendSMS(String messageAdress, String messageContent)
    {
        SmsManager mySMSManager = SmsManager.getDefault();
        mySMSManager.sendTextMessage(messageAdress, null, messageContent, null, null);
    }

    private void setupListeners()
    {
        sendTextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
        public void onClick(View currentView)
            {
                try
                {
                    for(String object : smileNumbers)
                    {
                        String contact = object.toString();
                        String message = smileMessageTextView.getText().toString();
                        sendSMS(contact, message);
                        Toast.makeText(currentView.getContext(),"message was sent", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(currentView.getContext(), "message was sent", Toast.LENGTH_SHORT).show();
                }
                catch(Exception currentExeption)
                {
                    Toast.makeText(currentView.getContext(), "message was not sent", Toast.LENGTH_LONG).show();
                    Toast.makeText(currentView.getContext(), currentExeption.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        randomMessageButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View currentView)
            {
                smileMessageTextView.setText(randomMessage());
            }
        });
        addNumberButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View currentView)
            {
                smileNumbers.add(addNumbEditText.getText().toString());
            }
        });
        addMessageButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View currentView)
            {
                smileMessage.add(addMessageEditText.getText().toString());
            }
        });
    }
}
