package com.example.p01.service.ifs;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.p01.dao.NewTableRepository;
import com.example.p01.entity.NewTable;
import com.example.p01.service.impl.NewTableServiceImpl;

@Service
public interface NewTableService {
	
	 List<NewTable> getAll();
	    NewTable create(NewTable newRecord);
	    NewTable askAI(NewTable newRecord);
}
