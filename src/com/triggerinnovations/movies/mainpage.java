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

import com.triggerinnovations.movies.SlideShow.RefreshHandler;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class mainpage extends Activity implements Runnable {
	
	String[] domain ={ "Search all", 
			"Laxmi", "Srinivas", "Sangam ", 
			"Sudha", "Apsara",
			"Shrangar" };
	
	static int index1,img;
	 private TextView txtStatus;
	    private ImageView imageView;
	    int i1=0;
	 
	ProgressDialog dialog;
	BufferedReader in;
	String[] movies;
	String sdomain;
	
	HttpPost httppost;
	StringBuffer buffer;
	byte[] data;
	HttpResponse response;
	HttpClient httpclient;
	InputStream inputStream;
	SharedPreferences app_preferences ;
	List<NameValuePair> nameValuePairs;
	int imgid[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.ii,R.drawable.j};
   RefreshHandler refreshHandler=new RefreshHandler();
   class RefreshHandler extends Handler{
       @Override
       public void handleMessage(Message msg) {
           // TODO Auto-generated method stub
           mainpage.this.updateUI();
       }
       public void sleep(long delayMillis){
           this.removeMessages(0);
           sendMessageDelayed(obtainMessage(0), delayMillis);
       }
   };
   public void updateUI(){
      // int currentInt=Integer.parseInt((String)txtStatus.getText())+10;
      // if(currentInt<=100){
           refreshHandler.sleep(2000);
           //txtStatus.setText(String.valueOf(currentInt));
           if(i1<imgid.length){
           	
           	 imageView.setImageResource(imgid[i1]);
               
               // imageView.setPadding(left, top, right, bottom);
               i1++;
           }
           else
           {
           	i1=0;
           }
       }
   
	
	String url="http://10.0.2.2/login.php";
	//String url="http://tii.2itb.com/";
	//String url="http://ti.2itb.com/"; 
	

    int i=0;
	String	txt,folders; 
	// flag for Internet connection status
	Boolean isInternetPresent = false;
	
	// Connection detector class
	ConnectionDetector cd;
	final Thread thread = new Thread(mainpage.this);
		
	//@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.movieui);
        
        Button lang=(Button) findViewById(R.id.lang);
        Button city=(Button) findViewById(R.id.city);
       // Button theatre=(Button) findViewById(R.id.theatre);
        Button recent=(Button) findViewById(R.id.recent);
        Button go=(Button) findViewById(R.id.b1);
        this.txtStatus=(TextView)this.findViewById(R.id.textView1);
        this.imageView=(ImageView)this.findViewById(R.id.imageView);
        updateUI();
        final Spinner spinstate = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter state = new ArrayAdapter(this,android.R.layout.simple_spinner_item,domain);
        state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinstate.setAdapter(state);	
        
		spinstate.setOnItemSelectedListener(new OnItemSelectedListener()
		  {
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3)
		  {
				 sdomain = spinstate.getSelectedItem().toString();
//				((TextView) spincount.getSelectedItem()).setTextColor(Color.WHITE);
//				((TextView) spincount.getSelectedItem()).setTextSize(18);
			//	((TextView)arg0.getChildAt(0)).setTextColor(Color.WHITE);
				((TextView)arg0.getChildAt(0)).setTextSize(18);
		  Toast.makeText(getBaseContext(),sdomain,Toast.LENGTH_LONG).show();                                                 
		  }
		  public void onNothingSelected(AdapterView<?> arg0)
		  {
		  // TODO Auto-generated method stub
		  }
		  });
		
		
		
		
		
		
		
     // creating connection detector class instance
		cd = new ConnectionDetector(getApplicationContext());

         movies = new String[10];
        if(img==4)
        {
        	img=0;
        }
        final int loader = R.drawable.a;
		ImageView image = (ImageView) findViewById(R.id.imageView);
		
		String image_url =url+"Adds/"+(img++)+".png";
		
     //   Toast.makeText(getApplicationContext(), img+"", Toast.LENGTH_LONG).show();

		ImageLoader imgLoader = new ImageLoader(getApplicationContext());
		imgLoader.DisplayImage(image_url, loader, image);
		// get Internet status
		isInternetPresent = cd.isConnectingToInternet();

        lang.setOnClickListener(new OnClickListener() {
			//@SuppressWarnings("null")
			//@Override
			public void onClick(View arg0) 
			{
			// check for Internet status
				if (isInternetPresent) {
			
					Intent lg=new Intent();
					lg.setClass(mainpage.this, first.class);
					startActivity(lg);
					
				} else {
					// Internet connection is notfirst present
					// Ask user to connect to Internet
					showAlertDialog(mainpage.this, "No Internet Connection",
							"You don't have internet connection.", false);
				}
			}
    });
        
        city.setOnClickListener(new OnClickListener() {
		//	@SuppressWarnings("null")
			//@Override
			public void onClick(View arg0) 
			{
				// check for Internet status
				if (isInternetPresent) {
				
					
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(mainpage.this);
					 
			        // Setting Dialog Title
			        alertDialog.setTitle("Select the City");
			 
			        // Setting Dialog Message
			        //alertDialog.setMessage("Select the City");
			 
			        //Setting Icon to Dialog
			        alertDialog.setIcon(R.drawable.okok);
			 
			        // Setting Positive "Yes" Button
			        alertDialog.setPositiveButton("Hubli", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog,int which) {
			 
			            // Write your code here to invoke YES event
			            Toast.makeText(getApplicationContext(), "Searching movies in Hubli", Toast.LENGTH_SHORT).show();
			            
			            Bundle bd=new Bundle();
			            bd.putString("city", "Hubli");
			            Intent id=new Intent();
			            id.setClass(mainpage.this,City.class );
			            id.putExtras(bd);
			            startActivity(id);
			            
			            
			            dialog.cancel();
			            
			            }
			        });
			         
			        // Setting Negative "NO" Button
			        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            // Write your code here to invoke NO event
//			            Toast.makeText(getApplicationContext(), "Searching movies in Dharwar", Toast.LENGTH_SHORT).show();
			            dialog.cancel();
			            }
			        });
			 
			        alertDialog.setNeutralButton("Dharwar", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            // Write your code here to invoke NO event
			            Toast.makeText(getApplicationContext(), "Searching movies in Dharwar", Toast.LENGTH_SHORT).show();
			            Bundle bd=new Bundle();
			            bd.putString("city", "Dharwar");
			            Intent id=new Intent();
			            id.setClass(mainpage.this,City.class );
			            id.putExtras(bd);
			            startActivity(id);
			            
			            
			            dialog.cancel();
			            }
			        });
			 
			        
			        // Showing Alert Message
			        alertDialog.show();

					
					
					
					
					
				} else {
					// Internet connection is notfirst present
					// Ask user to connect to Internet
					showAlertDialog(mainpage.this, "No Internet Connection",
							"You don't have internet connection.", false);
				}
				}
    });
        
        go.setOnClickListener(new OnClickListener() {
		//	@SuppressWarnings("null")
			//@Override
			public void onClick(View arg0) 
			{
				Bundle bd=new Bundle();
	            bd.putString("city", sdomain);
	            Intent id=new Intent();
	            id.setClass(mainpage.this,Theatre.class );
	            id.putExtras(bd);
	            startActivity(id);
				
				
			}
    });
        
    recent.setOnClickListener(new OnClickListener() {
		//	@SuppressWarnings("null")
			//@Override
			public void onClick(View arg0) 
			{
				// check for Internet status
				if (isInternetPresent) {
				
				folders="Telugu";
				txt = url+"Telugu.txt";
				dialog = ProgressDialog.show(mainpage.this, "Authenticating", "Please Wait..", true, false); 
				Thread thread = new Thread(mainpage.this);
				thread.start();
				} else {
					// Internet connection is notfirst present
					// Ask user to connect to Internet
					showAlertDialog(mainpage.this, "No Internet Connection",
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
    			
    			//Toast.makeText(getApplicationContext(), temMovies[0]+"    o   "+temMovies[1]+"    o   "+temMovies[2]+"    o   "+temMovies[3], 1111).show();	
    			
    			
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