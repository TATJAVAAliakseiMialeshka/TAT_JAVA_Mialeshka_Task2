package com.epam.ta.library.service;

import com.epam.ta.library.service.exception.ServiceException;

public interface SuperadminService {

	boolean deleteAdminRole(Integer userId) throws ServiceException;
}
