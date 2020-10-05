package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	

@Before("@DeletePlace")
public void beforeScenario() throws IOException
{
	StepDefinition  st = new StepDefinition();
	st.add_place_payload("3rd street", "Bingi", "Bingology");
	st.user_call_with_http_request("AddPlaceAPI", "POST");
	st.verify_place_Id_created_maps_to_using("Bingi", "GetPlaceAPI");
}
}
