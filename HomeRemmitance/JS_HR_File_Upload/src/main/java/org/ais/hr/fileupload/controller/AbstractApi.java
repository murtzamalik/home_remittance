package org.ais.hr.fileupload.controller;
/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 11/1/2021
 * Time: 12:26 AM
 * Project : agentmate
 */


import org.ais.hr.fileupload.Service.RemitCompanyFileUploadService;
import org.ais.hr.fileupload.Service.TransactionService;
import org.ais.hr.fileupload.dto.LoggedUserDetail;
import org.ais.hr.fileupload.model.*;
import org.ais.hr.fileupload.util.JWTSecurity;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class AbstractApi {

    private static final String[] IP_HEADER_CANDIDATES = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};

    //	public static final Map<String, TblCustomerAll> LOGGED_IN_CUSTOMERALL_MAP = new HashMap<String, TblCustomerAll>();
    Logger LOG = LoggerFactory.getLogger(AbstractApi.class);
    //    @Autowired
//    DataSource dataSource;
    @Autowired
    private Environment env;

    @Autowired
    private TransactionService transactionService;


    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public ResponseEntity<HashMap<String, Object>> getResponseFormat(HttpStatus status, String message, Object data) {

        int responsestatus;
        if (status.equals(HttpStatus.OK) || status.value() == 200) {
            responsestatus = 1;
        } else {
            responsestatus = 0;
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("responsecode", responsestatus);
        map.put("messages", message);
        map.put("data", data);
        return ResponseEntity.status(status).body(map);
    }

    public ResponseEntity<HashMap<String, Object>> getSingleCustomizeResponseFormat(HttpStatus status, String message, Object data,
                                                                                    String key) {
        int responsestatus;
        if (status.equals(HttpStatus.OK) || status.value() == 200) {
            responsestatus = 1;
        } else {
            responsestatus = 0;
        }

        HashMap<String, Object> datamap = new HashMap<>();
        datamap.put(key, data);
        HashMap<String, Object> map = new HashMap<>();
        map.put("responsecode", responsestatus);
        map.put("messages", message);
        map.put("data", datamap);
        return ResponseEntity.status(status).body(map);
    }

    public ResponseEntity<HashMap<String, Object>> getMultipleCustomizeResponseFormat(HttpStatus status, String message,
                                                                                      HashMap<String, Object> datamap) {
        int responsestatus;
        if (status.equals(HttpStatus.OK) || status.value() == 200) {
            responsestatus = 1;
        } else {
            responsestatus = 0;
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("responsecode", responsestatus);
        map.put("messages", message);
        map.put("data", datamap);
        return ResponseEntity.status(status).body(map);
    }


    public String getResponseFromPostAPI(Map headerMap, Map postParam, String url) {

        try {

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

            headers.setAll(headerMap);

            HttpEntity<?> request = new HttpEntity<>(postParam, headers);
            ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);

            String entityResponse = (String) response.getBody();
            HttpStatus entitystatus = (HttpStatus) response.getStatusCode();
            if (entitystatus.value() == 200) {
                System.out.println(entityResponse);
                if (!entityResponse.isEmpty()) {
                    return entityResponse;
                } else {
                    return null;
                }
            } else {
                return entityResponse;
            }

        } catch (RestClientException e) {
            // process exception
            if (e instanceof HttpStatusCodeException) {
                String errorResponse = ((HttpStatusCodeException) e).getResponseBodyAsString();
                System.out.println(errorResponse);
                return errorResponse;
            }
        } catch (Exception e) {
            String e5 = e.getMessage();

            return e5;
        }
        return null;

    }

    public String getResponseFromGetAPI(String url) {
        HttpURLConnection conn = null;
        try {
            URL target = new URL(url);
            conn = (HttpURLConnection) target.openConnection();
            if (conn instanceof HttpsURLConnection) {
                HttpsURLConnection https = (HttpsURLConnection) conn;
                // Punjab NFS serves a valid DigiCert chain for CN=*.pitb.gov.pk on host nfs.punjab.gov.pk
                // (hostname SAN mismatch). Keep PKIX trust, only relax hostname for known gov NFS hosts.
                https.setSSLSocketFactory(SSLContext.getDefault().getSocketFactory());
                https.setHostnameVerifier(govNfsHostnameVerifier());
            }
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(120000);
            conn.setRequestProperty("Accept", "application/json, text/plain, */*");
            conn.setRequestProperty("User-Agent", "JS-HomeRemit/1.0");
            int status = conn.getResponseCode();
            InputStream stream = status >= 400 ? conn.getErrorStream() : conn.getInputStream();
            if (stream == null) {
                LOG.error("getResponseFromGetAPI empty stream status={} url={}", status, url);
                return null;
            }
            String body;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                body = sb.toString().trim();
            }
            if (status != 200) {
                LOG.error("getResponseFromGetAPI HTTP {} for url={} bodyPrefix={}", status, url, body.length() > 180 ? body.substring(0, 180) : body);
                return null;
            }
            if (body.isEmpty()) {
                return null;
            }
            // HTML error pages must not be treated as JSON payload
            String lower = body.substring(0, Math.min(40, body.length())).toLowerCase();
            if (lower.startsWith("<!doctype") || lower.startsWith("<html")) {
                LOG.error("getResponseFromGetAPI got HTML instead of JSON for url={}", url);
                return null;
            }
            return body;
        } catch (Exception e) {
            String e1 = e.getMessage();
            if (e1 == null && e.getCause() != null) {
                e1 = e.getCause().getMessage();
            }
            if (e1 == null) {
                e1 = e.getClass().getSimpleName();
            }
            LOG.error("getResponseFromGetAPI failed for url={} :: {}", url, e1);
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * Accept hostname when cert is issued to PITB wildcard while service is published on Punjab/NACTA NFS hosts.
     */
    private HostnameVerifier govNfsHostnameVerifier() {
        final HostnameVerifier defaultVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
        return (String hostname, SSLSession session) -> {
            if (hostname == null) {
                return false;
            }
            String h = hostname.toLowerCase();
            if (h.equals("nfs.punjab.gov.pk")
                    || h.equals("www.nfs.punjab.gov.pk")
                    || h.equals("nfs.nacta.gov.pk")
                    || h.equals("www.nfs.nacta.gov.pk")) {
                return true;
            }
            return defaultVerifier.verify(hostname, session);
        };
    }


    public String getrequestHeadervalue(HttpServletRequest request, String key) {
        return (String) request.getHeader(key);
    }


    public ResponseEntity<HashMap<String, Object>> getResponseFormatWithHTTPStatus(HttpStatus status, String message, Object data) {

        int responsestatus;
        if (status.equals(HttpStatus.OK) || status.value() == 200) {
            responsestatus = 1;
        } else {
            responsestatus = 0;
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("responsecode", responsestatus);
        map.put("messages", message);
        map.put("data", data);
        return ResponseEntity.status(status).body(map);
    }

    public String generateRrnNumber() {
        int length = Integer.valueOf(4);
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }

    public String getJsBankDateTimeFormatForPresent() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(new Date());
    }

    public String generateSha256HashCodeForJsRequestObjects(Object object) throws IllegalAccessException {
        String rawString = new String();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            rawString = rawString + (String.valueOf(field.get(object)));
        }
        rawString = rawString.replace("null", "");
        String sha256hascode = DigestUtils.sha256Hex(rawString);
        return sha256hascode;
    }


    public LoggedUserDetail getLoggedUserDataFromHeaderToken(String header) {
        JWTSecurity jwtSecurity = new JWTSecurity();
        HashMap<String, String> data = jwtSecurity.parseJWT(header.substring(7));
        if (data != null && data.get("Expired").equalsIgnoreCase("N")) {
            LoggedUserDetail loggedUserDetail = new LoggedUserDetail();

            loggedUserDetail.setUserId(Long.parseLong(data.get("userId")));
            loggedUserDetail.setLoginId(Long.parseLong(data.get("loginId")));
            TblAuthAccessToken tblAuthAccessToken=transactionService.tokenVerification(BigDecimal.valueOf(loggedUserDetail.getUserId()),header);
            if(tblAuthAccessToken!=null){
                return loggedUserDetail;
            }
            else{
                return null;
            }





        } else {
            return null;
        }

    }


    public void sendEmail(List<String> toEmail, String ccEmail, String subject, String body, String filePath,
                          String fileName) {
        String smtpHost = "smtp.gmail.com";
        String fromEmail = "murtzamlk@gmail.com";
        String emailPassword = "bbmwafb@123";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            InternetAddress[] sendTo = new InternetAddress[toEmail.size()];
            for (int i = 0; i < toEmail.size(); i++) {
                sendTo[i] = new InternetAddress(toEmail.get(i));
            }
            message.setRecipients(MimeMessage.RecipientType.TO, sendTo);
            if (ccEmail != null) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail));
            }

            message.setSubject(subject);
            message.setText(body);

            // 3) create MimeBodyPart object and set your message text
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText(body);

            // 5) create Multipart object and add MimeBodyPart objects to this object
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);

            if (fileName != null && !fileName.isEmpty()) {
                // 4) create new MimeBodyPart object and set DataHandler object to this object
                MimeBodyPart messageBodyPart2 = new MimeBodyPart();

                FileDataSource source = new FileDataSource(filePath + fileName);
                messageBodyPart2.setDataHandler(new DataHandler(source));
                messageBodyPart2.setFileName(fileName);

                multipart.addBodyPart(messageBodyPart2);
            }

            // 6) set the multiplart object to the message object
            message.setContent(multipart);

            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public FileInputStream getFileInputStreamOnPath(String fileName, String filePath, String extension) {
        try {
            Path path = Paths.get(filePath + "\\" + fileName);
            Path file = path.resolve(fileName);
            String pathname = file.toString() + "." + extension;
            FileInputStream files = new FileInputStream(new File("" + pathname));
            return files;
//            FileHelper.ConvertExceltoListofUsers(files);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }
    public List<TblNactaList> readColumnDataFromNACTAExcel(InputStream myInputStream, long loggedUserId) {
        try {
            XSSFWorkbook myWorkbook = new XSSFWorkbook(myInputStream);
            // Referencing the Sheet inside the excel file using it's Name
            XSSFSheet mySheet = myWorkbook.getSheetAt(0);

            List<TblNactaList> tblNactaLists = new ArrayList<>();
            int x=mySheet.getPhysicalNumberOfRows();

            for (int index = 1; index <mySheet.getPhysicalNumberOfRows(); index++) {

                XSSFRow row = mySheet.getRow(index);
                TblNactaList tblNactaList = new TblNactaList();
                int totalCell = row.getLastCellNum();


                tblNactaList.setName(getCellValue(row, 0));
                tblNactaList.setCnic(getCellValue(row, 1));
                tblNactaList.setFatherName(getCellValue(row,2));
                tblNactaList.setProvince(getCellValue(row, 3));
                tblNactaList.setDistrict(getCellValue(row, 4));
                tblNactaList.setProcessName("NACTA");
                tblNactaList.setProcessType("M");
                tblNactaList.setCreateuser(BigDecimal.valueOf(loggedUserId));


                tblNactaLists.add(tblNactaList);

            }

            return tblNactaLists;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getNumberOfRecordsInExcelFile(InputStream myInputStream) {
        try {
            XSSFWorkbook myWorkbook = new XSSFWorkbook(myInputStream);
            // Referencing the Sheet inside the excel file using it's Name
            XSSFSheet mySheet = myWorkbook.getSheetAt(0);

            return mySheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public String readAuthHeader(MessageContext messageContext) {
        SaajSoapMessage soapRequest = (SaajSoapMessage) messageContext.getRequest();
        String[] headerlist = soapRequest.getSaajMessage().getMimeHeaders().getHeader("authorization");
        String header = headerlist[0];
        return header;
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        LOG.info("point 11");
        File convFile = new File(file.getOriginalFilename());
        LOG.info("point 22");
        FileOutputStream fos = new FileOutputStream(convFile);
        LOG.info("point 33");
        fos.write(file.getBytes());
        LOG.info("point 44");
        fos.close();
        LOG.info("point 55");
        return convFile;
    }

    public HashMap<String, List<String>> readDataFromExcelFile(InputStream myInputStream) {

        // List to store the data from the Excel File
        // this object is used to get the names of the Data-Members from the Entity Class
        // this array is used to store the column names that we are reading from Excel File
        List<String> columnNames = new ArrayList<>();
        List<String> columnType = new ArrayList<>();

        try {
            XSSFWorkbook myWorkbook = new XSSFWorkbook(myInputStream);
            // Referencing the Sheet inside the excel file using it's Name
            XSSFSheet mySheet = myWorkbook.getSheetAt(0);

            int rowNumber = 0;

            Iterator<Row> rows = mySheet.iterator();

            // Row Loop ==> Will Go on till the last Row
            while (rows.hasNext()) {
                Row myRow = rows.next();

                if (rowNumber == 0) {
                    // If rowNumber == 0 it means that we are on the First line that in most case is the Header of the Columns
                    Iterator<Cell> cells = myRow.iterator();
                    while (cells.hasNext()) {
                        Cell myCell = cells.next();
                        // Reading Column Headers / Column Names from 1st Row of Excel File
                        columnNames.add(myCell.getStringCellValue());
                        columnType.add(myCell.getCellType().name());

                    }
                    rowNumber++;
                    break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Reading is Complete. Now returning the complete List with all the data.
        if (columnNames.size() > 0 && columnType.size() > 0) {
            HashMap<String, List<String>> map = new HashMap<>();

            map.put("columnName", columnNames);
            map.put("columnType", columnType);

            return map;
        } else {
            return null;
        }
    }
    public TblUserActivityLog activityLogging(LoggedUserDetail loggedUserDetail,Long activityId){
        TblUserActivityLog tblUserActivityLog=new TblUserActivityLog();
        tblUserActivityLog.setActivityDate(new Date());
        TblUser tblUser=new TblUser();
        TblActivity tblActivity=new TblActivity();
        TblUserLoginHistory tblUserLoginHistory=new TblUserLoginHistory();
        tblUser.setUserId(loggedUserDetail.getUserId());
        tblUserLoginHistory.setUserLoginHistoryId(loggedUserDetail.getLoginId());
        tblActivity.setActivityId(activityId);
        tblUserActivityLog.setTblUser(tblUser);
        tblUserActivityLog.setTblUserLoginHistory(tblUserLoginHistory);
        tblUserActivityLog.setTblActivity(tblActivity);
        tblUserActivityLog=transactionService.acticityLogging(tblUserActivityLog);
        return tblUserActivityLog;

    }

}
