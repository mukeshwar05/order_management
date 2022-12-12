package com.assignment.order_management.utils;

/**
 * @author mukeshwar.s
 */
public class Constants {

	// Hikari connection
	public static final String CACHEPREPSTMTS = "true";
	public static final String PREPSTMTCACHESIZE = "500";
	public static final String PREPSTMTCACHESQLLIMIT = "2048";
	public static final int MAXPOOLSIZE = 1;

	// Tenant for db
	public static final String TENANT_ID = "tenentId";

	// Type of Api
	public static final String TYPE_QUERY = "query";
	public static final String TYPE_STATEMENT = "statement";

	// Datatype of Argument
	public static final String DATATYPE_INTEGER = "integer";
	public static final String DATATYPE_STRING = "string";
	public static final String DATATYPE_TIMESTAMP = "timestamp";

	// Api Name
	public static final String CREATE_ORDER_API = "createOrder";
	public static final String READ_USER_COUNT_API = "readUserCount";
	public static final String READ_USER_ID_API = "readUserIdList";

	// Column Name
	public static final String COLUMN_USER_COUNT = "userCount";
	public static final String COLUMN_USER_ID = "user_id";

}
