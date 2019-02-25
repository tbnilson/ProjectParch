package daotesting;

import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.DataProvider;

public class userdao {
  @Test(dataProvider = "dp")
  public void getUsertest(String uid, ) {
	  Assert.assertEquals(*, actual);
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
}
