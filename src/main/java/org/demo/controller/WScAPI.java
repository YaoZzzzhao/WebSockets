package org.demo.controller;

import org.demo.model.Task;
import org.demo.model.TaskJoin;
import org.demo.model.UnionResult;
import org.demo.service.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class WScAPI {
    private final Logger logger = LoggerFactory.getLogger(WScAPI.class);

    @Autowired
    Client client;

    @Autowired
    WebSocketEndPoint webSocketEndPoint;

    @RequestMapping(value = "/getTotal", method = RequestMethod.GET)
    public String getTotal(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        return client.getTotal();
    }

    @RequestMapping(value = "/getInprogress", method = RequestMethod.GET)
    public String getInProgress(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        return client.getInProgress();
    }

    @RequestMapping(value = "/getCompleted", method = RequestMethod.GET)
    public String getComleted(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        return client.getCompleted();
    }

    @RequestMapping(value = "/getCancelled", method = RequestMethod.GET)
    public String getCancelled(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        return client.getCancelled();
    }

    @RequestMapping(value = "/getNull", method = RequestMethod.GET)
    public String getNull(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        return client.getNull();
    }

    @RequestMapping(value = "/getUnion", method = RequestMethod.GET)
    public List<UnionResult> getUnionR(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        List<UnionResult> list = client.getUnionResult();
        return list;
    }

    @RequestMapping(value = "/getDaily", method = RequestMethod.GET)
    public List<TaskJoin> getDaily(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        List<TaskJoin> list = client.getDaily();
        return list;
    }

    @RequestMapping(value = "/getDailySummary", method = RequestMethod.GET)
    public Map<String, Long> getDailySummary(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        Long total = 0L;
        Long inP = 0L;
        Long comp = 0L;
        Long cancel = 0L;
        Long nullStatus = 0L;
        List<TaskJoin> list = client.getDaily();
        for(TaskJoin tj:list){
            if(tj.getStatus() == null || tj.getStatus()=="") nullStatus ++;
            else if(tj.getStatus().equals("In Progress")) inP++;
            else if(tj.getStatus().equals("Completed")) comp++;
            else if(tj.getStatus().equals("Cancelled")) cancel++;
            else total++;
        }
        total += inP + comp + cancel + nullStatus;
        Map<String, Long> map = new HashMap<>();
        map.put("Total",total);
        map.put("In Progress",inP);
        map.put("Completed",comp);
        map.put("Cancelled",cancel);
        map.put("Null",nullStatus);
        return map;
    }

    @RequestMapping(value = "/getWeekly", method = RequestMethod.GET)
    public List<TaskJoin> getWeekly(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        List<TaskJoin> list = client.getWeekly();
        return list;
    }

    @RequestMapping(value = "/getWeeklySummary", method = RequestMethod.GET)
    public Map<String, Long> getWeeklySummary(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        Long total = 0L;
        Long inP = 0L;
        Long comp = 0L;
        Long cancel = 0L;
        Long nullStatus = 0L;
        List<TaskJoin> list = client.getWeekly();
        for(TaskJoin tj:list){
            if(tj.getStatus() == null || tj.getStatus()=="") nullStatus ++;
            else if(tj.getStatus().equals("In Progress")) inP++;
            else if(tj.getStatus().equals("Completed")) comp++;
            else if(tj.getStatus().equals("Cancelled")) cancel++;
            else total++;
        }
        total += inP + comp + cancel + nullStatus;
        Map<String, Long> map = new HashMap<>();
        map.put("Total",total);
        map.put("InProgress",inP);
        map.put("Completed",comp);
        map.put("Cancelled",cancel);
        map.put("Null",nullStatus);
        return map;
    }

    @RequestMapping(value = "/getMonthly", method = RequestMethod.GET)
    public List<TaskJoin> getMonthly(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        List<TaskJoin> list = client.getMonthly();
        return list;
    }

    @RequestMapping(value = "/getMonthlySummary", method = RequestMethod.GET)
    public Map<String, Long> getMonthlySummary(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        Long total = 0L;
        Long inP = 0L;
        Long comp = 0L;
        Long cancel = 0L;
        Long nullStatus = 0L;
        List<TaskJoin> list = client.getMonthly();
        for(TaskJoin tj:list){
            if(tj.getStatus() == null || tj.getStatus()=="") nullStatus ++;
            else if(tj.getStatus().equals("In Progress")) inP++;
            else if(tj.getStatus().equals("Completed")) comp++;
            else if(tj.getStatus().equals("Cancelled")) cancel++;
            else total++;
        }
        total += inP + comp + cancel + nullStatus;
        Map<String, Long> map = new HashMap<>();
        map.put("Total",total);
        map.put("InProgress",inP);
        map.put("Completed",comp);
        map.put("Cancelled",cancel);
        map.put("Null",nullStatus);
        return map;
    }

    @RequestMapping(value = "/getQuarterly", method = RequestMethod.GET)
    public List<TaskJoin> getQuarterly(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        List<TaskJoin> list = client.getQuarterly();
        return list;
    }

    @RequestMapping(value = "/getQuarterlySummary", method = RequestMethod.GET)
    public Map<String, Long> getQuarterlySummary(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        Long total = 0L;
        Long inP = 0L;
        Long comp = 0L;
        Long cancel = 0L;
        Long nullStatus = 0L;
        List<TaskJoin> list = client.getQuarterly();
        for(TaskJoin tj:list){
            if(tj.getStatus() == null || tj.getStatus()=="") nullStatus ++;
            else if(tj.getStatus().equals("In Progress")) inP++;
            else if(tj.getStatus().equals("Completed")) comp++;
            else if(tj.getStatus().equals("Cancelled")) cancel++;
            else total++;
        }
        total += inP + comp + cancel + nullStatus;
        Map<String, Long> map = new HashMap<>();
        map.put("Total",total);
        map.put("InProgress",inP);
        map.put("Completed",comp);
        map.put("Cancelled",cancel);
        map.put("Null",nullStatus);
        return map;
    }

    @RequestMapping(value = "/getAnnually", method = RequestMethod.GET)
    public List<TaskJoin> getAnnually(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        List<TaskJoin> list = client.getAnnually();
        return list;
    }

    @RequestMapping(value = "/getAnnuallySummary", method = RequestMethod.GET)
    public Map<String, Long> getAnnuallySummary(){
        logger.debug(">>>>>>>>>>>>>>>>>.");
        Long total = 0L;
        Long inP = 0L;
        Long comp = 0L;
        Long cancel = 0L;
        Long nullStatus = 0L;
        List<TaskJoin> list = client.getAnnually();
        for(TaskJoin tj:list){
            if(tj.getStatus() == null || tj.getStatus()=="") nullStatus ++;
            else if(tj.getStatus().equals("In Progress")) inP++;
            else if(tj.getStatus().equals("Completed")) comp++;
            else if(tj.getStatus().equals("Cancelled")) cancel++;
            else total++;
        }
        total += inP + comp + cancel + nullStatus;
        Map<String, Long> map = new HashMap<>();
        map.put("Total",total);
        map.put("InProgress",inP);
        map.put("Completed",comp);
        map.put("Cancelled",cancel);
        map.put("Null",nullStatus);
        return map;
    }

    @RequestMapping(value = "/saveTask",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String saveTask(@RequestBody Task task){
        String msg = "Task not saved.";
        try {
            logger.warn(">>>>>>>>>>>>Task: "+ task.toString());
            boolean isSuccess = client.insert(task);
//            webSocketEndpoint.sendMessage("1");
            if(isSuccess)
                msg = "Task saved successfully!";
        }catch(Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping(value = "/updateTask",method = RequestMethod.PUT,consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateTask(@RequestBody Task task) throws IOException {
//        try {
        if(task.getTaskName()!=null) {
            client.update(task);
//            webSocketEndPoint.sendMessage("1");
            return "Task updated successfully!";
        }else
            return "Missing information.";
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return "Task not updated.";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String deleteTask(){
        String msg = "Task not deleted.";
        try {
            client.delete();
//            webSocketEndpoint.sendMessage("1");
            msg = " Task Deleted Successfully.";
        }catch(Exception e){
            e.printStackTrace();
        }
        return msg;
    }

}
