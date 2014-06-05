package com.deppon.crm.test.client.wsdl;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("message")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = { "content" })
public class RendezvousMessage {
	@XStreamAlias("type")
	private int messageType;

	private String content;

	@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = {
			"yes", "no" })
	private boolean important;


	public RendezvousMessage(int messageType, boolean important, String content) {
		this.messageType = messageType;
		this.important = important;
		this.content = content;
	}
}
