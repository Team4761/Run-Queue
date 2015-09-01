package org.robockets.runqueue.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User testUser;
	private String name = "Bob";
	
	/**
	 * Can new instances of the User object be created?
	 */
	@Before
	@Test
	public void testCanCreateUserInstance() {
		testUser = new User(name);
	}

	/**
	 * Do the getters and setters for the name field work?
	 */
	@Test
	public void testNameMethodsWork() {
		assertEquals("User's name has somehow changed", testUser.getName(), name);
		String newName = "Fred";
		testUser.setName(newName);
		assertEquals("User's name has not been correctly updated", testUser.getName(), newName);
	}
	
	/**
	 * Does the class accurately report if the user is in the queue or not?
	 */
	@Test
	public void testIsInQueueMethod() {
		assertFalse("User is somehow in the queue before joining", testUser.isInQueue());
		testUser.joinQueue(Priority.NORMAL);
		assertTrue("User is somehow not in the queue after joining", testUser.isInQueue());
		testUser.leaveQueue();
		assertFalse("User is somehow still in the queue after leaving", testUser.isInQueue());
	}
	
	/**
	 * Does the getter for the priority field work?
	 */
	@Test
	public void testGetPriorityWorks() {
		testUser.joinQueue(Priority.NORMAL);
		assertEquals("User priority is not Priority.NORMAL", testUser.getPriority(), Priority.NORMAL);
		testUser.leaveQueue();
	}
	
	/**
	 * Does the getter for the time joined return an instance of {@link java.util.Calendar}?
	 * This is kind of useless right now but it is the space to put tests for
	 * time joined in the future.
	 */
	@Test
	public void testGetTimeJoinedWorks() {
		testUser.joinQueue(Priority.NORMAL);
		assert(testUser.getTimeJoined() instanceof Calendar);
		testUser.leaveQueue();
	}
	
	/**
	 * Can the user join and leave the queue without any trouble?
	 */
	@Test
	public void testQueueMethodsWork() {
		testUser.joinQueue(Priority.NORMAL);
		testUser.leaveQueue();
	}
}
