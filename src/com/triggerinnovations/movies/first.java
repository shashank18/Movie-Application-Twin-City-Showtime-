package com.triggerinnovations.movies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class first extends Activity implements Runnable {
	
	static int index1,img;
	ProgressDialog dialog;
	BufferedReader in;
	String[] movies;
	HttpPost httppost;
	StringBuffer buffer;
	byte[] data;
	HttpResponse response;
	HttpClient httpclient;
	InputStream inputStream;
	SharedPreferences app_preferences ;
	List<NameValuePair> nameValuePairs;
	
	String url="http://10.0.2.2/login.php";
	//String url="http://tii.2itb.com/";
	//String url="http://ti.2itb.com/"; 
	

    int i=0;
	String	txt,folders; 
	// flag for Internet connection status
	Boolean isInternetPresent = false;
	
	// Connection detector class
	ConnectionDetector cd;
	final Thread thread = new Thread(first.this);
		
	//@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first);
        
        Button hindi=(Button) findViewById(R.id.hindi);
        Button kannada=(Button) findViewById(R.id.kannada);
        Button english=(Button) findViewById(R.id.english);
        Button telugu=(Button) findViewById(R.id.telugu);
        
     // creating connection detector class instance
		cd = new ConnectionDetector(getApplicationContext());

         movies = new String[10];
        if(img==4)
        {
        	img=0;
        }
        final int loader = R.drawable.loader;
		ImageView image = (ImageView) findViewById(R.id.ti);
		
		String image_url =url+"Adds/"+(img++)+".png";
		
     //   Toast.makeText(getApplicationContext(), img+"", Toast.LENGTH_LONG).show();

		ImageLoader imgLoader = new ImageLoader(getApplicationContext());
		imgLoader.DisplayImage(image_url, loader, image);
		// get Internet status
		isInternetPresent = cd.isConnectingToInternet();

        hindi.setOnClickListener(new OnClickListener() {
			//@SuppressWarnings("null")
			//@Override
			public void onClick(View arg0) 
			{
			// check for Internet status
				if (isInternetPresent) {
					//txt = url+"Hindi.txt";
					folders="Hindi";
					dialog = ProgressDialog.show(first.this, "Loading", "Please Wait..", true, false); 
					Thread thread = new Thread(first.this);
					thread.start();
				} else {
					// Internet connection is notfirst present
					// Ask user to connect to Internet
					showAlertDialog(first.this, "No Internet Connection",
							"You don't have internet connection.", false);
				}
			}
    });
        
        kannada.setOnClickListener(new OnClickListener() {
		//	@SuppressWarnings("null")
			//@Override
			public void onClick(View arg0) 
			{
				// check for Internet status
				if (isInternetPresent) {
				folders="Kannada";
				txt = url+"Kannada.txt";
				dialog = ProgressDialog.show(first.this, "Authenticating", "Please Wait..", true, false); 
				Thread thread = new Thread(first.this);
				thread.start();
				} else {
					// Internet connection is notfirst present
					// Ask user to connect to Internet
					showAlertDialog(first.this, "No Internet Connection",
							"You don't have internet connection.", false);
				}
				}
    });
        
        english.setOnClickListener(new OnClickListener() {
		//	@SuppressWarnings("null")
			//@Override
			public void onClick(View arg0) 
			{
				
				// check for Internet status
				if (isInternetPresent) {
			
				txt = url+"English.txt";
				folders="English";	
				//Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
				try{
				dialog = ProgressDialog.show(first.this, "Authenticating", "Please Wait..", true, false); 
				Thread thread = new Thread(first.this);
				thread.start();
				}
				catch(Exception e)
				{
				}
				
				} else {
				// Internet connection is notfirst present
				// Ask user to connect to Internet
				showAlertDialog(first.this, "No Internet Connection",
						"You don't have internet connection.", false);
			}
			
			}
        
    });
        
    telugu.setOnClickListener(new OnClickListener() {
		//	@SuppressWarnings("null")
			//@Override
			public void onClick(View arg0) 
			{
				// check for Internet status
				if (isInternetPresent) {
				
				folders="Telugu";
				txt = url+"Telugu.txt";
				dialog = ProgressDialog.show(first.this, "Authenticating", "Please Wait..", true, false); 
				Thread thread = new Thread(first.this);
				thread.start();
				} else {
					// Internet connection is notfirst present
					// Ask user to connect to Internet
					showAlertDialog(first.this, "No Internet Connection",
							"You don't have internet connection.", false);
				}
				}
        
    });
    }
	
	public void run() {
		process();
		handler.sendEmptyMessage(0);
	  }

	private Handler handler = new Handler() {
        //@Override
     //   @SuppressWarnings("null")
		public void handleMessage(Message msg) {
                dialog.dismiss();
                
                Bundle b1=new Bundle();
                Intent i1=new Intent();
                if(movies[0].equalsIgnoreCase("no"))
                {
                	i1.setClass(getApplicationContext(),NoMovies.class);
        			//i1.putExtras(b1);
        			startActivity(i1);	
                }
                else{
                	String temMovies[]=new String[(movies.length)-1];
//                	Toast.makeText(getApplicationContext(), movies[0], 1).show();
//					Toast.makeText(getApplicationContext(), movies[1], 1).show();
//					Toast.makeText(getApplicationContext(), movies[2], 1).show();
//					Toast.makeText(getApplicationContext(), movies[3], 1).show();
//					Toast.makeText(getApplicationContext(), movies[4], 1).show();
//					
                	
                	for(int ii=0;ii<(movies.length)-1;ii++)
                	{	temMovies[ii]=movies[ii];
                	}
                	
                	
					//Toast.makeText(getApplicationContext(), movies[5], 1).show();
              //  Toast.makeText(getApplicationContext(), movies[0]+"    o   "+movies[1], 1111).show();	
    			b1.putStringArray("hindi", temMovies);
    			b1.putInt("index", i);
    			b1.putInt("len", temMovies.length);
    			b1.putString("folder",folders);
    			i1.setClass(getApplicationContext(),trytry.class);
    			i1.putExtras(b1);
    			
    			//Toast.makeText(getApplicationContext(), temMovies[0], 1111).show();	
    			
    			
    			startActivity(i1);
                }
    			finish();
	    }
		};

		
    public void process()
	{		
//		try {
			///////////////////////////////////////////////////////////////////
			
			try
			{
			httpclient = new DefaultHttpClient();
			httppost = new HttpPost("http://10.0.2.2/login.php");
	        // Add your data
					nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("type", folders));
					//nameValuePairs.add(new BasicNameValuePair("Password", pass.trim()));
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
					response = httpclient.execute(httppost);
					inputStream = response.getEntity().getContent();
					data = new byte[256];
					buffer = new StringBuffer();
					int len = 0;
					while (-1 != (len = inputStream.read(data)) )
					{
						buffer.append(new String(data, 0, len));
						
					}
					String str =buffer.toString();
					movies=str.split("@#");
					//for(int i=0;i<movies.length;i++)
					
					
					Toast.makeText(getApplicationContext(), str, 1).show();
					
					inputStream.close();
//				}
//				catch (Exception e)
//				{
//				//	Toast.makeText(Lect_sims_login1.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
//				}
			
			
			///////////////////////////////////////////////////////////////
			
			
//			URL url=new URL(txt);
//			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//			//String str;
//			while((str=in.readLine())!=null)
//			{
//				movies[i++]=str;
//			}
//			in.close();
//			in=null;
			thread.stop();
			}catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    public void showAlertDialog(Context context, String title, String message, Boolean status) {
	AlertDialog alertDialog = new AlertDialog.Builder(context).create();

	// Setting Dialog Title
	alertDialog.setTitle(title);
	// Setting Dialog Message
	alertDialog.setMessage(message);
	// Setting alert dialog icon
	alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
	// Setting OK Button
	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
		}
	});
	// Showing Alert Message
	alertDialog.show();
}
}