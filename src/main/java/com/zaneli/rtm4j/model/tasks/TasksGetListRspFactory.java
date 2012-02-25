package com.zaneli.rtm4j.model.tasks;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.zaneli.rtm4j.model.RspFactory;

public class TasksGetListRspFactory extends RspFactory<TasksGetListRsp> {

	private static TasksGetListRspFactory factory;

	private TasksGetListRspFactory() {
		super(TasksGetListRsp.class);
		Digester digester = getDigester();
		digester.addSetProperties("rsp/tasks", "rev", "rev");
		
		digester.addObjectCreate("rsp/tasks/list", List.class);
		digester.addSetNext("rsp/tasks/list", "addList");
		digester.addSetProperties("rsp/tasks/list", "id", "id");
		
		digester.addObjectCreate("rsp/tasks/list/taskseries", Taskseries.class);
		digester.addSetNext("rsp/tasks/list/taskseries", "addTaskseries");
		digester.addSetProperties("rsp/tasks/list/taskseries", "id", "id");
		digester.addSetProperties("rsp/tasks/list/taskseries", "created", "created");
		digester.addSetProperties("rsp/tasks/list/taskseries", "modified", "modified");
		digester.addSetProperties("rsp/tasks/list/taskseries", "name", "name");
		digester.addSetProperties("rsp/tasks/list/taskseries", "source", "source");
		digester.addSetProperties("rsp/tasks/list/taskseries", "url", "url");
		digester.addSetProperties("rsp/tasks/list/taskseries", "location_id", "locationId");
		
		digester.addObjectCreate("rsp/tasks/list/taskseries/tags/tag", Tag.class);
		digester.addSetNext("rsp/tasks/list/taskseries/tags/tag", "addTag");
		digester.addBeanPropertySetter("rsp/tasks/list/taskseries/tags/tag", "value");
		
		digester.addObjectCreate("rsp/tasks/list/taskseries/notes/note", Note.class);
		digester.addSetNext("rsp/tasks/list/taskseries/notes/note", "addNote");
		digester.addSetProperties("rsp/tasks/list/taskseries/notes/note", "id", "id");
		digester.addSetProperties("rsp/tasks/list/taskseries/notes/note", "created", "created");
		digester.addSetProperties("rsp/tasks/list/taskseries/notes/note", "modified", "modified");
		digester.addSetProperties("rsp/tasks/list/taskseries/notes/note", "title", "title");
		digester.addSetProperties("rsp/tasks/list/taskseries/notes/note", "id", "id");
		digester.addBeanPropertySetter("rsp/tasks/list/taskseries/notes/note", "content");
		
		digester.addObjectCreate("rsp/tasks/list/taskseries/task", Task.class);
		digester.addSetNext("rsp/tasks/list/taskseries/task", "setTask");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "id", "id");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "due", "due");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "has_due_time", "hasDueTime");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "added", "added");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "completed", "completed");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "deleted", "deleted");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "priority", "priority");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "postponed", "postponed");
		digester.addSetProperties("rsp/tasks/list/taskseries/task", "estimate", "estimate");
	}

	@Override
	public TasksGetListRsp create(InputStream in) throws IOException, SAXException {
		return getDigester().parse(in);
	}

	public static synchronized TasksGetListRspFactory getInstance() {
		if (factory == null) {
			factory = new TasksGetListRspFactory();
		}
		return factory;
	}
}
