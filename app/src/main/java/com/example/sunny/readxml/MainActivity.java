package com.example.sunny.readxml;

import com.example.sunny.parsexml.*;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Process;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SignOnDataPass,AlertDataPass{
    private static String msg="";
    private HttpMessage tempUrl=new HttpMessage();
    private XMLFile tempFile=new XMLFile();

    public void onClick(View v){
        DialogFragment newFragment = new SignOnDialog();
        newFragment.show(getFragmentManager(),"credentials");

    }


    public void saveTempXMLFile()throws IOException{
        EditText urlMsg=(EditText)findViewById(R.id.xml_result_msg);
        Editable xml;
        xml=urlMsg.getText();

        tempFile.setIntDir(getFilesDir());
        tempFile.writeFile(xml);
    }


    public void onSaveAs(View v){
        Intent saveAs;
        final ProgressDialog saveProgress=ProgressDialog.show(MainActivity.this,"Saving","Please Wait...",true,false);
        try {
            Thread saveFile=new Thread(new Runnable(){
                public void run(){
                    try {
                        saveTempXMLFile();
                        saveProgress.dismiss();
                    }catch(IOException ex){
                        msg="Error\n"+ex.toString()+"\n"+tempFile.getIntDir()+tempFile.getFilename();//we pass this message to alert
                        DialogFragment alert = new NotifyDialog();
                        alert.show(getFragmentManager(),"Alert");
                    }
                }
            });
            saveFile.start();
            saveFile.join();


            saveAs=new Intent(this,SaveAsActivity.class);
            startActivity(saveAs);

        }catch(InterruptedException ex){
            msg=ex.getMessage();
            DialogFragment alert=new NotifyDialog();
            alert.show(getFragmentManager(),"Alert");
        }

    }

    public void passPassword(String pswd){
        tempUrl.setPswd(pswd);

    };

    public void passUsername(String user){
        tempUrl.setUsername(user);
    };


    public String getPassword(){
        return tempUrl.getPswd();
    }


    public String getUsername(){
        return tempUrl.getUsername();
    }

    public String getMessage(){
        return msg;
    }




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*On create Default values*/
        EditText xmlResult=(EditText) findViewById(R.id.xml_result_msg);
        xmlResult.setText("<!--XML Message Response Goes Here-->");
        Button saveAs= (Button)findViewById(R.id.save_as);
        saveAs.setEnabled(false);
        /*End Default*/


        final Button xmlMsgBttn=(Button)findViewById(R.id.url_get_button);

        xmlMsgBttn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                msg="";
                final ProgressDialog loadProgress=ProgressDialog.show(MainActivity.this,"Loading XML","Please Wait...",true,false);
                DialogFragment alert=new NotifyDialog();
                EditText urlTxt = (EditText) findViewById(R.id.url_text_box);
                EditText urlMsg = (EditText) findViewById(R.id.xml_result_msg);
                Button saveAs = (Button) findViewById(R.id.save_as);
                List<String> line;
                try {
                    tempUrl.setUrl(urlTxt.getText().toString());
                    tempUrl.requestMsg();
                    if (tempUrl.getErrMsg().length() == 0) {

                        switch (tempUrl.getStatusCode()) {
                            case 200:
                                line = tempUrl.getList();
                                urlMsg.setSelected(true);
                                urlMsg.setText("");
                                for (int i = 0; i < line.size(); i++) {
                                    if (i == 0) {
                                        urlMsg.setText(line.get(i));//writing first line
                                    } else {
                                        urlMsg.append("\n" + line.get(i));
                                    }
                                }
                                line.clear();
                                urlMsg.setMovementMethod(new ScrollingMovementMethod());
                                saveAs.setEnabled(true);
                                break;

                            case 401:
                                msg = "Unathorized! Please enter Credentials by \nClicking 'Credentials'";//we pass this message to alert
                                alert.show(getFragmentManager(), "Alert");
                                break;
                            default:
                                msg = "Unexpected error occured";
                                alert.show(getFragmentManager(),"Alert");
                        }

                    } else {
                        msg = "Error " + tempUrl.getErrMsg().toString();
                        alert.show(getFragmentManager(), "Alert");
                    }//we have some uncaught error in thread

                }/*PLEASE REMEMBER TO WRITE TO 'MSG' STRING FOR THE MESSAGE YOU WANT TO PASS TO THE ALERT DIALOG!!!!*/
                catch (MalformedURLException ex) {
                    msg = "No such url\n'" + urlTxt.getText().toString() + "'";
                    alert.show(getFragmentManager(), "Alert");
                } catch (SocketTimeoutException ex) {
                    msg = "Connection Timeout please check connection!";
                    alert.show(getFragmentManager(), "Alert");
                } catch (Exception ex) {
                    msg = "Unknown error:\n" + ex.toString();
                    alert.show(getFragmentManager(), "Alert");
                }

            }
        });



    }


    public void onStart(){
        super.onStart();



    }


}
