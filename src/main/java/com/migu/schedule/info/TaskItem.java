package com.migu.schedule.info;

/**
 * @author wangqiang
 * @version C10 2018年06月20日
 * @since SDP V300R003C10
 */
public class TaskItem
{
    int nodeId;
    int taskId;
    int consumption;

    public TaskItem(int nodeId,int taskId, int consumption)
    {
        this.nodeId = nodeId;
        this.taskId = taskId;
        this.consumption = consumption;
    }

    public TaskItem(int taskId, int consumption)
    {
        this.taskId = taskId;
        this.consumption = consumption;
    }


    public int getNodeId()
    {
        return nodeId;
    }

    public void setNodeId(int nodeId)
    {
        this.nodeId = nodeId;
    }

    public int getTaskId()
    {
        return taskId;
    }

    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }

    public int getConsumption()
    {
        return consumption;
    }

    public void setConsumption(int consumption)
    {
        this.consumption = consumption;
    }
}
