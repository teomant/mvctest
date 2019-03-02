
package org.teomant.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.teomant.server.GetDateTimeRequest;
import org.teomant.server.GetDateTimeResponse;

public class DateTimeClient extends WebServiceGatewaySupport {

	public GetDateTimeResponse getDateTime() {

		GetDateTimeRequest request = new GetDateTimeRequest();

		GetDateTimeResponse response = (GetDateTimeResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/server/dateTime", request,
						new SoapActionCallback(
								"http://teomant.org/server/getDateTimeRequest"));

		return response;
	}

}
