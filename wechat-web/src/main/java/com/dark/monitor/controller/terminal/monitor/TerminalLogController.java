package com.dark.monitor.controller.terminal.monitor;

import com.dark.monitor.annotion.ResourceDescription;
import com.dark.monitor.controller.base.BaseController;
import com.dark.monitor.json.Response;
import com.dark.monitor.service.terminal.TerminalLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/terminalLog")
public class TerminalLogController extends BaseController {
    @Autowired private TerminalLogService terminalLogService;

    @PostMapping("/getTerminalLog")
    @ResourceDescription("终端日志查询")
    @PreAuthorize("hasPermission('*')")
    public Response getTerminalLog(@RequestBody Map params){
        Response response = Response.Success();
        int pageNumber = null != params.get("pageNum") ? (Integer) params.get("pageNum") : 1;
        int pageSize = null != params.get("pageSize") ? (Integer) params.get("pageSize") : 10;
        params.put("orgIds",getPermitShowOrgids());
        HashMap<String, Object> terminalByParams = terminalLogService.findTerminalByParams(params, pageNumber, pageSize);
        response.put("total",terminalByParams.get("total"));
        response.put("result",terminalByParams.get("result"));
        return response;
    }
}
