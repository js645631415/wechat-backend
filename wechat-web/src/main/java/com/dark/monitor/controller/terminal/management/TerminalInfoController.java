package com.dark.monitor.controller.terminal.management;

import com.dark.monitor.annotion.ResourceDescription;
import com.dark.monitor.controller.base.BaseController;
import com.dark.monitor.entity.terminal.dto.TerminalDevDto;
import com.dark.monitor.json.Response;
import com.dark.monitor.service.terminal.TerminalDevServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/terminalInfo")
public class TerminalInfoController extends BaseController {
    @Autowired private TerminalDevServices terminalDevServices;
    @PostMapping("/getTerminal")
    @ResourceDescription("终端查询")
    @PreAuthorize("hasPermission('terminal:*')")
    public Response getAllTerminal(@RequestBody Map params){
        Response response = Response.Success();
        int pageNumber = null != params.get("pageNum") ? (Integer) params.get("pageNum") : 1;
        int pageSize = null != params.get("pageSize") ? (Integer) params.get("pageSize") : 10;
        params.put("orgIds",getPermitShowOrgids());
        HashMap<String, Object> terminalByParams = terminalDevServices.findTerminalByParams(params, pageNumber, pageSize);
        response.put("total",terminalByParams.get("total"));
        response.put("result",terminalByParams.get("result"));
        return response;
    }

    /**有ID时是修改，无是增加*/
    @PostMapping("/saveTerminal")
    @ResourceDescription("终端增加或修改")
    @PreAuthorize("hasPermission('terminal:update')")
    public Response saveTerminal(TerminalDevDto terminalDevDto){
        Response response = Response.Success();
        try {
            terminalDevServices.saveOrUpdate(terminalDevDto);
        }catch (Exception e){
            log.error("终端增加，修改失败:"+e);
            return  Response.Error("失败");
        }
        return response;
    }
    /**有ID时是修改，无是增加*/
    @PostMapping("/deleteTerminal")
    @ResourceDescription("终端增删除")
    @PreAuthorize("hasPermission('terminal:update')")
    public Response deleteTerminal(@RequestBody Map params){
        Response response = Response.Success();
        try {
            List ids = (List) params.get("ids");
            terminalDevServices.deleteById(ids);
        }catch (Exception e){
            log.error("终端增加，修改失败:"+e);
            return  Response.Error("失败");
        }
        return response;
    }


}
