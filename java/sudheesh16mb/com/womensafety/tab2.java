package sudheesh16mb.com.womensafety;



import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tab2 extends Fragment
{
    EditText editText;
    Button button;



    public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle bundle) {
        View view = null;
        view = inflater.inflate(R.layout.activity_tab2, group, false);
/*
        editText= (EditText) view.findViewById(R.id.vechileno);
        button= (Button) view.findViewById(R.id.send_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationManager locationManager= (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria=new Criteria();
                String provider=locationManager.getBestProvider(criteria, false);
                Location location=locationManager.getLastKnownLocation(provider);
                double lat=  location.getLatitude();
                double lon= location.getLongitude();

                String lat_st= String.valueOf(Double.parseDouble(String.valueOf(lat)));
                String lon_st= String.valueOf(Double.parseDouble(String.valueOf(lon)));
                Toast.makeText(getActivity().getApplicationContext(),"lat:"+lat_st+"Lon"+lon_st+"",Toast.LENGTH_LONG).show();


                String vnum=editText.getText().toString();
                //String type="Login";
                ///BackgroundWorker bw=new BackgroundWorker(this);
                //bw.execute(vnum,lat_st,lon_st);
                inseert(lat_st,lon_st,vnum);



            }
        });

        return view;


    private void inseert(String lat_st, String lon_st, String vnum) {

        class SendPostReqAsyncTask extends AsyncTask<String ,Void , String >{

            @Override
            protected String doInBackground(String... params) {
                String lat1=params[0];
                String lon1=params[1];
                String vname1=params[2];

                List<NameValuePair> nameValuePairList =new ArrayList<NameValuePair>();
                nameValuePairList.add(new BasicNameValuePair("lat1",lat1));
                nameValuePairsL.add(new BasicNameValuePair("name",lon1 ));
                nameValuePairs.add(new BasicNameValuePair("address", vname1));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://simplifiedcoding.16mb.com/insert-db.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText("Inserted");
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, add);
            }
        }*/

   return view;} }
