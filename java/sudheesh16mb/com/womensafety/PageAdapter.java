package sudheesh16mb.com.womensafety;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by KUTTAN on 16-08-2016.
 */
public class PageAdapter  extends FragmentStatePagerAdapter{
    int numberOfTabs;
    public PageAdapter(FragmentManager fm,int nTabs) {
        super(fm);
        this.numberOfTabs=nTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                tab1 t1=new tab1();
                return t1;
            case 1:
                tab2 t2=new tab2();
                return t2;
            case 2:
                tab3 t3=new tab3();
                return t3;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
