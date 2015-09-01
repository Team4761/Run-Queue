package org.robockets.runqueue.server;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {
	User x = new User("XXX");
	User y = new User("YYY");
	User z = new User("ZZZ");
	
	User[] users = {x, y, z};
	
	/**
	 * Adds the test users to the queue.
	 */
	@Before
	public void addTestUsersToQueue() {
		for(User u : users) {
			Queue.add(u, Priority.NORMAL);
		}
	}
	
	/**
	 * Does getAllInQueue() return an accurate list of users in the queue?
	 */
	@Test
	public void testGetUsersInQueue() {
		ArrayList<User> testArrayList = new ArrayList<User>();
		for(User u : users) {
			testArrayList.add(u);
		}
		assertEquals(testArrayList, Queue.getAllInQueue());
		testArrayList.remove(x);
		x.leaveQueue();
		assertEquals(testArrayList, Queue.getAllInQueue());
	}
	
	/*
	@Test
	public void testNextMethod() {
		//TODO: Add test for next()
	}
	*/
	
	/**
	 * Does the clear method remove every user in the queue?
	 */
	@Test
	public void testClearMethod() {
		for(User u : users) {
			Queue.add(u, Priority.NORMAL);
		}
		Queue.clear();
		assertEquals(new ArrayList<User>(), Queue.getAllInQueue());
	}
	
	//TODO: Test priority in queue
}
