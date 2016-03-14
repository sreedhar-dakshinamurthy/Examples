package com.sdm.excelRead;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import static com.sdm.excelReader.ExcelReader.*;

public class ReadExcelTest {
    @Test(priority = 0)
    public void ReadExcelData() {
        class Local {
        }
        ;

        HashMap<String, ArrayList> excelData = ReadexcelData(ReadExcelTest.class.getSimpleName(),
                Local.class.getEnclosingMethod().getName());
        HashMap hashMap = excelData;
        Iterator iteratorColumnName = hashMap.keySet().iterator();
        for (Iterator iterator = excelData.keySet().iterator(); iterator.hasNext(); ) {

            System.out.print(iteratorColumnName.next() + " = { ");

            ArrayList arrayList = excelData.get(iterator.next());

            for (Iterator columnValues = arrayList.iterator(); columnValues.hasNext(); ) {
                System.out.print(columnValues.next());
                if (columnValues.hasNext())
                    System.out.print(", ");
            }
            System.out.println(" }");
        }
    }

    @Test(priority = 1)
    public void WriteIntoExcel() {
        HashMap<String, Object[]> data = new HashMap<String, Object[]>();

        data.put("1", new Object[]{"col1", "col2"});
        data.put("2", new Object[]{"value0col1", "Value0col2"});
        data.put("3", new Object[]{"value1col1", "Value1col2"});

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet();
        XSSFRow xssfRow;

        Set<String> keys = data.keySet();
        Iterator rowNum = data.keySet().iterator();
        for (String key : keys) {
            xssfRow = xssfSheet.createRow(Integer.parseInt(rowNum.next().toString()));
            Object[] object = data.get(key);
            int cellNum = 1;
            XSSFCell xssfCell;
            for (Object obj : object) {
                xssfCell = xssfRow.createCell(cellNum++);
                xssfCell.setCellValue(obj.toString());
            }
        }

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("SampleExcel.xlsx"));
            xssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void ReadFromExcel(){
        FileInputStream fileInputStream = null;
        HashMap<String,ArrayList> data = new HashMap<String, ArrayList>();
        XSSFWorkbook xssfWorkbook = null;
        XSSFSheet xssfSheet = null;
        try {
            fileInputStream = new FileInputStream(new File("SampleExcel.xlsx"));
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            xssfSheet = xssfWorkbook.getSheetAt(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFRow xssfRow;
        for(int rowNum = xssfSheet.getFirstRowNum();rowNum<=xssfSheet.getLastRowNum();rowNum++)
        {
            xssfRow = xssfSheet.getRow(rowNum);
            ArrayList<String> arrayList = new ArrayList<String>();
            XSSFCell xssfCell;
            for(int cellNum=xssfRow.getFirstCellNum();cellNum<xssfRow.getLastCellNum();cellNum++){
                xssfCell = xssfRow.getCell(cellNum);
                arrayList.add(xssfCell.getStringCellValue());
            }
            data.put(String.valueOf(rowNum),arrayList);
        }

        Set<String> keys = data.keySet();
        for(String key:keys){
            System.out.print("RowNumber "+"\""+key+"\" = { ");
            ArrayList<String> arrayList = data.get(key);
            for(Iterator iterator = arrayList.iterator();iterator.hasNext();){
                System.out.print(iterator.next());
                if(iterator.hasNext()){
                    System.out.print(", ");
                }
            }
            System.out.println(" }");
        }
    }
}