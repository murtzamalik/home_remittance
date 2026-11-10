package org.ais.hr.fileupload.util;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class FileHelper {
    Path root = Paths.get("C:\\Home_Remit_files");

    public static Boolean CheckExcelFormat(MultipartFile file) {
        String contenttype = file.getContentType();
        if (contenttype.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }

    public static void ConvertExceltoListofUsers(FileInputStream inputStream) {

//        List<User> users=new ArrayList<>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            Boolean flag = false;
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String[] elements = new String[13];
                if (rowNumber == 0) {
                    Iterator<Cell> cells = row.iterator();
                    Cell cell = cells.next();
                    elements[0] = cell.getStringCellValue();
                    System.out.println("1 : " + elements[0]);
                    cell = cells.next();
                    elements[1] = cell.getStringCellValue();
                    System.out.println("2 : " + elements[1]);
                    cell = cells.next();
                    elements[2] = cell.getStringCellValue();
                    System.out.println("3 : " + elements[2]);
                    cell = cells.next();
                    elements[3] = cell.getStringCellValue();
                    System.out.println("4 : " + elements[3]);
                    cell = cells.next();
                    elements[4] = cell.getStringCellValue();
                    System.out.println("5 : " + elements[4]);
                    cell = cells.next();
                    elements[5] = cell.getStringCellValue();
                    System.out.println("6 : " + elements[5]);
                    cell = cells.next();
                    elements[6] = cell.getStringCellValue();
                    System.out.println("7 : " + elements[6]);
                    cell = cells.next();
                    elements[7] = cell.getStringCellValue();
                    System.out.println("8 : " + elements[7]);
                    cell = cells.next();
                    elements[8] = cell.getStringCellValue();
                    System.out.println("9 : " + elements[8]);
                    cell = cells.next();
                    elements[9] = cell.getStringCellValue();
                    System.out.println("10 : " + elements[9]);
                    cell = cells.next();
                    elements[10] = cell.getStringCellValue();
                    System.out.println("11 : " + elements[10]);
                    cell = cells.next();
                    elements[11] = cell.getStringCellValue();
                    System.out.println("12 : " + elements[11]);
                    cell = cells.next();
                    elements[12] = cell.getStringCellValue();
                    System.out.println("13 : " + elements[12]);


                    if (elements[0].equals("Remitter Name") && elements[1].equals("Remitter Number") && elements[2].equals("Remitter Address") && elements[3].equals("Beneficiary Name") && elements[4].equals("Beneficiary CNIC")
                            && elements[5].equals("Beneficiary Account #") && elements[6].equals("Transaction Reference") && elements[7].equals("Amount") && elements[8].equals("Beneficiary Branch code")
                            && elements[9].equals("Ben Other Contact") && elements[10].equals("Beneficiary Bank name") && elements[11].equals("Bene Branch Add 1") && elements[12].equals("Ben Branch Add 2")) {
                        flag = true;
                        rowNumber++;
                        continue;
                    } else {
                        flag = false;
                        throw new Exception("Colums name not matches!!!");

                    }

                }
                if (flag == false) {
                    break;
                } else {

                    Iterator<Cell> cells = row.iterator();

                    int cid = 0;

//                User u = new User();

                    while (cells.hasNext()) {
                        Cell cell = cells.next();

                        switch (cid) {
                            case 0:
//                                u.setUserId((int) cell.getNumericCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            case 1:
//                                u.setName(cell.getStringCellValue());
                                System.out.print(cell.getNumericCellValue() + " ");
                                break;
                            case 2:
//                                u.setAge((int) cell.getNumericCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            case 3:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            case 4:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getNumericCellValue() + " ");
                                break;
                            case 5:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            case 6:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            case 7:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getNumericCellValue() + " ");
                                break;
                            case 8:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getNumericCellValue() + " ");
                                break;
                            case 9:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            case 10:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            case 11:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            case 12:
//                                u.setGender(cell.getStringCellValue());
                                System.out.print(cell.getStringCellValue() + " ");
                                break;
                            default:
                                break;
                        }
                        cid++;

                    }


//                users.add(u);
                    System.out.println();


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}

