package org.teomant.endpoint;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.teomant.server.GetDateTimeRequest;
import org.teomant.server.GetDateTimeResponse;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

@Endpoint
public class DateTimeEndpoint {
	private static final String NAMESPACE_URI = "http://teomant.org/server";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDateTimeRequest")
	@ResponsePayload
	public GetDateTimeResponse getDateTimeResponse(@RequestPayload GetDateTimeRequest request) {
		GetDateTimeResponse response = new GetDateTimeResponse();
        GregorianCalendar gcal = new GregorianCalendar();
        XMLGregorianCalendar xgcal = new XMLGregorianCalendarImpl();
        try {
            xgcal = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(gcal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        response.setDate(xgcal);

		return response;
	}
}
