package com.gmail.dp.denzay.nytviewer.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.dp.denzay.nytviewer.MainActivity;
import com.gmail.dp.denzay.nytviewer.R;
import com.gmail.dp.denzay.nytviewer.adapters.NYTAPI;
import com.gmail.dp.denzay.nytviewer.adapters.NYTRequestAdapter;
import com.gmail.dp.denzay.nytviewer.models.AbstractResponse;
import com.gmail.dp.denzay.nytviewer.models.AbstractResponseResult;
import com.gmail.dp.denzay.nytviewer.models.EmailedResponse;
import com.gmail.dp.denzay.nytviewer.models.EmailedResponseResult;
import com.gmail.dp.denzay.nytviewer.models.SharedResponse;
import com.gmail.dp.denzay.nytviewer.models.SharedResponseResult;
import com.gmail.dp.denzay.nytviewer.models.ViewedResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String API_KEY = "Ny86Hu9i2xN81axhZNlgms8TGAydLikN";
    private static final int ARTICLES_PERIOD_DAYS = 30;

    private TextView textView;

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView) rootView.findViewById(R.id.section_label);
        int fragmentSectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

        textView.setText(getString(R.string.section_format, fragmentSectionNumber));

        Button btn_ExecuteRequest = (Button) rootView.findViewById(R.id.btn_ExecuteRequest);

        btn_ExecuteRequest.setOnClickListener((View v) -> {
           NYTAPI nytAPI = NYTRequestAdapter.getInstance().getNYTAPI();
           switch (fragmentSectionNumber) {
               case 1:
                   nytAPI.getEmailedArticles(ARTICLES_PERIOD_DAYS, API_KEY).enqueue(emailedCallback);
                   break;
               case 2:
                   nytAPI.getSharedArticles(ARTICLES_PERIOD_DAYS, API_KEY).enqueue(sharedCallback);
                   break;
               case 3:
                   nytAPI.getViewedArticles(ARTICLES_PERIOD_DAYS, API_KEY).enqueue(viewedCallback);
                   break;
           }
        });
        return rootView;
    }

    private Callback<EmailedResponse> emailedCallback = new Callback<EmailedResponse>() {
        @Override
        public void onResponse(Call<EmailedResponse> call, Response<EmailedResponse> response) {
            AbstractResponse result = response.body();
            PlaceholderFragment.this.processSuccessResponse(result);
        }

        @Override
        public void onFailure(Call<EmailedResponse> call, Throwable t) {
            PlaceholderFragment.this.processFailResponse(t);
        }
    };

    private Callback<SharedResponse> sharedCallback = new Callback<SharedResponse>() {
        @Override
        public void onResponse(Call<SharedResponse> call, Response<SharedResponse> response) {
            AbstractResponse result = response.body();
            PlaceholderFragment.this.processSuccessResponse(result);
        }

        @Override
        public void onFailure(Call<SharedResponse> call, Throwable t) {
            PlaceholderFragment.this.processFailResponse(t);
        }
    };

    private Callback<ViewedResponse> viewedCallback = new Callback<ViewedResponse>() {
        @Override
        public void onResponse(Call<ViewedResponse> call, Response<ViewedResponse> response) {
            AbstractResponse result = response.body();
            PlaceholderFragment.this.processSuccessResponse(result);
        }

        @Override
        public void onFailure(Call<ViewedResponse> call, Throwable t) {
            PlaceholderFragment.this.processFailResponse(t);
        }
    };

    private void processSuccessResponse(AbstractResponse response) {
        textView.setText("" + response.getArticlesCount() + "\n");
        List<? extends AbstractResponseResult> listResults;
        if (response instanceof EmailedResponse) {
            listResults = ((EmailedResponse) response).getResults();
        } else if (response instanceof SharedResponse) {
            listResults = ((SharedResponse) response).getResults();
        } else if (response instanceof ViewedResponse) {
            listResults = ((ViewedResponse) response).getResults();
        } else return;
        if(listResults.size() > 0) {
            textView.append(listResults.get(0).getTitle());
        }
    }

    private void processFailResponse(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
    }
}
