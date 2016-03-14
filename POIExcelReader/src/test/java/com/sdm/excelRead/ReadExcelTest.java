package com.sdm.excelRead;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.sdm.excelReader.ExcelReader.*;

public class ReadExcelTest {
    @Test
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
}
