package com.javarush.task.task23.task2304;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Inner 3
*/

public class Solution {

    private List<Task> tasks;
    private List<String> names;

    private DbDataProvider taskDataProvider = new TaskDataProvider();
    private DbDataProvider nameDataProvider = new NameDataProvider();

    public void refresh() {
        Map taskCriteria = MockView.getFakeTaskCriteria();
        taskDataProvider.refreshAllData(taskCriteria);

        Map nameCriteria = MockView.getFakeNameCriteria();
        nameDataProvider.refreshAllData(nameCriteria);
    }

    private interface DbDataProvider<T> {
        void refreshAllData(Map criteria);
    }

    class Task {
    }

    private class TaskDataProvider implements DbDataProvider{
        @Override
        public void refreshAllData(Map criteria) {
            List<Task> fakeTasks = MockDB.getFakeTasks(criteria);
            tasks = fakeTasks;
        }
    }

    private class NameDataProvider implements DbDataProvider{
        @Override
        public void refreshAllData(Map criteria) {
            List<String> fakeNames = MockDB.getFakeNames(criteria);
            names = fakeNames;
        }
    }

    public static void main(String[] args) {

    }
}
