package com.supralabs.cahier.Messaging;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.supralabs.cahier.R;

import java.util.ArrayList;
import java.util.List;

//#####################################################
//################### MENSAJERIA ######################
//#####################################################

public class Chat extends AppCompatActivity implements KeyListener {

    private RecyclerView mDialogTextView;
    private List<Message> messages;
    private MessagesAdapter adapter;
    private Button buttonSendMessage;
    private EditText editTextEditMessage;



    String[][] chatBot = {
            //saludos habituales
            {"hi", "hello", "hola", "ola", "howdy"},
            {"hi", "hello", "hey"},
            //preguntas saludos
            {"how are you", "how r you", "how r u", "how are u","como estas"},
            {"goood", "doing well"},
            //si
            {"yes"},
            {"no", "NO", "NO!!!!!"},
            //default
            {"shut up!", "you're bad", "noob", "stop talking", "(Chat bot no esta disponible... jajaja"}
    };

    public static String mensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messages = new ArrayList<>();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolBars);
        mDialogTextView = (RecyclerView) findViewById(R.id.dialogMessages);

        buttonSendMessage = (Button)findViewById(R.id.sendButton);
        editTextEditMessage = (EditText) findViewById(R.id.messageEditText);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        mDialogTextView.setLayoutManager(linearLayoutManager);

        adapter = new MessagesAdapter(messages,this);
        mDialogTextView.setAdapter(adapter);


        buttonSendMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mensaje = editTextEditMessage.getText().toString();
                CreateMessage(mensaje);
                CreateChatin();

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        setScrollbarChat();
    }
    public void CreateMessage(String string){
        Message auxiliarMessage = new Message();
        auxiliarMessage.setId("0");
        auxiliarMessage.setMensaje(string);
        auxiliarMessage.setTipoDeMensaje(1);
        auxiliarMessage.setHoraDelMensaje(string);
        messages.add(auxiliarMessage);
        adapter.notifyDataSetChanged();
        editTextEditMessage.setText("");
        setScrollbarChat();
    }

    public void CreateChatin(){
        Message auxiliarChatin = new Message();

        mensaje.trim();
        while (
                mensaje.charAt(mensaje.length() - 1) == '!' ||
                mensaje.charAt(mensaje.length() - 1) == '.' ||
                mensaje.charAt(mensaje.length() - 1) == '?'
        ) {
                mensaje = mensaje.substring(0, mensaje.length() - 1);
        }
        mensaje.trim();
        byte response = 0;


        /*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
        //-----check for matches----

        int j = 0;
        while (response == 0) {
            if (inArray(mensaje.toLowerCase(), chatBot[j * 2])) {
                response = 2;
                int r = (int) Math.floor(Math.random() * chatBot[(j * 2) + 1].length);

                //---------mensaje--------
                auxiliarChatin.setId("0");
                auxiliarChatin.setMensaje(chatBot[(j * 2) + 1][r]);
                auxiliarChatin.setTipoDeMensaje(2);
                messages.add(auxiliarChatin);
                adapter.notifyDataSetChanged();
                editTextEditMessage.setText("");
                setScrollbarChat();
                //------------------------
            }
            j++;
            if (j * 2 == chatBot.length - 1 && response == 0) {
                response = 1;
            }
        }
        //-----default--------------
        if (response == 1) {
            int r = (int) Math.floor(Math.random() * chatBot[chatBot.length - 1].length);

            //---------mensaje--------
            auxiliarChatin.setId("0");
            auxiliarChatin.setMensaje(chatBot[chatBot.length - 1][r]);
            auxiliarChatin.setTipoDeMensaje(2);
            messages.add(auxiliarChatin);
            adapter.notifyDataSetChanged();
            editTextEditMessage.setText("");
            setScrollbarChat();
            //------------------------
        }
    }

    public  boolean inArray(String in, String[] str){
        boolean match = false;
        for (int i=0; i<str.length;i++){
            if (str[i].equals(in)){
                match=true;
            }
        }
        return match;
    }

    public void setScrollbarChat(){
        mDialogTextView.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    public int getInputType() {
        return 0;
    }

    @Override
    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void clearMetaKeyState(View view, Editable editable, int i) {

    }
}
