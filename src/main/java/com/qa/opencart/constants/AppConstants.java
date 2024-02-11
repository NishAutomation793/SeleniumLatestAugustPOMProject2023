package com.qa.opencart.constants;
import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final int SHORT_DEFAULT_WAIT=5;
	public static final int MEDIUM_DEFAULT_WAIT=10;
	public static final int LONG_DEFAULT_WAIT=20;
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	
	public static final String ACC_PAGE_TITLE="My Account";
	
	public static final int POLLING_DEFAULT_WAIT=2;
	
	public static final String LOGIN_PAGE_URL_FRACTION="route=account/login";
	
	public static final String ACC_PAGE_URL_FRACTION="route=account/account";
	public static final String ORDER_HISTORY_URL_FRACTION="route=account/order";
	
	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST=Arrays.asList("My Account","My Orders", "My Affiliate Account","Newsletter");
	
	public static final String USER_REGISTER_SUCCESS_MESSAGE="Your Account Has Been Created!";
	public static final String USER_REG_SHEET_NAME = "RegistrationData";
	public static final String ADDRESS_DATA_SHEET_NAME = "AddNewAddressData";
	public static final String PRODUCT_DATA_SHEET_NAME = "ProductData";
	public static final String FORGET_PWD_HEADER_NAME = "Forgot Your Password?";
	public static final String FORGET_PWD_SUCCESS_MESSAGE = "An email with a confirmation link has been sent your email address.";
	
	public static final String ADD_NEW_ADDRESS_SUCCESS_MESSAGE="Your address has been successfully added";

	public static final String ADD_CART_SUCCESS_MESSAGE ="Success: You have added to your shopping cart!";
	
	public static final String SUCCESS_ORDER_PLACED_MESSAGE="Your order has been placed!";
	

	
	
}
