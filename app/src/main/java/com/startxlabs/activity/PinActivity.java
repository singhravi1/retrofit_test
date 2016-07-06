package com.startxlabs.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arcfix.R;
import com.startxlabs.rest_api.APIClient;
import com.startxlabs.rest_api.data_model.PinResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by deep on 06/07/16.
 */
public class PinActivity extends BaseActivity {
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.txt_pin)
    TextView txtPin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        ButterKnife.bind(this);
      //TODO: put network check
        getData(true);
    }

    void getData(boolean showProgressBar) {
        //TODO:  check if internet is available
        if (showProgressBar) {
            progressBar.setVisibility(View.VISIBLE);
        }
        Call<PinResponse> apiCall = APIClient.getInstance().getRestAdapter().createUser("9818788321","+91");
        apiCall.enqueue(new Callback<PinResponse>() {
            @Override
            public void onResponse(Response<PinResponse> response, Retrofit retrofit) {

                progressBar.setVisibility(View.GONE);
                if (response != null && response.body() != null) {
                    txtPin.setText(response.body().getPin());
                } else {
                    Snackbar.make(progressBar,"No data to display", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                }

            }

            @Override
            public void onFailure(Throwable t) {

                Snackbar.make(progressBar, t.getMessage(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}
