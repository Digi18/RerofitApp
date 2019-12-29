package com.app.retrofitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.retrofitapp.Adapters.UserAdapter;
import com.app.retrofitapp.Pojo.BioData;
import com.app.retrofitapp.Retrofit.ApiService;
import com.app.retrofitapp.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Users extends AppCompatActivity {

    RecyclerView recycle;
    UserAdapter adapter;
  //  List<BioData> list;
    ProgressBar prog;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        prog = findViewById(R.id.prog);

        recycle = findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));

       // list  = new ArrayList<>();

        compositeDisposable = new CompositeDisposable();

        fetchData();
    }

        private void fetchData(){

        Retrofit retrofit  = RetrofitClient.getInstance();
        ApiService myApi = retrofit.create(ApiService.class);

       /* Disposable disposable = myApi.getData().subscribeOn(Schedulers.io())
                                               .observeOn(AndroidSchedulers.mainThread())
                                               .subscribe(new Consumer<List<BioData>>() {
                                                   @Override
                                                   public void accept(List<BioData> bioata) throws Exception {

                                                       prog.setVisibility(View.INVISIBLE);

                                                          if(bioata.size() > 9){
                                                              Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                                                          }
                                                       adapter = new UserAdapter(bioata,getApplicationContext());
                                                       recycle.setAdapter(adapter);
                                                   }
                                               });  */

                               myApi.getData()
                               .toObservable()
                               .observeOn(AndroidSchedulers.mainThread())
                               .subscribeOn(Schedulers.io())
                               .subscribe(this::handleResponse,this::handleError);
                             /*  .subscribe(new Observer<List<BioData>>() {

                                   @Override
                                   public void onSubscribe(Disposable d) {

                                   }

                                   @Override
                                   public void onNext(List<BioData> bioData) {

                                       prog.setVisibility(View.GONE);

                                       if(bioData.size() > 0){

                                           adapter = new UserAdapter(bioData,getApplicationContext());
                                           recycle.setAdapter(adapter);
                                       }
                                   }

                                   @Override
                                   public void onError(Throwable e) {

                                       Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void onComplete() {

                                   }
                               });  */

                               //  compositeDisposable.add(disposable);

    }

    @Override
    protected void onStop() {
        super.onStop();

       compositeDisposable.clear();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}
