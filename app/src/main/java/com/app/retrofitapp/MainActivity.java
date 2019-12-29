package com.app.retrofitapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.retrofitapp.Pojo.BioData;
import com.app.retrofitapp.Retrofit.ApiService;
import com.app.retrofitapp.Retrofit.RetrofitClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText name,age;
    Button submit,go;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            //  Log.w(TAG, "getInstanceId failed", task.getException());
                            //  return;
                            Toast.makeText(getApplicationContext(),"Not generated",Toast.LENGTH_SHORT).show();

                        }

                        // Get new Instance ID token1
                        String token = task.getResult().getToken();
                          Log.i("My Token", "Current token=" + token);

                    }
                });

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        submit = findViewById(R.id.submit);
        go = findViewById(R.id.go);
        progressBar = findViewById(R.id.progressBar);

        go.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,Users.class);
                startActivity(i);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

              progressBar.setVisibility(View.VISIBLE);

              String str1 = name.getText().toString();
              String str2 = age.getText().toString();

              if(str1.equals("")){

                  progressBar.setVisibility(View.INVISIBLE);
                  Toast.makeText(getApplicationContext(),"Enter name",Toast.LENGTH_LONG).show();
              }
              else if(str2.equals("")){

                  progressBar.setVisibility(View.INVISIBLE);
                  Toast.makeText(getApplicationContext(),"Enter age",Toast.LENGTH_SHORT).show();
              }
              else{

                  postData(str1,str2);
              }

            }

        });
    }

    private void postData(String str1,String str2){


                  Retrofit retrofit  = RetrofitClient.getInstance();
                  ApiService mapiService = retrofit.create(ApiService.class);

                  mapiService.saveData(str1,str2)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .unsubscribeOn(Schedulers.io())
                          .subscribe(new Observer<String>() {

                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(String s) {

                         name.setText("");
                         age.setText("");

                         progressBar.setVisibility(View.INVISIBLE);
                         Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                     }

                     @Override
                     public void onError(Throwable e) {

                         progressBar.setVisibility(View.INVISIBLE);
                         Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                     }

                     @Override
                     public void onComplete() {

                     }
                 });
    }
}
