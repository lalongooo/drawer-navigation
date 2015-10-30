package lalongooo.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * By Jorge E. Hernandez (@lalongooo) 2015
 */
public class FragmentThree extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.textContent);

        tv.setText(this.getClass().getSimpleName());

        return rootView;
    }
}
