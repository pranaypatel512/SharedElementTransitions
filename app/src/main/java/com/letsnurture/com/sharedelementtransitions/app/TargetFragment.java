package com.letsnurture.com.sharedelementtransitions.app;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ln-69 on 5/1/16.
 */
public class TargetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String actionTitle = "DUMMY ACTION";
        Bitmap imageBitmap = null;
        String transText = "";
        String transitionName = "";

        if (bundle != null) {
            transitionName = bundle.getString(MainFragment.TRANSITION_NAME);
            actionTitle = bundle.getString(MainFragment.ACTION);
            imageBitmap = bundle.getParcelable(MainFragment.IMAGENAME);
            transText = bundle.getString(MainFragment.TRANSITION_TEXT);
        }

        getActivity().setTitle(actionTitle);
        View view = inflater.inflate(R.layout.fragment_target, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.findViewById(R.id.listImage).setTransitionName(transitionName);
            view.findViewById(R.id.itemTextView).setTransitionName(transText);
        }

        ((ImageView) view.findViewById(R.id.listImage)).setImageBitmap(imageBitmap);
        ((TextView) view.findViewById(R.id.itemTextView)).setText(actionTitle);

        return view;
    }
}
