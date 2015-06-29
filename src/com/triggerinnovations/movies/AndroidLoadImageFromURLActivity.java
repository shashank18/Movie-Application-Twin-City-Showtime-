package com.triggerinnovations.movies;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class AndroidLoadImageFromURLActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Loader image - will be shown before loading image
        int loader = R.drawable.loader;
        
        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.image);
        ImageView image11 = (ImageView) findViewById(R.id.imagee);
        ImageView image22 = (ImageView) findViewById(R.id.imager);
     //   ImageView ti = (ImageView) findViewById(R.id.ti);
        
        // Image url
        String image_url =  "http://10.0.2.2/img.jpg";
        String image_url11 =  "http://10.0.2.2/img.jpg";
        String image_url22 =  "http://10.0.2.2/img.jpg";
        String image_ti =  "http://10.0.2.2/TI1.png";
        //String image_url = "http://api.androidhive.info/images/sample.jpg";
        
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
        ImageLoader imgLoader11 = new ImageLoader(getApplicationContext());
        ImageLoader imgLoader22 = new ImageLoader(getApplicationContext());
        ImageLoader imgLoaderti = new ImageLoader(getApplicationContext());
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(image_url, loader, image);
        imgLoader11.DisplayImage(image_url11, loader, image11);
        imgLoader22.DisplayImage(image_url22, loader, image22);
        imgLoaderti.DisplayImage(image_ti, loader, image22);
    }
}