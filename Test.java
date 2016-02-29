import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Test {
	public static void main(String[] args) throws Exception {
//			String address = InetAddress.getLocalHost().getHostAddress();
//			System.out.println(address);
//		Enumeration<NetworkInterface> e=NetworkInterface.getNetworkInterfaces();
//        while(e.hasMoreElements())
//        {
//        		String str1 = e.nextElement().toString();
//        		String str2 = new String(str1.getBytes("UNICODE"),"utf-8");
//            System.out.println(str1);
//        }
//		 java.net.InetAddress   test   =   java.net.InetAddress.getByName("localhost")   ;
//		 System.out.println(test.getLocalHost().getHostAddress());
		
		
		
	    Enumeration allNetInterfaces = null;  
        try {  
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();  
        } catch (java.net.SocketException e) {  
            e.printStackTrace();  
        }  
        InetAddress ip = null;  
        while (allNetInterfaces.hasMoreElements())  
        {  
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces  
                    .nextElement();  
            System.out.println(netInterface.getName());  
            Enumeration addresses = netInterface.getInetAddresses();  
            while (addresses.hasMoreElements())  
            {  
                ip = (InetAddress) addresses.nextElement();  
                if (ip != null && ip instanceof Inet4Address)  
                {  
                    System.out.println("/u672c/u673a/u7684IP = "  
                            + ip.getHostAddress());  
                }  
            }  
        }  
		
	}
}
