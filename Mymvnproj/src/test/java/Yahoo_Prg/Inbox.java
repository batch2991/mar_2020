package Yahoo_Prg;

import org.openqa.selenium.By;

import Yahoo_prop.InboxP;

public class Inbox extends MainClass
{
	
  public void deletemail() throws Exception
  {
	  Thread.sleep(5000);
	  driver.findElement(By.xpath(InboxP.xcheckbox)).click();
	  driver.findElement(By.xpath(InboxP.xdelete)).click();

	  System.out.println("Hello Inbox");

	  System.out.println("Hello");
	  System.out.println("hai");
	  System.out.println("new updates");
	  
  }
}
