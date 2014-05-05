package nl.ipo.cds.nagios;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

public class NagiosStatusClient {

	public static void main (String[] args) {
		if(args.length != 1) {
			throw new IllegalArgumentException("RMI service URL expected as single argument");
		}
		
		RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
		factory.setServiceUrl(args[0]);
		factory.setServiceInterface(NagiosStatusService.class);
		factory.setLookupStubOnStartup(false);
		factory.setRefreshStubOnConnectFailure(true);
		factory.afterPropertiesSet();
		
		final NagiosStatusService nagiosStatusService = (NagiosStatusService)factory.getObject();
		
		System.out.println (nagiosStatusService.getAvailableHosts ());
		System.out.println (nagiosStatusService.getAvailableServices ());
	}
}
