package com.deppon.crm.module.customer.server.utils;


public class Assert {
	/**
	 * Assert that an object is <code>null</code> .
	 * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is not <code>null</code>
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * 
	 * <p>
	 * Assert that an object is not <code>empty</code><br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-1
	 * @param object object the object to check
	 * @param message message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is <code>empty</code>
	 */
	public static void notEmpty(Object object, String message) {
		if (ValidateUtil.objectIsEmpty(object)){
			throw new IllegalArgumentException(message);
		}
	}
}
