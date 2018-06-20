package com.migu.schedule;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.info.TaskItem;

import java.util.*;

/*
*类名和方法不能修改
 */
public class Schedule {

    // 节点
    private List<Integer> nodes = new ArrayList<Integer>();
    // 任务id
    private List<Integer> tasks = new ArrayList<Integer>();
    // 任务信息
    private Map<Integer,TaskItem> tasksMap = new HashMap<Integer,TaskItem>();
    // 挂起队列
    private final Set<Integer> pending = new HashSet<Integer>();

    public int init()
    {
        nodes.clear();
        tasks.clear();
        tasksMap.clear();
        pending.clear();
        return ReturnCodeKeys.E001;
    }

    /**
     * 服务节点注册
     * @param nodeId
     * @return
     */
    public int registerNode(int nodeId) {

        if (nodeId > 0)
        {
            if (nodes.contains(nodeId))
            {
                // E005:服务节点已注册
                return ReturnCodeKeys.E005;
            }
            else
            {
                // E003:服务节点注册成功
                nodes.add(nodeId);
                return ReturnCodeKeys.E003;
            }
        }
        else
        {
            // E004:服务节点编号非法
            return ReturnCodeKeys.E004;
        }
    }

    /**
     * 服务节点注销
     * @param nodeId
     * @return
     */
    public int unregisterNode(int nodeId) {
        if (nodeId < 0)
        {
            // E004:服务节点编号非法
            return ReturnCodeKeys.E004;
        }
        if(!nodes.contains(nodeId))
        {
            // E007:服务节点不存在
            return ReturnCodeKeys.E007;
        }
        nodes.remove(Integer.valueOf(nodeId));
        // E006:服务节点注销成功
        return ReturnCodeKeys.E006;
    }

    /**
     * 添加任务
     * @param taskId
     * @param consumption 资源消耗率
     * @return
     */
    public int addTask(int taskId, int consumption) {

        if (taskId > 0 && consumption > 0)
        {
            if (!tasksMap.containsKey(taskId)) {
                tasksMap.put(taskId, new TaskItem(taskId, consumption));
                pending.add(taskId);
                // E008任务添加成功
                return ReturnCodeKeys.E008;
            }
            else
            {
                // E010:任务已添加
                return ReturnCodeKeys.E010;
            }
        }
        else
        {
            // E009:任务编号非法
            return ReturnCodeKeys.E009;
        }
    }

    /**
     * 删除任务
     * @param taskId
     * @return
     */
    public int deleteTask(int taskId) {

        if (taskId > 0)
        {
            if (tasksMap.containsKey(taskId))
            {
                tasks.remove(new Integer(taskId));
                tasksMap.remove(taskId);
                pending.remove(taskId);
                return ReturnCodeKeys.E011;
            }
            else
            {
                // E012:任务不存在
                return ReturnCodeKeys.E012;
            }
        }
        else
        {
            // E009:任务编号非法
            return ReturnCodeKeys.E009;
        }
    }


    public int scheduleTask(int threshold) {

        if (tasks.isEmpty())
        {
            return ReturnCodeKeys.E014;
        }

        if (threshold > 0 && !nodes.isEmpty())
        {
            if (tasks.isEmpty())
            {
                return ReturnCodeKeys.E013;
            }
        }
        return ReturnCodeKeys.E014;
    }

    /**
     * 查询任务状态列表
     * @param tasks
     * @return
     */
    public int queryTaskStatus(List<TaskInfo> tasks) {

        if (null != tasks)
        {
            tasks.clear();
            for (TaskItem item : tasksMap.values())
            {
                TaskInfo task = new TaskInfo();
                task.setNodeId(item.getNodeId());
                task.setTaskId(item.getTaskId());
                tasks.add(task);
            }
            return ReturnCodeKeys.E015;
        }
        else
        {
            return ReturnCodeKeys.E016;
        }

    }
}
