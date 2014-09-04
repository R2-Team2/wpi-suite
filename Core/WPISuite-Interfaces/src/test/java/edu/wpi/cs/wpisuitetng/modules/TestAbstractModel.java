package edu.wpi.cs.wpisuitetng.modules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Permission;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class TestAbstractModel {

	AbstractModel model = new AbstractModel() {
		@Override
		public String toJson() {return null;}
		
		@Override
		public void save() {}
		
		@Override
		public Boolean identify(Object o) {return null;}
		
		@Override
		public void delete() {}
	};
	
	@Test
	public void testSetAndGetPermission(){
		User user = new User("Test User", "test", "password", 1);
		Permission permission = Permission.WRITE;
		
		model.setPermission(permission, user);
		
		assertEquals(permission, model.getPermission(user));
	}

	@Test
	public void testSetAndGetProject() {
		Project project = new Project("Test Project", 1);

		model.setProject(project);
		assertEquals(project, model.getProject());
	}
}
