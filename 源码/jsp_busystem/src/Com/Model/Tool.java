package Com.Model;

import java.io.UnsupportedEncodingException;

public class Tool {
	
	public  String getNewString(String input)
	
	{
		try {
			input =new String(input.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return input;
	}

}
