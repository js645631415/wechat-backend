package com.dark.monitor.service.terminal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.dark.monitor.entity.terminal.TerminalDevEntity;
import com.dark.monitor.entity.terminal.dto.TerminalDevDto;
import com.dark.monitor.mapper.terminal.TerminalDevMapper;
import com.dark.monitor.repository.system.OrgnazitionRepository;
import com.dark.monitor.repository.terminal.TerminalDevRepository;
import com.dark.monitor.trans.Trans2Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TerminalDevServices {
    @Autowired private OrgnazitionRepository orgnazitionRepository;
    @Autowired private TerminalDevMapper terminalDevMapper;
    @Autowired private TerminalDevRepository terminalDevRepository;
    public HashMap<String, Object> findTerminalByParams(Map params, int pageNumber, int pageSize) {
        Page<Map> results = PageHelper.startPage(pageNumber, pageSize);
        List<Long> ids =terminalDevMapper.findTerminalByParams(params);
        if(results.getTotal() == 0) {
            return new HashMap<String, Object>() {{
                put("total", "0");
                put("result", "");
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("total", String.valueOf(results.getTotal()));
                ArrayList<TerminalDevEntity> terminalInfoEntities = new ArrayList<>();
                ids.stream().forEach(id->{
                    terminalInfoEntities.add(terminalDevRepository.getOne(id));
                });
                put("result",terminalInfoEntities);
            }};
        }
    }


    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void saveOrUpdate(TerminalDevDto terminalDevDto) throws InstantiationException, IllegalAccessException {
        TerminalDevEntity terminalDevEntity ;
        if (terminalDevDto.getId()!=null){
            TerminalDevEntity one = terminalDevRepository.getOne(terminalDevDto.getId());
            terminalDevEntity=Trans2Entity.trans(terminalDevDto, one);
        }else {
            terminalDevEntity=Trans2Entity.trans(terminalDevDto, new TerminalDevEntity());
        }
        if (terminalDevDto.getOrganizationId()!=null){
            terminalDevEntity.setOrganization(orgnazitionRepository.getOne(terminalDevDto.getOrganizationId()));
        }
        terminalDevRepository.save(terminalDevEntity);
    }

    public void deleteById(List ids) {
        List<Long> _ids = new ArrayList<>();
        ids.stream().forEach(id->{
            _ids.add(Long.valueOf(id.toString()));
        });
        terminalDevRepository.deleteAllByIds(_ids);

    }
}
