package Yahoo_Prg;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import Yahoo_prop.LoginP;

public class Login extends MainClass
{
 public void open()
 {
	 
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.get(LoginP.yurl);
	lg.debug("Login.java   :  open   :  after open url");
 }
 public void valid_login() throws Exception
 {
	open();
	Properties p = new Properties();
	p.load(new FileInputStream(new File("src/test/resources/config.properties")));
	lg.debug("Login.java    :   Login  :  before valid login");
	driver.findElement(By.name(LoginP.nemail)).sendKeys(p.getProperty("uid"));
	driver.findElement(By.name(LoginP.nnext)).click();
	driver.findElement(By.name(LoginP.npwd)).sendKeys(p.getProperty("pwd"));
	driver.findElement(By.name(LoginP.nnext2)).click();
 }
 public void validate_login() throws Exception
 {
	    String err;
		
		FileInputStream fin=new FileInputStream("d:\\sel_dec18\\data.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fin);
		XSSFSheet ws=wb.getSheet("Sheet2");  
		
		Row row;
		for(int r=1;r<=ws.getLastRowNum();r++) //for all the rows in the sheet
		{
			row=ws.getRow(r);
			open();
			Thread.sleep(2000);
			driver.findElement(By.name(LoginP.nemail)).sendKeys(row.getCell(0).getStringCellValue());
			driver.findElement(By.name(LoginP.nnext)).click();
			Thread.sleep(5000);
			driver.findElement(By.name(LoginP.npwd)).sendKeys(row.getCell(1).getStringCellValue());
			driver.findElement(By.name(LoginP.nnext2)).click();
			Thread.sleep(5000);
			
			try
			{
				if(driver.findElement(By.linkText(LoginP.lsignout)).isDisplayed())
				{
					row.createCell(2).setCellValue("Login is success");
					driver.findElement(By.linkText(LoginP.lsignout)).click();
				}
			}
			catch(Exception e)
			{
				err=driver.findElement(By.xpath(LoginP.xerrmsg)).getText();
				row.createCell(2).setCellValue("Login is failed  :  "+err);			
			}			
		}
		FileOutputStream fout=new FileOutputStream("d:\\sel_dec18\\data.xlsx");
		wb.write(fout);
		fin.close();
		fout.close(); 
 }
 public void signup() throws Exception
 {
	 open();
	 driver.findElement(By.id(LoginP.isignup)).click();
	 try
	 {
		 if(driver.findElement(By.name(LoginP.nfname)).isDisplayed())
		 {
			log=ext.createTest("Login");
			log.log(Status.PASS,"Signup is working");
											///To add screenshot to the report.... 1st takescreenshot , save in the local disk and add the image to the report
			File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f,new File("d:\\Mar_2020\\images\\signup.png"));
			log.addScreenCaptureFromPath("d:\\Mar_2020\\images\\signup.png");
		 }
	 }
	 catch(Exception e)
	 {
		 log=ext.createTest("Login");
		 log.log(Status.FAIL,"Signup NOT working");
		 File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(f,new File("d:\\Mar_2020\\images\\signup.png"));
		 log.addScreenCaptureFromPath("d:\\Mar_2020\\images\\signup.png");
	 }
 }
}
