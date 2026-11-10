package org.ais.hr.fileupload.SoapApi.endpoints;



import org.ais.hr.fileupload.SoapApi.dto.Response;
import org.ais.hr.fileupload.SoapApi.dto.TransactionRequest;
import org.ais.hr.fileupload.SoapApi.service.SoapService;
import org.ais.hr.fileupload.controller.AbstractApi;

import org.ais.hr.fileupload.dto.LoggedUserDetail;
import org.ais.hr.fileupload.model.TblTransHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;

@Endpoint()
public class ApiEndPoints extends AbstractApi {
   Logger LOG = LoggerFactory.getLogger(ApiEndPoints.class);

   public static final String NAMESPACE = "http://www.src.com/main/java/org/ais/hr/fileupload/SoapApi/dto";

   @Autowired
   SoapService soapService;

   @PayloadRoot(namespace = NAMESPACE, localPart = "TransactionRequest")
   @ResponsePayload()

   public JAXBElement<Response> checkSoap(@RequestPayload JAXBElement<TransactionRequest> request, MessageContext context) throws SOAPException {
      String className = this.getClass().getSimpleName();
      String methodName = new Object() {
      }.getClass().getEnclosingMethod().getName();

      try {
         LOG.info("\n\n\nINSIDE \n CLASS == " + className + " \n METHOD == " + methodName);
         LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(readAuthHeader(context));
         if (loggedUserDetail != null) {
            TblTransHead tblTransHead = soapService.transactionupload(request, loggedUserDetail);
            if (tblTransHead != null && tblTransHead.getTransHeadId() > 0) {
               LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
               Response response=new Response();
               response.setTransHeadId(tblTransHead.getTransHeadId());
               response.setApiType(tblTransHead.getApiType());
               response.setStatus(tblTransHead.getStatus());
               response.setRemitterid(tblTransHead.getTblRemitter().getRemitterId());
               JAXBElement<Response> element = new JAXBElement<>(new QName("Response"),
                       Response.class, response);
               return element;
            } else {
               LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
               Response response=null;
               JAXBElement<Response> element = new JAXBElement<>(new QName("Response"),
                       Response.class, response);
               return element;
            }
         }

      } catch (Exception e) {
         LOG.info("\n EXITING THIS METHOD == " + methodName + " OF CLASS = " + className + " \n\n\n");
         Response response=null;
         JAXBElement<Response> element = new JAXBElement<>(new QName("Response"),
                 Response.class, response);
         return element;
      }
      return null;
   }
}

