package sudheesh16mb.com.womensafety;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by KUTTAN on 16-08-2016.
 */
public class BackgroundWorker extends AsyncTask<String,String,String> {

    AlertDialog alt;

    Context cnt;
    public BackgroundWorker(Context ctx) {
        // TODO Auto-generated constructor stub
        cnt=ctx;
    }

    public BackgroundWorker(Object o) {

    }

    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

        String lurl="http://sudheesh95.16mb.com/get-data1.php";
            try
            {
                String user_name=params[0];
                String password=params[1];
                String longi=params[2];
                URL url=new URL(lurl);
                HttpURLConnection htcon=(HttpURLConnection) url.openConnection();
                htcon.setRequestMethod("POST");
                htcon.setDoInput(true);
                htcon.setDoOutput(true);
                OutputStream ostream=htcon.getOutputStream();
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(ostream, "UTF-8"));
                String post_data=URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password, "UTF-8")+"&"
                        +URLEncoder.encode("longi","UTF-8")+"="+ URLEncoder.encode(longi, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                InputStream istream=htcon.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(istream,"iso-8859-1"));
                String results="";
                String line="";
                while((line=br.readLine())!=null)
                {
                    results+=line;
                }
                br.close();
                istream.close();
                htcon.disconnect();
                return results;
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }



        return null;
    }


    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        alt.setMessage(result);
        alt.show();
    }


   protected void onPreExecute() {
        alt=new AlertDialog.Builder(cnt).create();
        alt.setTitle("Login Status");
    }


  protected void onProgressUpdate(String... text) {


    }



}
