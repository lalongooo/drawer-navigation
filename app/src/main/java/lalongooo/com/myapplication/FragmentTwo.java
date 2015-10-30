package lalongooo.com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * By Jorge E. Hernandez (@lalongooo) 2015
 */
public class FragmentTwo extends Fragment {

    private ShowDetail showDetail;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        showDetail = (ShowDetail) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);

        TextView tv = (TextView) rootView.findViewById(R.id.textContent);
        tv.setText(this.getClass().getSimpleName());

        Button btn = (Button) rootView.findViewById(R.id.button);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetail.showDetails();
            }
        });

        return rootView;
    }

    public interface ShowDetail {
        void showDetails();
    }
}