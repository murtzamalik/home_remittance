package org.ais.hr.transactions.controller;
/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 11/1/2021
 * Time: 12:26 AM
 * Project : agentmate
 */


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ais.hr.transactions.Service.ProcessTransactionService;
import org.ais.hr.transactions.dto.LoggedUserDetail;
import org.ais.hr.transactions.esb.*;
import org.ais.hr.transactions.model.*;
import org.ais.hr.transactions.util.JWTSecurity;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
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
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
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
    private ProcessTransactionService processTransactionService;


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

        try {

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

            // headers.setAll(headerMap);

            // HttpEntity<?> request = new HttpEntity<>(postParam, headers);

            // ResponseEntity<?> response = new RestTemplate().postForEntity(url, request,
            // String.class);

            ResponseEntity<?> response = new RestTemplate().getForEntity(url, String.class);
            String entityResponse = (String) response.getBody();
            HttpStatus entitystatus = (HttpStatus) response.getStatusCode();
            if (entitystatus.value() == 200) {
//                System.out.println(entityResponse);
                if (!entityResponse.isEmpty()) {
                    return entityResponse;
                } else {
                    return null;
                }
            } else {
                return entityResponse;
            }

        } catch (Exception e) {
            String e1 = e.getCause().getCause().getCause().getLocalizedMessage();
            if (e1.contains("ORA")) {
                e1 = e1.replace("ORA", "FMFB");
                // addMessageToFacesContext(e1);
            } else {
                // addMessageToFacesContext(e1);
            }

            return null;
        }

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
            TblAuthAccessToken tblAuthAccessToken=processTransactionService.tokenVerification(BigDecimal.valueOf(loggedUserDetail.getUserId()),header);
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

            for (int index = 1; index < mySheet.getPhysicalNumberOfRows(); index++) {

                XSSFRow row = mySheet.getRow(index);
                TblNactaList tblNactaList = new TblNactaList();
                int totalCell = row.getLastCellNum();


                tblNactaList.setName(getCellValue(row, 0));
                tblNactaList.setCnic(getCellValue(row, 1));
                tblNactaList.setProvince(getCellValue(row, 2));
                tblNactaList.setDistrict(getCellValue(row, 3));
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
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
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

    public String padLeftZeros(String inputString, int length) {

        if (inputString.contains(".")) {
            inputString.replace(".", "");
        } else {
            inputString = inputString + "00";
        }


        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }


    public String getResponseFromPostAPIwithoutHeader(Object object, String url) {

        try {


            ResponseEntity<String> response = new RestTemplate().postForEntity(url, object, String.class);
            return response.getBody();


        } catch (RestClientException e) {
            // process exception
            if (e instanceof HttpStatusCodeException) {
                String errorResponse = ((HttpStatusCodeException) e).getResponseBodyAsString();
                return errorResponse;
            }
            return e.getLocalizedMessage();
        } catch (Exception e) {
            String e5 = e.getMessage();
            return e5;
        }

    }


    public String dateLocalTransaction(Date date) {
        String month;
        if (date.getMonth() < 10) {
            month = String.valueOf("0" + date.getMonth());
        } else {
            month = String.valueOf(date.getMonth());
        }
        String date1;
        if (date.getDate() < 10) {
            date1 = String.valueOf("0" + date.getDate());
        } else {
            date1 = String.valueOf(date.getDate());
        }
        return month + date1;
    }

    public String timeLocalTransaction(Date date) {
        String hour;
        String min;
        String sec;
        if (date.getHours() < 10) {
            hour = String.valueOf("0" + date.getHours());
        } else {
            hour = String.valueOf(date.getHours());
        }
        if (date.getMinutes() < 10) {
            min = String.valueOf("0" + date.getMinutes());
        } else {
            min = String.valueOf(date.getMinutes());
        }
        if (date.getSeconds() < 10) {
            sec = String.valueOf("0" + date.getSeconds());
        } else {
            sec = String.valueOf(date.getSeconds());
        }


        return hour + min + sec;
    }



    public IftTitleFetchResponse callJSBankIFTTitleFetch(String fromAcc, String toAcc, String amnt, String stan, LoggedUserDetail loggedUserDetail) throws JSONException, JsonProcessingException {


        IftTitleFetchReq iftTitleFetchReq = new IftTitleFetchReq();


        amnt = amnt.replace(".", "");
        String amount = padLeftZeros(amnt, 12);
        Date date = new Date();
        String timeLocalTrans = timeLocalTransaction(date);
        String dateLocalTrans = dateLocalTransaction(date);


        iftTitleFetchReq.setMti("0200");
        iftTitleFetchReq.setProcessingCode_003("630000");
        iftTitleFetchReq.setPrimaryAccountNumber_002("4374590000723569");
        iftTitleFetchReq.setTransmissionDatetime_007(dateLocalTrans + timeLocalTrans);
        iftTitleFetchReq.setSystemsTraceAuditNumber_011(stan);
        iftTitleFetchReq.setTimeLocalTransaction_012(timeLocalTrans);
        iftTitleFetchReq.setDateLocalTransaction_013(dateLocalTrans);
        iftTitleFetchReq.setMerchantType_018("0097");
        iftTitleFetchReq.setCurrencyCodeTransaction_049("586");
        iftTitleFetchReq.setCurrencyCodeSettlement_050("586");
        iftTitleFetchReq.setAccountIdentification1_102(fromAcc);
        iftTitleFetchReq.setAccountIdentification2_103(toAcc);
        iftTitleFetchReq.setReservedPrivate_120("");

        TblRequest tblRequest = new TblRequest();
        tblRequest.setMti("0200");
        tblRequest.setProcessingcode003("630000");
        tblRequest.setPrimaryaccountnumber002("4374590000723569");
        tblRequest.setTransmissiondatetime007(dateLocalTrans + timeLocalTrans);
        tblRequest.setSystemstraceauditnumber011(stan);
        tblRequest.setTimelocaltransaction012(timeLocalTrans);
        tblRequest.setDatelocaltransaction013(date.toString());
        tblRequest.setMerchanttype018("0097");
        tblRequest.setCurrencycodetransaction049("586");
        tblRequest.setCurrencycodesettlement050("586");
        tblRequest.setAccountidentification1102(fromAcc);
        tblRequest.setAccountidentification2103(toAcc);
        tblRequest.setReservedprivate120("");
        tblRequest.setEndPoint("/titlefetchandibftservice/v1/Transaction");

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(iftTitleFetchReq);
            tblRequest.setRequestJson(json);
            //System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String url=env.getProperty("js.esb");
        String response = getResponseFromPostAPIwithoutHeader(iftTitleFetchReq, url+"/esb-api/iftTitleFetch");
        TblResponse tblResponse = new TblResponse();
        response.replaceAll("<200,", "");
        JSONObject json = new JSONObject(response);
        tblResponse.setResponseJson(json.toString());
        String data = json.getString("data");
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        IftTitleFetchResponse iftTitleFetchResponse = objectMapper.readValue(data, IftTitleFetchResponse.class);

        tblResponse.setMti(iftTitleFetchResponse.getmTI());
        tblResponse.setSystemstraceauditnumber011(iftTitleFetchResponse.getSystemsTraceAuditNumber011());
        tblResponse.setPrimaryaccountnumber002(iftTitleFetchResponse.getPrimaryAccountNumber002());
        tblResponse.setMerchanttype018(iftTitleFetchResponse.getMerchantType018());
        tblResponse.setResponsecode039(iftTitleFetchResponse.getResponseCode039());
        tblResponse.setAccountidentification1102(iftTitleFetchResponse.getAccountIdentification1102());
        tblResponse.setProcessingcode003(iftTitleFetchResponse.getProcessingCode003());
        tblResponse.setTimelocaltransaction012(iftTitleFetchResponse.getTimeLocalTransaction012());
        tblResponse.setDatelocaltransaction013(iftTitleFetchResponse.getDateLocalTransaction013());
        tblResponse.setCurrencycodesettlement050(iftTitleFetchResponse.getCurrencyCodeSettlement050());
        tblResponse.setTransmissiondatetime007(iftTitleFetchResponse.getTransmissionDatetime007());
        tblResponse.setAccountidentification2103(iftTitleFetchResponse.getAccountIdentification2103());
        tblResponse.setReservedprivate120(iftTitleFetchResponse.getReservedPrivate120());
        tblResponse.setCurrencycodetransaction049(iftTitleFetchResponse.getCurrencyCodeTransaction049());
        tblResponse.setMessage(iftTitleFetchResponse.getMessage());

        List<TblResponse> tblResponses = new ArrayList<>();
        tblResponses.add(tblResponse);
        tblRequest.setTblResponses(tblResponses);
        tblRequest.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblResponse.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblResponse.setMessage(json.getString("messages"));
        tblResponse.setTblRequest(tblRequest);


        if (iftTitleFetchResponse != null) {
            tblRequest = processTransactionService.saveRequest(tblRequest);
            tblResponse = processTransactionService.saveResponse(tblResponse);
            iftTitleFetchResponse.setMessage(json.getString("messages"));
            return iftTitleFetchResponse;
        } else {
            return null;
        }
    }


    //////


    public IftResponse callJSBankIFTTransfer(String fromAcc, String toAcc, String amnt, String stan, LoggedUserDetail loggedUserDetail) throws JSONException, JsonProcessingException {


        IftRequest iftRequest = new IftRequest();

        amnt = amnt.replace(".", "");
        String amount = padLeftZeros(amnt, 12);

        Date date = new Date();
        String timeLocalTrans = timeLocalTransaction(date);
        String dateLocalTrans = dateLocalTransaction(date);


        iftRequest.setMti("0200");
        iftRequest.setProcessingCode_003("400000");
        iftRequest.setAmountTransaction_004(amount);
//        iftRequest.setTransmissionDatetime_007(dateLocalTrans + timeLocalTrans);
        iftRequest.setTransmissionDatetime_007("1230213142");
        iftRequest.setSystemsTraceAuditNumber_011(stan);
//        iftRequest.setTimeLocalTransaction_012(timeLocalTrans);
//        iftRequest.setDateLocalTransaction_013(dateLocalTrans);
        iftRequest.setTimeLocalTransaction_012("030548");
        iftRequest.setDateLocalTransaction_013("0305");
        iftRequest.setMerchantType_018("0099");
        iftRequest.setCurrencyCodeTransaction_049("586");
        iftRequest.setCurrencyCodeSettlement_050("586");
        iftRequest.setAccountIdentification1_102(fromAcc);
        iftRequest.setAccountIdentification2_103(toAcc);
        iftRequest.setReservedPrivate_127("FT-MB20220305120048365888");
        //
        TblRequest tblRequest = new TblRequest();
        tblRequest.setMti("0200");
        tblRequest.setProcessingcode003("400000");
        tblRequest.setAmounttransaction004(new BigDecimal(amount));
//      tblRequest.setTransmissionDatetime_007(dateLocalTrans + timeLocalTrans);
        tblRequest.setTransmissiondatetime007("1230213142");
        tblRequest.setSystemstraceauditnumber011(stan);
//      tblRequest.setTimeLocalTransaction_012(timeLocalTrans);
//      tblRequesttblRequest.setDateLocalTransaction_013(dateLocalTrans);
        tblRequest.setTimelocaltransaction012("030548");
        tblRequest.setDatelocaltransaction013("0305");
        tblRequest.setMerchanttype018("0099");
        tblRequest.setCurrencycodetransaction049("586");
        tblRequest.setCurrencycodesettlement050("586");
        tblRequest.setAccountidentification1102(fromAcc);
        tblRequest.setAccountidentification2103(toAcc);
        tblRequest.setReservedprivate127("FT-MB20220305120048365888");
        tblRequest.setEndPoint("/titlefetchandibftservice/v1/Transaction");

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(iftRequest);
            tblRequest.setRequestJson(json);
            //System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url=env.getProperty("js.esb");

        String response = getResponseFromPostAPIwithoutHeader(iftRequest, url+"/esb-api/ift");
        if (response != null) {
            TblResponse tblResponse = new TblResponse();
            JSONObject json = new JSONObject(response);
            tblResponse.setResponseJson(json.toString());
            String data = json.getString("data");
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            IftResponse iftResponse = objectMapper.readValue(data, IftResponse.class);

            tblResponse.setMti(tblResponse.getMti());
            tblResponse.setSystemstraceauditnumber011(tblResponse.getSystemstraceauditnumber011());
            tblResponse.setMerchanttype018(tblResponse.getMerchanttype018());
            tblResponse.setResponsecode039(tblResponse.getResponsecode());
            tblResponse.setAccountidentification1102(tblResponse.getAccountidentification1102());
            tblResponse.setProcessingcode003(tblResponse.getProcessingcode003());
            tblResponse.setTimelocaltransaction012(tblResponse.getTimelocaltransaction012());
            tblResponse.setAdditionalresponsedata044(tblResponse.getAdditionalresponsedata044());
            tblResponse.setDatelocaltransaction013(tblResponse.getDatelocaltransaction013());
            tblResponse.setCurrencycodesettlement050(tblResponse.getCurrencycodesettlement050());
            tblResponse.setTransmissiondatetime007(tblResponse.getTransmissiondatetime007());
            tblResponse.setAccountidentification2103(tblResponse.getAccountidentification2103());
            tblResponse.setAmounttransaction004(tblResponse.getAmounttransaction004());
            tblResponse.setCurrencycodetransaction049(tblResponse.getCurrencycodetransaction049());

            List<TblResponse> tblResponses = new ArrayList<>();
            tblResponses.add(tblResponse);
            tblRequest.setTblResponses(tblResponses);
            tblRequest.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblResponse.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblResponse.setMessage(json.getString("messages"));
            tblResponse.setTblRequest(tblRequest);

            if (iftResponse != null) {
                iftResponse.setMessage(json.getString("messages"));
                tblRequest = processTransactionService.saveRequest(tblRequest);
                tblResponse = processTransactionService.saveResponse(tblResponse);
                return iftResponse;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public IbftTitleFetchResponse callJSBankIBFTTitleFetch(String fromAcc, String toAcc, String userId, String cnic, String bankCode, String stan, LoggedUserDetail loggedUserDetail) throws JSONException, JsonProcessingException {


        IbftTitleFetchReq ibftTitleFetchReq = new IbftTitleFetchReq();


        Date date = new Date();
        String timeLocalTrans = timeLocalTransaction(date);
        String dateLocalTrans = dateLocalTransaction(date);


        ibftTitleFetchReq.setMti("0200");
        ibftTitleFetchReq.setPrimaryAccountNumber_002("4220171508989");
        ibftTitleFetchReq.setProcessingCode_003("620000");
        ibftTitleFetchReq.setTransmissionDatetime_007("0228070501");
        ibftTitleFetchReq.setSystemsTraceAuditNumber_011(stan);
        ibftTitleFetchReq.setTimeLocalTransaction_012("070501");
        ibftTitleFetchReq.setDateLocalTransaction_013("1111");
        ibftTitleFetchReq.setMerchantType_018("6041");
        ibftTitleFetchReq.setBankCode(bankCode);
        ibftTitleFetchReq.setAcquiringInstitutionIdentificationCode_032(bankCode);
        ibftTitleFetchReq.setTransactionDescription_104("TitleFetch- APIGEE");
        ibftTitleFetchReq.setUserId(userId);
        ibftTitleFetchReq.setCnic(cnic);
        ibftTitleFetchReq.setAccountIdentification1_102(fromAcc);
        ibftTitleFetchReq.setAccountIdentification2_103(toAcc);
        ibftTitleFetchReq.setReservedPrivate_120("");

        TblRequest tblRequest = new TblRequest();

        tblRequest.setMti("0200");
        tblRequest.setPrimaryaccountnumber002("4220171508989");
        tblRequest.setProcessingcode003("620000");
        tblRequest.setTransmissiondatetime007("0228070501");
        tblRequest.setSystemstraceauditnumber011(stan);
        tblRequest.setTimelocaltransaction012("070501");
        tblRequest.setDatelocaltransaction013("1111");
        tblRequest.setMerchanttype018("6041");
        tblRequest.setBankname(bankCode);

        tblRequest.setAcquiringinstitutionidentificationcode032(bankCode);
        tblRequest.setTransactiondescription104("TitleFetch- APIGEE");
        tblRequest.setUserid(userId);
        tblRequest.setCnic(cnic);
        tblRequest.setAccountidentification1102(fromAcc);
        tblRequest.setAccountidentification2103(toAcc);
        tblRequest.setReservedprivate120("");
        tblRequest.setEndPoint("/ibftservices/v1/ibfttitlefetch");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(ibftTitleFetchReq);
            tblRequest.setRequestJson(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url=env.getProperty("js.esb");

        String response = getResponseFromPostAPIwithoutHeader(ibftTitleFetchReq, url+"/esb-api/ibftTitleFetch");
        TblResponse tblResponse = new TblResponse();
        JSONObject json = new JSONObject(response);
        tblResponse.setResponseJson(json.toString());
        String data = json.getString("data");
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        IbftTitleFetchResponse ibftTitleFetchResponse = objectMapper.readValue(data, IbftTitleFetchResponse.class);
        ibftTitleFetchResponse.setMessage(json.getString("messages"));


        tblResponse.setMti(ibftTitleFetchResponse.getmTI());
        tblResponse.setSystemstraceauditnumber011(ibftTitleFetchResponse.getSystemsTraceAuditNumber_011());
        tblResponse.setPrimaryaccountnumber002(ibftTitleFetchResponse.getPrimaryAccountNumber_002());
        tblResponse.setMerchanttype018(ibftTitleFetchResponse.getMerchantType_018());
        tblResponse.setBankcode(ibftTitleFetchResponse.getBankCode());
        tblResponse.setResponsemessage(ibftTitleFetchResponse.getResponseMessage());
        tblResponse.setResponsecode039(ibftTitleFetchResponse.getResponseCode_039());
        tblResponse.setAccountidentification1102(ibftTitleFetchResponse.getAccountIdentification1_102());
        tblResponse.setProcessingcode003(ibftTitleFetchResponse.getProcessingCode_003());
        tblResponse.setTimelocaltransaction012(ibftTitleFetchResponse.getTimeLocalTransaction_012());
        tblResponse.setDatelocaltransaction013(ibftTitleFetchResponse.getDateLocalTransaction_013());
        tblResponse.setUserid(ibftTitleFetchResponse.getUserId());
        tblResponse.setTransmissiondatetime007(ibftTitleFetchResponse.getTransmissionDatetime_007());
        tblResponse.setAccountidentification2103(ibftTitleFetchResponse.getAccountIdentification2_103());
        tblResponse.setTransactiondescription104(ibftTitleFetchResponse.getTransactionDescription_104());
        tblResponse.setCnic(ibftTitleFetchResponse.getcNIC());
        tblResponse.setAcquirunginstitutionidentificationcode032(ibftTitleFetchResponse.getAcquiringInstitutionIdentificationCode_032());
        tblResponse.setReservedprivate120(ibftTitleFetchResponse.getReservedPrivate_120());
        tblResponse.setToaccounttitle(ibftTitleFetchResponse.getToAccountTitle());
        tblResponse.setBranchname(ibftTitleFetchResponse.getBranchName());

        List<TblResponse> tblResponses = new ArrayList<>();
        tblResponses.add(tblResponse);
        tblRequest.setTblResponses(tblResponses);
        tblRequest.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblResponse.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblResponse.setMessage(json.getString("messages"));
        tblResponse.setTblRequest(tblRequest);

        if (ibftTitleFetchResponse != null) {
            tblRequest = processTransactionService.saveRequest(tblRequest);
            tblResponse = processTransactionService.saveResponse(tblResponse);
            return ibftTitleFetchResponse;
        } else {
            return null;
        }
    }

    public IbftPaymentResponse callJSBankIBFTPayment(String fromAcc, String toAcc, String amount, String userId, String cnic, String accTitle, String bankCode, String stan, String branchName, String bankName, String reasonCode, LoggedUserDetail loggedUserDetail) throws JSONException, JsonProcessingException {


        IbftPaymentRequest ibftPaymentRequest = new IbftPaymentRequest();


        amount = amount.replace(".", "");
        amount = padLeftZeros(amount, 12);
        Date date = new Date();
        String timeLocalTrans = timeLocalTransaction(date);
        String dateLocalTrans = dateLocalTransaction(date);


        ibftPaymentRequest.setMti("0200");
        ibftPaymentRequest.setPrimaryAccountNumber_002("4220188556725626");
        ibftPaymentRequest.setProcessingCode_003("410000");
        ibftPaymentRequest.setAmountTransaction_004(amount);
        ibftPaymentRequest.setTransmissionDatetime_007("0305213142");
        ibftPaymentRequest.setSystemsTraceAuditNumber_011(stan);
        ibftPaymentRequest.setTimeLocalTransaction_012("095257");
        ibftPaymentRequest.setDateLocalTransaction_013("0305");
        ibftPaymentRequest.setMerchantType_018("6041");
        ibftPaymentRequest.setAmountTransactionFee_028("000000000000");
        ibftPaymentRequest.setAcquiringInstitutionIdentificationCode_032(bankCode);
        ibftPaymentRequest.setPersonalIdentificationNumberData_052("");
        ibftPaymentRequest.setAccountIdentification1_102(fromAcc);
        ibftPaymentRequest.setAccountIdentification2_103(toAcc);
        ibftPaymentRequest.setTransactionDescription_104("MB-GA-BEN.20220606215257585358");
        ibftPaymentRequest.setCnic(cnic);
        ibftPaymentRequest.setUserId(userId);
        ibftPaymentRequest.setBankName(bankName);
        ibftPaymentRequest.setBranchName(branchName);
        ibftPaymentRequest.setConsumerTitle(accTitle);
        ibftPaymentRequest.setReasonCode(reasonCode);

        TblRequest tblRequest = new TblRequest();
        tblRequest.setMti("0200");
        tblRequest.setPrimaryaccountnumber002("4220188556725626");
        tblRequest.setProcessingcode003("410000");
        tblRequest.setAmounttransaction004(new BigDecimal(amount));
        tblRequest.setTransmissiondatetime007("0305213142");
        tblRequest.setSystemstraceauditnumber011(stan);
        tblRequest.setTimelocaltransaction012("095257");
        tblRequest.setDatelocaltransaction013("0305");
        tblRequest.setMerchanttype018("6041");
        tblRequest.setAmounttransactionfee028(new BigDecimal("000000000000"));
        tblRequest.setAcquiringinstitutionidentificationcode032(bankCode);
        tblRequest.setPersonalidentificationnumberdata052("");
        tblRequest.setAccountidentification1102(fromAcc);
        tblRequest.setAccountidentification2103(toAcc);
        tblRequest.setTransactiondescription104("MB-GA-BEN.20220606215257585358");
        tblRequest.setCnic(cnic);
        tblRequest.setUserid(userId);
        tblRequest.setBankname(bankName);
        tblRequest.setBranchname(branchName);
        tblRequest.setConsumertitle(accTitle);
        tblRequest.setReasoncode(reasonCode);
        tblRequest.setEndPoint("/ibftservices/v1/rdvibft");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(ibftPaymentRequest);
            tblRequest.setRequestJson(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String url=env.getProperty("js.esb");

        String response = getResponseFromPostAPIwithoutHeader(ibftPaymentRequest, url+"/esb-api/ibftPayment");
        TblResponse tblResponse = new TblResponse();
        JSONObject json = new JSONObject(response);
        tblResponse.setResponseJson(json.toString());
        String data = json.getString("data");
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        IbftPaymentResponse ibftPaymentResponse = objectMapper.readValue(data, IbftPaymentResponse.class);
        ibftPaymentResponse.setMessage(json.getString("messages"));


        tblResponse.setMti(ibftPaymentResponse.getMti());
        tblResponse.setSystemstraceauditnumber011(ibftPaymentResponse.getSystemsTraceAuditNumber_011());
        tblResponse.setPrimaryaccountnumber002(ibftPaymentResponse.getPrimaryAccountNumber_002());
        tblResponse.setMerchanttype018(ibftPaymentResponse.getMerchantType_018());
        tblResponse.setResponsecode039(ibftPaymentResponse.getResponseCode_039());
        tblResponse.setProcessingcode003(ibftPaymentResponse.getProcessingCode_003());
        tblResponse.setTimelocaltransaction012(ibftPaymentResponse.getTimeLocalTransaction_012());
        tblResponse.setDatelocaltransaction013(ibftPaymentResponse.getDateLocalTransaction_013());
        tblResponse.setUserid(ibftPaymentResponse.getUserId());
        tblResponse.setTransmissiondatetime007(ibftPaymentResponse.getTransmissionDatetime_007());
        tblResponse.setCnic(ibftPaymentResponse.getCnic());
        tblResponse.setRelationshipno(ibftPaymentResponse.getRelationshipNo());
        tblResponse.setMessage(ibftPaymentResponse.getMessage());
//        tblResponse.setBankName(ibftPaymentResponse.getBankName());

        List<TblResponse> tblResponses = new ArrayList<>();
        tblResponses.add(tblResponse);
        tblRequest.setTblResponses(tblResponses);
        tblRequest.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblResponse.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblResponse.setTblRequest(tblRequest);

        if (ibftPaymentResponse != null) {

            return ibftPaymentResponse;
        } else {
            return null;
        }
    }

    String convertFiletoBase64(String path, String fileName) {
        File fi = new File(path + fileName + ".txt");
        if (fi.isFile()) {
            try {
                byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(fi));
                String encodedString = new String(encoded);

                return encodedString;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public TblUserActivityLog activityLogging(LoggedUserDetail loggedUserDetail, Long activityId){
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
        tblUserActivityLog=processTransactionService.activityLogging(tblUserActivityLog);
        return tblUserActivityLog;

    }


}
