package com.ldh.upgreen.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Utils extends Fragment {
    public static String[] privacies = {"Public", "Only me"};

    public static class LoadImage extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageViewAvatar;
        private Bitmap avatar;

        public LoadImage(ImageView imageViewAvatar) {
            this.imageViewAvatar = imageViewAvatar;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                imageViewAvatar.setImageBitmap(bitmap);
            }
        }

        public Bitmap getAvatar() {
            return avatar;
        }

        public void setAvatar(Bitmap avatar) {
            this.avatar = avatar;
        }
    }

    public static class LoadImageWithSavedImage extends AsyncTask<String, Void, Bitmap> {
        private Bitmap avatar;
        private IUtils iUtils;
        private ImageView imageViewvAvatar;
        //-------position is position (or index) of item whose avatar attribute is saved
        private int position;

        public LoadImageWithSavedImage(ImageView imageViewvAvatar, int position, IUtils iUtils) {
            this.imageViewvAvatar = imageViewvAvatar;
            this.iUtils = iUtils;
            this.position = position;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                imageViewvAvatar.setImageBitmap(bitmap);
                iUtils.assignAvatar(position, bitmap);
            }
        }

        public Bitmap getAvatar() {
            return avatar;
        }

        public void setAvatar(Bitmap avatar) {
            this.avatar = avatar;
        }
    }

    private static class LoadVideo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }


    //method sort values
    public static HashMap sortValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        //Custom comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        //copying the sorted list in HashMap to preserve the iteration order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static void setSizeAndLayout(RecyclerView rv, int layout) {
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(
                rv.getContext(),
                layout,
                false
        ));
    }

    public static void setRecyclerView(RecyclerView rv, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    public static void setOnClick(View v, Void func) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public String getToken() {
        SharedPreferences sp = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        return token;
    }
}
