package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	/*
	 *  Whenever there is @DataProvider annotation, this particular method is responsible for generating data
	 and passing data to another test methods in the project
	 *  "Data" is the name of the data provider which would be used in the method that is implemented
	 */
	// 
	@DataProvider (name="Data")
	public String[][] getAllData() throws IOException {
		
		// Below we are getting path of the file
		// To get current project location directly, we are using - System.getProperty("user.dir")
	    String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
	    
	    // Below is creation of object related to XL Utility and path is being passed as part of constructor
	    XLUtility xl = new XLUtility(path);
	    
	    // We are finding total number of rows and columns below
	    // Any row number can be passed related to colcount. It would count the total number of cells in that particular row
	    int rownum = xl.getRowCount("Sheet1");
	    int colcount = xl.getCellCount("Sheet1", 1);
	    
	    // How many rows and columns we have in excel sheet, below array also has same number of rows and columns 
	    String apidata[][] = new String[rownum][colcount];

	    /*
	     *  Below looping statement would get the data from excel sheet and store it in two dimensional array
	     *  
	     */
	    
	    for (int i = 1; i <= rownum; i++) {
	        for (int j = 0; j < colcount; j++) {
	            apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
	        }
	    }

	    return apidata;
	}
	
	@DataProvider (name = "UserNames")
	public String[] getUserNames() throws IOException {
	    String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
	    XLUtility xl = new XLUtility(path);
	    int rownum = xl.getRowCount("Sheet1");
	    String apidata[] = new String[rownum];

	    for (int i = 1; i <= rownum; i++) {
	        apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
	    }

	    return apidata;
	}

}
