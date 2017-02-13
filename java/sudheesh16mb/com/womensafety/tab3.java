package sudheesh16mb.com.womensafety;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class tab3 extends Fragment
{
    Button b,b1;
    public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle bundle){

        View view=null;
        view= inflater.inflate(R.layout.activity_tab3,group,false);

        b= (Button) view.findViewById(R.id.button);
        b1= (Button) view.findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"alarm",Toast.LENGTH_SHORT).show();


                AudioManager am=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                int vl=70;
                am.adjustVolume(1, 0);
                //am.setStreamVolume(AudioManager.STREAM_ALARM, vl, 0);
                Uri noti =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                Ringtone r= RingtoneManager.getRingtone(getActivity(), noti);
                r.play();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String no="8754342326";
                String msg="Help Me :11.245654,77.24243533 Auto Generated";
                Intent i=new Intent(getActivity().getApplicationContext(),tab3.class);
                PendingIntent pi=PendingIntent.getActivity(getActivity().getApplicationContext(), 0,i, 0);
                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage(no, null, msg, pi, null);
                Toast.makeText(getActivity(), "Message send to"+no, Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}