package eclipseIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import login.TestStringData;

public class eclipseIOTest {

	public static void main(String[] args) throws IOException {

		JSONParser parser = new JSONParser();
		 
        try {
        	
        	//讀取 eclipse 內部文字檔
    		InputStream res = Main.class.getResourceAsStream("/configTxt/text.txt");
    		BufferedReader reader = new BufferedReader(new InputStreamReader(res));
    		
            Object obj = parser.parse(reader);
 
            JSONObject jsonObject = (JSONObject) obj;
 
            String name = (String) jsonObject.get("Name");
            String author = (String) jsonObject.get("Author");
            JSONArray companyList = (JSONArray) jsonObject.get("companyList");
 
            System.out.println("Name: " + name);
            System.out.println("Author: " + author);
            System.out.println("\nCompany List:");
            Iterator<String> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            
            //Json 轉成物件
            Gson gson= new Gson();
            TestStringData d = gson.fromJson(jsonObject.toString(), TestStringData.class);
            
            System.out.println(jsonObject.toString());
            
            //寫入 eclipse  內部文字檔案
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/configTxt/text2.txt"));
            out.write("Write the string to text file");
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
