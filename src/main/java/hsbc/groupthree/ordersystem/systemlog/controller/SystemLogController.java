package hsbc.groupthree.ordersystem.systemlog.controller;

import hsbc.groupthree.ordersystem.systemlog.entity.SystemLog;
import hsbc.groupthree.ordersystem.systemlog.service.SystemLogService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author:Evan
 * @Date:2018/8/9 10:25
 * @Describe：
 * @Return:
 * @Param:
 */
@RestController
public class SystemLogController {

    @Autowired
    SystemLogService systemLogService;

    @RequestMapping(value = "/export/systemlog", method = { RequestMethod.GET,RequestMethod.POST })
    public String getSystemLogList( @RequestParam(value = "n") int n,HttpServletResponse response) {

        System.out.println(n+"111");
        String str = systemLogService.getSystemLog(n, response);
        return str;
    }

    @GetMapping("/get/system/log/list")
    public Page<SystemLog> getSystemLogList(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNum) {
        Sort sort = Sort.by("time");
        PageRequest request = PageRequest.of(pageNum, 5, sort);
        Page<SystemLog> logs = systemLogService.getSystemLogListPage(request);
        return logs;
    }


}
