package com.rentix.utils;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class UnZip2 {
   static final int BUFFER = 2048;
   
   
   public static void main(String args[]){
	   
	   
	   UnZip2.unzipMemory("C:\\Users\\Iquer Spindola\\Desktop\\contratoNuevo.zip");
	   
   }
   
   
   public static ArrayList<ZipTmp> unzipMemory (String file) {
	   ArrayList<ZipTmp> iss = new ArrayList<ZipTmp>();
	   
	   try {
	        
	         BufferedInputStream is = null;
	         ZipEntry entry;
	         ZipFile zipfile = new ZipFile(file);
	         Enumeration e = zipfile.entries();
	         
	         while(e.hasMoreElements()) {
	        	 
	        	 entry = (ZipEntry) e.nextElement();
	             System.out.println("Extracting: " +entry);
	             is = new BufferedInputStream(zipfile.getInputStream(entry));
	             iss.add(new ZipTmp(is,entry.getName(),null));
	        	 
	         }
	               
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	   
	   return iss;
	   
	   /*
	   try{
		   FileOutputStream fos=null;
		   BufferedOutputStream dest = null;
		   
		   for (ZipTmp tmp:iss){
			   
			   int count;
	           byte data[] = new byte[BUFFER];
	           
	           fos = new FileOutputStream(new File("C:\\Users\\Iquer Spindola\\Desktop\\prueba\\" + tmp.getName()));
	           dest = new BufferedOutputStream(fos, BUFFER);
	           
	           while ((count = tmp.getIs().read(data, 0, BUFFER)) != -1) {
	              dest.write(data, 0, count);
	           }
	           
	           dest.flush();
	           dest.close();
	           tmp.getIs().close();
		   }
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }*/
 
   }
   
   public static void unzip (String file) {
      try {
         BufferedOutputStream dest = null;
         BufferedInputStream is = null;
         ZipEntry entry;
         ZipFile zipfile = new ZipFile(file);
         Enumeration e = zipfile.entries();
         
         while(e.hasMoreElements()) {
        	 
            entry = (ZipEntry) e.nextElement();
            System.out.println("Extracting: " +entry);
            
            is = new BufferedInputStream(zipfile.getInputStream(entry));
            
            int count;
            byte data[] = new byte[BUFFER];
            
            FileOutputStream fos = new FileOutputStream(entry.getName());
            
            dest = new BufferedOutputStream(fos, BUFFER);
            
            while ((count = is.read(data, 0, BUFFER)) != -1) {
               dest.write(data, 0, count);
            }
            
            dest.flush();
            dest.close();
            is.close();
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
}