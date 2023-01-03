package com.jsp.jspwfm.Services;

import com.jsp.jspwfm.Dao.UsersRepository;
import com.jsp.jspwfm.Exception.PasswordInvalidException;
import com.jsp.jspwfm.Exception.UserNotFoundException;
import com.jsp.jspwfm.Models.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService {

	@Autowired
	UsersRepository usersRepository;

	public User getUser(String username, String password) {
		return usersRepository.getUserByUsername(username);
	}

	public Object login(String username, String password) {
		User user = usersRepository.getUserByUsername(username);

		if (user != null) {
			if (user.getPassword().equals(password))

			{
				return user;
			} else {
				try {
					throw new PasswordInvalidException("Invalid password or username");
				} catch (PasswordInvalidException exception) {
					return exception.getMessage();

				}
			}
		}

		try

		{
			throw new UserNotFoundException("Invalid password or username");
		} catch (UserNotFoundException exception) {
			return exception.getMessage();

		}
	}
}
