package com.dark.monitor.service.terminal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.dark.monitor.entity.terminal.TerminalLogEntity;
import com.dark.monitor.mapper.terminal.TerminalLogMapper;
import com.dark.monitor.repository.terminal.TerminalLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TerminalLogService {
    @Autowired private TerminalLogMapper terminalLogMapper;
    @Autowired private TerminalLogRepository terminalLogRepository;
    public HashMap<String, Object> findTerminalByParams(Map params, int pageNumber, int pageSize) {
        Page<Map> results = PageHelper.startPage(pageNumber, pageSize);
        List<Long> ids =terminalLogMapper.findTerminalLogByParams(params);
        if(results.getTotal() == 0) {
            return new HashMap<String, Object>() {{
                put("total", "0");
                put("result", "");
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("total", String.valueOf(results.getTotal()));
                ArrayList<TerminalLogEntity> terminalLogEntities = new ArrayList<>();
                ids.stream().forEach(id->{
                    terminalLogEntities.add(terminalLogRepository.getOne(id));
                });
                put("result",terminalLogEntities);
            }};
        }
    }
}
