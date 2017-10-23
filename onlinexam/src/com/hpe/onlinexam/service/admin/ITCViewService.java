package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.vo.TCView;

public interface ITCViewService {
	void saveTCView(TCView v);
	void updateTCView(TCView v);
	void delTCView(int id);
	
	List<TCView> searchTCView(TCView v);
	TCView findById(int id);	
}
