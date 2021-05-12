package com.qa.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelReader {
	
	
	  public List<Map <String, String>> getData(String sheetName,String TestcaseID,String Scenario_Name) throws FilloException{
		  Fillo fillo = new Fillo();
		  
		  Connection connection = fillo.getConnection(System.getProperty("user.dir")+"\\src\\test\\resources\\data\\Testdata.xlsx");
	        
		  String strSelectQuerry =" Select * from " + sheetName + " where TestcaseID='" + TestcaseID.trim() + "' and Scenario_Name='"+ Scenario_Name+"'" ;
		  Recordset recordset =connection.executeQuery(strSelectQuerry);
		  
		  List<Map<String, String>> listOfMaps = new ArrayList<>();
		  while(recordset.next()) {
		      ArrayList<String> collection = recordset.getFieldNames();
		      int size = collection.size();
		      Map<String, String> values = new HashMap<>();
		      for (int i = 0; i <=(size-1); i++) {
		          String colname = collection.get(i);
		          String colval = recordset.getField(colname);
		          values.put(colname, colval);
		      }
		      listOfMaps.add(values);
		  }
		  
					/*
					 * String strUpdateQuerry =
					 * "Update Data Set SiteTitle = 'SoftwareTestingHelp.com' ";
					 * connection.executeUpdate (strUpdateQuerry);
					 */
		  recordset.close();

		  connection.close();
		  
		return listOfMaps;
		  
	  }

}
