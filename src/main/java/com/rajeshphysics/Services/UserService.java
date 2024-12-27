package com.rajeshphysics.Services;

import java.util.List;

import com.rajeshphysics.Models.User;
import com.rajeshphysics.Payloads.PageableDataResponse;


public interface UserService {
	public User createUser(User user, Long roleId, Long courseId);
	public User updateUserToken(String userName, Long days, String isPaid);
	public PageableDataResponse<List<User>> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);
	User getUserInfo(String username);

}
