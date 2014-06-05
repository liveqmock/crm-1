package com.deppon.crm.module.client.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.clustering.SequentialStrategy;
import org.apache.cxf.message.Exchange;

import com.deppon.crm.module.client.common.domain.InterfaceAddressConfig;

/**
 * @作者：罗典
 * @描述：接口地址负载均衡
 * @时间：2012-10-19
 * */
public class AddressStrategy extends SequentialStrategy {
	private InterfaceAddressConfigProvider provider;

	@Override
	public List<String> getAlternateAddresses(Exchange exchange) {
		if (exchange == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String clientAddress = exchange.getEndpoint().getEndpointInfo()
				.getAddress();
		if (clientAddress.startsWith("http://")) {
			list.add(clientAddress);
		} else {
			InterfaceAddressConfig config = provider.getAddressMap(clientAddress);
			list.add(config.getAddress());
		}
		return new ArrayList<String>(list);
	}

	public InterfaceAddressConfigProvider getProvider() {
		return provider;
	}

	public void setProvider(InterfaceAddressConfigProvider provider) {
		this.provider = provider;
	}

}
