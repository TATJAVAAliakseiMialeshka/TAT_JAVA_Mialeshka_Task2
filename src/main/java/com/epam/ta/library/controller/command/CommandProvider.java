package com.epam.ta.library.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.ta.library.controller.command.impl.AccessDenied;
import com.epam.ta.library.controller.command.impl.ActivateUser;
import com.epam.ta.library.controller.command.impl.AddBookDescription;
import com.epam.ta.library.controller.command.impl.BanUser;
import com.epam.ta.library.controller.command.impl.DisableBook;
import com.epam.ta.library.controller.command.impl.EditBookInfo;
import com.epam.ta.library.controller.command.impl.EditProfile;
import com.epam.ta.library.controller.command.impl.GrantAdmin;
import com.epam.ta.library.controller.command.impl.MarkBookReturned;
import com.epam.ta.library.controller.command.impl.OrderBook;
import com.epam.ta.library.controller.command.impl.RefuseBook;
import com.epam.ta.library.controller.command.impl.Register;
import com.epam.ta.library.controller.command.impl.RemoveAdmin;
import com.epam.ta.library.controller.command.impl.SeeAllBooks;
import com.epam.ta.library.controller.command.impl.SeeProfile;
import com.epam.ta.library.controller.command.impl.SignIn;
import com.epam.ta.library.controller.command.impl.WrongRequest;
import com.epam.ta.library.controller.session.SessionStorage;

public class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	private final static Logger log = Logger.getLogger(CommandProvider.class);

	public CommandProvider() {
		repository.put(CommandName.ACTIVATE_USER, new ActivateUser());
		repository.put(CommandName.ADD_BOOK_DESCRIPTION, new AddBookDescription());
		repository.put(CommandName.AUTHORIZATION, new SignIn());
		repository.put(CommandName.BAN_USER, new BanUser());
		repository.put(CommandName.DISABLE_BOOK, new DisableBook());
		repository.put(CommandName.EDIT_BOOK_INFO, new EditBookInfo());
		repository.put(CommandName.EDIT_PROFILE, new EditProfile());
		repository.put(CommandName.GRANT_ADMIN, new GrantAdmin());
		repository.put(CommandName.MARK_BOOK_RETURNED, new MarkBookReturned());
		repository.put(CommandName.ORDER_BOOK, new OrderBook());
		repository.put(CommandName.REFUSE_BOOK, new RefuseBook());
		repository.put(CommandName.REGISTRATION, new Register());
		repository.put(CommandName.REMOVE_ADMIN, new RemoveAdmin());
		repository.put(CommandName.REMOVE_BAN, new ActivateUser());
		repository.put(CommandName.SEE_ALL_BOOKS, new SeeAllBooks());
		repository.put(CommandName.SEE_PROFILE, new SeeProfile());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		repository.put(CommandName.ACCESS_DINIED, new AccessDenied());
	}

	public Command getCommand(String commandString) {
		
		CommandName commandName = null;
		Command command = null;
		SessionStorage session = null;
		try {
			commandName = CommandName.valueOf(commandString.toUpperCase());
			session = SessionStorage.getInstance();
			if (session.checkPermission(commandName)) {
				command = repository.get(commandName);
			} else {
				command = repository.get(CommandName.ACCESS_DINIED);
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			log.error(e);
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}

}
