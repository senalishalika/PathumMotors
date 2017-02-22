/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;


public class Excel {
   public int load(String no) 
      throws BiffException, IOException, WriteException
   {
      Workbook workbook = Workbook.getWorkbook(new File("E:\\project\\New folder (2)\\New folder\\pathumMoto\\Price-List1.xls"));
      int b=0;
      try{
      Sheet sheet = workbook.getSheet(0);
      Cell cell1 = sheet.getCell(0, 2);
      System.out.println(cell1.getContents());
      Cell cell2 = sheet.getCell(3, 4);
      Cell cell3=sheet.findCell(no);
      Cell cell4 = sheet.getCell(2,cell3.getRow());
      
      b=Integer.parseInt(cell4.getContents());
      System.out.println(cell2.getContents());
      System.out.println("fsdfsdfsd");
      System.out.println(cell3.getRow());
      
      System.out.println(b);
      workbook.close();
      }catch(Exception e){
          
      }
     return b;
   }
}


