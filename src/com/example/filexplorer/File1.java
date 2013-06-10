package com.example.filexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class File1 extends ListActivity
{
 Button bn1,bn2;	
 private List<String> item = null;
 private List<String> path = null;
 private String root="/";
 private TextView myPath;
 long b=0;
 File f = new File(root);
 final File[] files = f.listFiles();
 /** Called when the activity is first created. */
    @Override
   public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file1);
        myPath = (TextView)findViewById(R.id.path);
        getDir(root);
    }
    private void getDir(final String dirPath)
    {
    	item = new ArrayList<String>();
    	 path = new ArrayList<String>();
         myPath.setText("SORTING BY NAME " + dirPath);
    	     
     if(!dirPath.equals(root))
     {
      item.add(root);
      path.add(root);
      path.add(f.getParent());
     }
     
     for(int i=0; i<files.length ;i++)
		  for(int j=0; j<files.length-i-1;j++)
		  {
				   if(files[j].getName().compareTo(files[j+1].getName())>0)
				   {
					File temp= files[j];
					files[j] = files[j+1];
					files[j+1]=temp;
				   }
				
		  }

 		   for(int i=0; i < files.length; i++)
 	       {       
 	        File file = files[i];
 	        b=file.length();
 	        path.add(file.getPath());
 	        if(file.isDirectory())
 	        {
 	    	   File[] f1 = file.listFiles();
 	           if(f1 != null)
 	                   b = f1.length;
 	           else b = 0;
 	           item.add(file.getName() + "/  Size : " + b + " items");
 	       }
 	       else
 	        item.add(file.getName() + "   Size : " + b + " bytes");
 	      }	        
     ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, R.layout.file_list_row, item);
     setListAdapter(fileList); 
    }
    
    
}