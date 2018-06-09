package task;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class siteTest {
	private static site site = new site();
	
	@Test
	public void should_return_invalid_when_booking_command_has_wrong_format() {
		String command="abcdefghilmnopq12345678";
		assertEquals("the booking is invalid!", site.read(command));
	}
	
	@Test
	public void should_return_invalid_when_booking_time_is_same(){
		String command="U001 2016-06-02 22:00~22:00 A";
		assertEquals("the booking is invalid!", site.read(command));
	}
	
	@Test
	public void should_return_invalid_when_book_time_is_not_integral(){
		String command="U001 2016-06-02 20:15~22:00 A";
		assertEquals("the booking is invalid!", site.read(command));
	}
	
	@Test
	public void should_return_invalid_when_unbooking_time_is_same(){
		String command="U001 2016-06-02 22:00~22:00 A C";
		assertEquals("the booking is invalid!", site.read(command));
	}
	
	@Test
	public void should_return_invalid_when_unbook_time_is_not_integral(){
		String command="U001 2016-06-02 20:15~22:00 A C";
		assertEquals("the booking is invalid!", site.read(command));
	}
	
	@Test
	public void should_return_invalid_when_cancle_flag_is_not_C(){
		String command="U001 2016-06-02 20:00~22:00 A G";
		assertEquals("the booking is invalid!", site.read(command));
	}
	
	@Test
	public void all_time_of_a_new_site_should_be_available() throws ParseException{
		MySite A = new MySite("A");
		
		Stadium stadium = new Stadium();
		stadium.addSite(A);
		//stadium.addFeeStandard(DayType.WORKDAY,"20:00~22:00", 60);
		//A.setBookedInfo("U002","2017-08-01","20:00~22:00");
		//stadium.book("U002","2017-08-01","20:00~22:00", "A");
		assertTrue(A.isAvailable("2017-08-01","18:00~19:00"));
		assertTrue(A.isAvailable("2017-08-01","20:00~22:00"));
	}
	
	@Test
	public void should_return_accept_if_book_success() throws ParseException{
		MySite A = new MySite("A");
		Stadium stadium = new Stadium();
		stadium.addSite(A);
		assertEquals("the booking is accepted!", stadium.book("U002","2017-08-01","17:00~19:00", "A"));
		assertEquals("the booking conflicts with existing bokings!", stadium.book("U002","2017-08-01","17:00~19:00", "A"));
	}
	
	@Test
	public void should_return_accept_if_cancle_success() throws ParseException{
		MySite A = new MySite("A");
		Stadium stadium = new Stadium();
		stadium.addSite(A);
		assertEquals("the booking is accepted!", stadium.book("U002","2017-08-01","17:00~19:00", "A"));
		assertEquals("the booking is accepted!", stadium.cancel("U002","2017-08-01","17:00~19:00", "A"));
		assertEquals("the booking being cancelled doesn't exist!", stadium.cancel("U002","2017-08-01","17:00~19:00", "A"));
	}
	
	@Test
	public void should_return_does_not_exist_if_no_book() throws ParseException{
		MySite A = new MySite("A");
		Stadium stadium = new Stadium();
		stadium.addSite(A);
		assertEquals("the booking being cancelled doesn't exist!", stadium.cancel("U002","2017-08-01","17:00~19:00", "A"));
	}
	
	@Test
	public void should_return_canel_fail_if_userB_cancel_book_of_userA() throws ParseException{
		//given
		Stadium stadium = new Stadium();
		MySite A = new MySite("A");
		stadium.addSite(A);
		stadium.book("U002","2017-08-01","17:00~19:00", "A");
		
		//when
		String result = stadium.cancel("U001","2017-08-01","17:00~19:00", "A");
		
		//then
		assertEquals("cancel faied!", result);
	}
}
