package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.AddMapInSerialize;
import pojo.LocationBoom;

public class TestDataBuild {
	
public AddMapInSerialize testdata(String Address, String Name, String Language)
{
	AddMapInSerialize am =new AddMapInSerialize();
	am.setAccuracy(50);
	am.setAddress(Address);
	am.setLanguage(Language);
	am.setName(Name);
	am.setPhone_number("(+91) 983 893 3937");
	am.setWebsite("http://google.com");
	LocationBoom lb = new LocationBoom();
	lb.setLat(-38.383494);
	lb.setLng(33.427362);
	am.setLocation(lb);
	List<String> as = new ArrayList<String>();
	as.add("shoe park");
	as.add("shop");
	am.setTypes(as);
	
	return am;
}

public String deletePayload(String placeId){
	
	return "{\r\n    \"place_id\":\""+placeId+"\"   \t \r\n}";
}

}
