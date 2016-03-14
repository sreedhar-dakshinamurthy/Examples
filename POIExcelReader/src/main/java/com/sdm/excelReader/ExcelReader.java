package com.sdm.excelReader;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sd4 on 3/10/16.
 */
public class ExcelReader {

    public static HashMap ReadexcelData(String className, String methodName){
        FileInputStream fileInputStream = null;
        XSSFWorkbook xssfWorkbook = null;
        XSSFSheet xssfSheet = null;

        try {
            fileInputStream = new FileInputStream(new File(className+".xlsx"));
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            xssfSheet = xssfWorkbook.getSheetAt(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int namedCellIdx = xssfWorkbook.getNameIndex(methodName);
        XSSFName xssfName = xssfWorkbook.getNameAt(namedCellIdx);

        AreaReference areaReference = new AreaReference(xssfName.getRefersToFormula());

        int noOfRows = areaReference.getLastCell().getRow()-areaReference.getFirstCell().getRow();
        int rowStart = areaReference.getFirstCell().getRow()+1;
        int noOfCols = areaReference.getLastCell().getCol()-areaReference.getFirstCell().getCol()+1;
        int colStart = areaReference.getFirstCell().getCol();

        HashMap<String,ArrayList> headerColumnValue = new HashMap<String, ArrayList>();

        for (int i=0; i<noOfCols; i++){
            ArrayList<String> columnValues = new ArrayList<String>();
            XSSFRow xssfRowHeader = xssfSheet.getRow(rowStart-1);
            XSSFCell cellHeader = xssfRowHeader.getCell(colStart+i);
            for(int j=0; j<noOfRows; j++){
                XSSFRow xssfRow = xssfSheet.getRow(rowStart+j);
                XSSFCell cell = xssfRow.getCell(colStart+i);
                columnValues.add(cell.getStringCellValue());
            }
            headerColumnValue.put(cellHeader.getStringCellValue(),columnValues);
        }
        return headerColumnValue;
    }
}
