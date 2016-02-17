package com.letsnurture.com.sharedelementtransitions.app;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainFragment extends Fragment implements AbsListView.OnItemClickListener {

    private String[] textItems = {"PRATIK PATEL", "PARESH MAYANI", "PRIYANK BHOJAK", "CHINTAN SONI", "PIYUSH MALAVIYA", "PRANAY PATEL"};

    public static final String TRANSITION_NAME = "TRANSITION_NAME";
    public static final String ACTION = "ACTION";
    public static final String TRANSITION_TEXT = "TRANSITION_TEXT";
    public static final String IMAGENAME = "IMAGENAME";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listView);

        MyListAdapter myListAdapter = new MyListAdapter(getActivity(), R.layout.listitem, textItems);
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String imageTransitionName = "";
        String textTransitionName = "";

        ImageView imageView = (ImageView) view.findViewById(R.id.itemImageView);
        TextView textView = (TextView) view.findViewById(R.id.itemTextView);

        ImageView staticImage = (ImageView) getView().findViewById(R.id.itemImageView);

        TargetFragment TargetFragment = new TargetFragment();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementReturnTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image_trans));
            setExitTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.fade));

            TargetFragment.setSharedElementEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image_trans));
            TargetFragment.setEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.fade));

            imageTransitionName = imageView.getTransitionName();
            textTransitionName = textView.getTransitionName();
        }

        Bundle bundle = new Bundle();
        bundle.putString(TRANSITION_NAME, imageTransitionName);
        bundle.putString(ACTION, textView.getText().toString());
        bundle.putString(TRANSITION_TEXT, textTransitionName);
        bundle.putParcelable(IMAGENAME, ((BitmapDrawable) imageView.getDrawable()).getBitmap());
        TargetFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, TargetFragment)
                .addToBackStack(TargetFragment.class.getSimpleName())
                .addSharedElement(imageView, imageTransitionName)
                .addSharedElement(textView, textTransitionName)
                .addSharedElement(staticImage, getString(R.string.fragment_image_trans))
                .commit();
    }
}

class MyListAdapter extends ArrayAdapter<String> {
    private int[] images = {R.drawable.ic_git, R.drawable.ic_git_green, R.mipmap.ic_launcher, R.drawable.ic_git, R.drawable.ic_git_green, R.mipmap.ic_launcher};

    public MyListAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listitem, null);
        }

        String listItem = getItem(position);

        TextView textView = (TextView) view.findViewById(R.id.itemTextView);
        textView.setText(listItem);

        ImageView imageView = (ImageView) view.findViewById(R.id.itemImageView);
        imageView.setImageResource(images[position]);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setTransitionName("trans_text" + position);
            imageView.setTransitionName("trans_image" + position);
        }

        return view;
    }

}
