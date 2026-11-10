package org.ais.hr.fi.controller;
/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 11/1/2021
 * Time: 12:26 AM
 * Project : agentmate
 */



import org.ais.hr.fi.Service.FinInstService;
import org.ais.hr.fi.dto.LoggedUserDetail;
import org.ais.hr.fi.model.*;
import org.ais.hr.fi.util.JWTSecurity;
import org.apache.commons.codec.digest.DigestUtils;
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

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class AbstractApi {

    Logger LOG = LoggerFactory.getLogger(AbstractApi.class);

//	public static final Map<String, TblCustomerAll> LOGGED_IN_CUSTOMERALL_MAP = new HashMap<String, TblCustomerAll>();


    @Autowired
    private Environment env;

    @Autowired
    private FinInstService finInstService;
//    @Autowired
//    DataSource dataSource;


    private static final String[] IP_HEADER_CANDIDATES = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};


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
                System.out.println(entityResponse);
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

    public String getJsBankDateTimeFormatForPresent(){
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
            TblAuthAccessToken tblAuthAccessToken=finInstService.tokenVerification(BigDecimal.valueOf(loggedUserDetail.getUserId()),header);
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
        tblUserActivityLog=finInstService.acticityLogging(tblUserActivityLog);
        return tblUserActivityLog;

    }
}
