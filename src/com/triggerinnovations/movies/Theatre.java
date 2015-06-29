package com.triggerinnovations.movies;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Theatre extends ListActivity {
	 HttpPost httppost;
	 StringBuffer buffer;
		byte[] data;
	   HttpResponse response;
		HttpClient httpclient;
		InputStream inputStream;
		SharedPreferences app_preferences ;
		List<NameValuePair> nameValuePairs;

	static final String KEY_RATE = "Rating:  "; // parent node
	static final String KEY_COMM = "Says:  ";
	static final String KEY_NAME = "name";
	 int lnum=0,cn=0,cc=1,cr=2;
	 String []temp;
    ListAdapter adapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pmain);
        
        ///////////////////////////////////////////////////////////////////////
        
        Intent in1 = getIntent();
        String mname = in1.getStringExtra("city");
        

		try
		{
		httpclient = new DefaultHttpClient();
		httppost = new HttpPost("http://10.0.2.2/theatre.php");
        // Add your data
				nameValuePairs = new ArrayList<NameValuePair>(1);
				nameValuePairs.add(new BasicNameValuePair("mname", mname));
				
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
				
		 	
		 		temp=str.split("@#");
		 		
//		 		movname=temp[0];
//		 		hub=temp[1];
//		 		dwd=temp[2];
//		 		details=temp[3];
//				
				
				lnum=(temp.length)/3;
				
				//Toast.makeText(getApplicationContext(), temp[0]+""+temp[1]+""+temp[2]+""+temp[3]+"", Toast.LENGTH_LONG).show();
//				Toast.makeText(getApplicationContext(), temp[4]+""+temp[5]+""+temp[6]+""+temp[7]+"", Toast.LENGTH_LONG).show();
//				Toast.makeText(getApplicationContext(), temp[8]+""+temp[9]+""+temp[10]+""+temp[11]+"", Toast.LENGTH_LONG).show();
//				Toast.makeText(getApplicationContext(), temp[12]+""+temp[13]+""+temp[14]+""+temp[15]+"", Toast.LENGTH_LONG).show();
				//movie=str.split("@#");
				//for(int i=0;i<movies.length;i++)
				
				
				
				
				inputStream.close();
			}
			catch (Exception e)
			{
			
			}
		
        
        ///////////////////////////////////////////////////////////////////////
        
        
        
        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
        // creating new HashMap
   		//String[] count=new String[3];
   	
        for (int i = 1; i <=lnum;i++) {
			// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				// adding each child node to HashMap key => value
				map.put(KEY_NAME,temp[cr]);
				map.put(KEY_COMM,temp[cc]);
				map.put(KEY_RATE,temp[cn]);
				
				cn=cn+3;
				cc=cc+3;
				cr=cr+3;
				// adding HashList to ArrayList
				menuItems.add(map);
			}
   		
     // Adding menuItems to ListView
//		 adapter = new SimpleAdapter(this, menuItems,R.layout.list,new String[] {"Says: "+KEY_NAME,KEY_COMM," Rating: "+KEY_RATE+""}, new int[] {R.id.name, R.id.comment,R.id.rate});
        adapter = new SimpleAdapter(this, menuItems,R.layout.list,new String[] {KEY_NAME,KEY_COMM,KEY_RATE}, new int[] {R.id.name, R.id.comment,R.id.rate});
        setListAdapter(adapter);
     
    }
}