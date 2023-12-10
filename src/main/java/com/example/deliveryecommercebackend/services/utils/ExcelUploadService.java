package com.example.deliveryecommercebackend.services.utils;

import com.example.deliveryecommercebackend.model.ProductType;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }

    public static List<ProductType> getProductTypeFromExcel(InputStream inputStream){
        List<ProductType>productTypes =new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                ProductType productType = new ProductType();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> productType.setCode(cell.getStringCellValue());
                        case 1 -> productType.setName(cell.getStringCellValue());
                        case 2 -> productType.setDes(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                productType.setCreated(Date.valueOf(LocalDate.now()));
                productTypes.add(productType);
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        return productTypes;
    }
}
