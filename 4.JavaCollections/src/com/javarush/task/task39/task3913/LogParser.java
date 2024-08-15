package com.javarush.task.task39.task3913;



import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    Path logDir;
    List<Path> files;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public LogParser(Path logDir) {
        this.logDir = logDir;

        try(Stream<Path> list = Files.list(logDir)) {
            files = list.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".log")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = getUniqueIPs(after, before);
        return uniqueIPs.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = reader.readLine()) != null) {
                    String[] arr = len.split("\t");
                    Date real = formatter.parse(arr[2]);
                    if (dateCheck(real, after, before)) {
                        result.add(arr[0]);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = reader.readLine()) != null) {
                    String[] arr = len.split("\t");
                    Date real = formatter.parse(arr[2]);
                    if (arr[1].equals(user)) {
                        if (dateCheck(real, after, before)) {
                            result.add(arr[0]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = reader.readLine()) != null) {
                    String[] arr = len.split("\t");
                    Date real = formatter.parse(arr[2]);
                    String s = arr[3];
                    String[] s1 = s.split(" ");
                    Event event1 = Event.valueOf(s1[0]);

                    if (event1 == event) {
                        if (dateCheck(real, after, before)) {
                            result.add(arr[0]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = reader.readLine()) != null) {
                    String[] arr = len.split("\t");
                    Date real = formatter.parse(arr[2]);
                    Status statusTemp = Status.valueOf(arr[4]);
                    if (statusTemp == status){
                        if (dateCheck(real,after,before)){
                            result.add(arr[0]);
                        }
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = reader.readLine()) != null) {
                    String[] arr = len.split("\t");
                    result.add(arr[1]);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = reader.readLine()) != null) {
                    String[] arr = len.split("\t");
                    if (dateCheck(formatter.parse(arr[2]),after,before)){
                        result.add(arr[1]);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    if (split[1].equals(user)){
                        if (dateCheck(real,after,before)){
                            result.add(split[3]);
                        }
                    }
                }

            }catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    if (split[0].equals(ip)){
                        if (dateCheck(real,after,before)){
                            result.add(split[1]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    if (event == Event.LOGIN){
                        if (dateCheck(real,after,before)){
                            result.add(split[1]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    if (event == Event.DOWNLOAD_PLUGIN){
                        if (dateCheck(real,after,before)){
                            result.add(split[1]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    if (event == Event.WRITE_MESSAGE){
                        if (dateCheck(real,after,before)){
                            result.add(split[1]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    if (event == Event.SOLVE_TASK){
                        if (dateCheck(real,after,before)){
                            result.add(split[1]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);

                    if (event == Event.SOLVE_TASK && arr[1] != null){
                        int i = Integer.parseInt(arr[1]);
                        if (i == task) {
                            if (dateCheck(real, after, before)) {
                                result.add(split[1]);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    if (event == Event.DONE_TASK){
                        if (dateCheck(real,after,before)){
                            result.add(split[1]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);

                    if (event == Event.DONE_TASK && arr[1] != null){
                        int i = Integer.parseInt(arr[1]);
                        if (i == task) {
                            if (dateCheck(real, after, before)) {
                                result.add(split[1]);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
      Set<Date> result = new HashSet<>();

      for (Path temp : files){
          try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
              String len;
              while((len = reader.readLine()) != null){
                  String[] split = len.split("\t");
                  Date real = formatter.parse(split[2]);
                  String[] arr = split[3].split(" ");
                  Event eventTemp = Event.valueOf(arr[0]);
                  if (split[1].equals(user) && eventTemp == event && dateCheck(real,after,before)){
                      result.add(real);
                  }
              }
          } catch (IOException e) {
              throw new RuntimeException(e);
          } catch (ParseException e) {
              throw new RuntimeException(e);
          }
      }

      return result;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    Status status = Status.valueOf(split[4]);
                    if (status == Status.FAILED && dateCheck(real,after,before)){
                        result.add(real);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    Status status = Status.valueOf(split[4]);
                    if (status == Status.ERROR && dateCheck(real,after,before)){
                        result.add(real);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    Date real = formatter.parse(split[2]);
                    if (dateCheck(real, after, before)) {
                        if (split[1].equals(user) && event == Event.LOGIN) {
                            result.add(real);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if (result.size() == 0){
            return null;
        }


        return Collections.min(result);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    Date real = formatter.parse(split[2]);
                    if (dateCheck(real,after,before)) {
                        if (split[1].equals(user) && event == Event.SOLVE_TASK && arr[1] != null) {
                            int i = Integer.parseInt(arr[1]);
                            if (i == task) {
                                result.add(real);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if (result.size() == 0){
            return null;
        }
        return Collections.min(result);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    Date real = formatter.parse(split[2]);
                    if (dateCheck(real,after,before)) {
                        if (split[1].equals(user) && event == Event.DONE_TASK && arr[1] != null) {
                            int i = Integer.parseInt(arr[1]);
                            if (i == task) {
                                result.add(real);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if (result.size() == 0){
            return null;
        }
         return Collections.min(result);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    Date real = formatter.parse(split[2]);
                    if (dateCheck(real,after,before)) {
                        if (split[1].equals(user) && event == Event.WRITE_MESSAGE) {
                            result.add(formatter.parse(split[2]));
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    String[] arr = split[3].split(" ");
                    Event event = Event.valueOf(arr[0]);
                    Date real = formatter.parse(split[2]);
                    if (dateCheck(real,after,before)) {
                        if (split[1].equals(user) && event == Event.DOWNLOAD_PLUGIN) {
                            result.add(real);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after,before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = buff.readLine()) != null){
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    if (dateCheck(real,after,before)){
                        String[] s = split[3].split(" ");
                        Event event = Event.valueOf(s[0]);
                        result.add(event);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    if (dateCheck(real, after, before)) {
                        if (split[0].equals(ip)) {
                            String[] s = split[3].split(" ");
                            Event event = Event.valueOf(s[0]);
                            result.add(event);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    if (dateCheck(real, after, before)) {
                        if (split[1].equals(user)) {
                            String[] s = split[3].split(" ");
                            Event event = Event.valueOf(s[0]);
                            result.add(event);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    Status status = Status.valueOf(split[4]);
                    if (dateCheck(real, after, before)) {
                        if (status == Status.FAILED) {
                            String[] s = split[3].split(" ");
                            Event event = Event.valueOf(s[0]);
                            result.add(event);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    Status status = Status.valueOf(split[4]);
                    if (dateCheck(real, after, before)) {
                        if (status == Status.ERROR) {
                            String[] s = split[3].split(" ");
                            Event event = Event.valueOf(s[0]);
                            result.add(event);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int size = 0;

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] s1 = split[3].split(" ");
                    Event event = Event.valueOf(s1[0]);
                    if (dateCheck(real, after, before) && event == Event.SOLVE_TASK) {
                      if (Integer.parseInt(s1[1]) == task){
                          size++;
                      }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return size;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int size = 0;

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] s1 = split[3].split(" ");
                    Event event = Event.valueOf(s1[0]);
                    if (dateCheck(real, after, before) && event == Event.DONE_TASK) {
                        if (Integer.parseInt(s1[1]) == task) {
                            size++;
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return size;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer> map = new HashMap<>();

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] s1 = split[3].split(" ");
                    Event event = Event.valueOf(s1[0]);
                    if (dateCheck(real, after, before)) {
                        if (event == Event.SOLVE_TASK) {
                            int task = Integer.parseInt(s1[1]);
                            int size = getNumberOfAttemptToSolveTask(task, after, before);
                            map.put(task, size);
                        }
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer> map = new HashMap<>();

        for (Path temp : files){
            try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while ((len = buff.readLine()) != null) {
                    String[] split = len.split("\t");
                    Date real = formatter.parse(split[2]);
                    String[] s1 = split[3].split(" ");
                    Event event = Event.valueOf(s1[0]);
                    if (dateCheck(real,after,before)){
                        if (event == Event.DONE_TASK){
                            int task = Integer.parseInt(s1[1]);
                            int size = getNumberOfSuccessfulAttemptToSolveTask(task, after, before);
                            map.put(task,size);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return map;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> result = new HashSet<>();

        String[] s = query.split(" ");

        if (s.length == 2){
            return findAll(indexFor(s[1]));
        }

        List<String> extractedValues = extractQuotedValues(query);
        int in1 = indexFor(s[1]);
        int in2 = indexFor(s[3]);

        if (extractedValues.size() == 1) {
            return getElements(in1, in2, extractedValues.get(0));
        }else if (extractedValues.size() == 3){
           return getElementsForDate(in1,in2,extractedValues.get(0),extractedValues.get(1),extractedValues.get(2));
        }

        return result;
    }


    public static List<String> extractQuotedValues(String input) {
        List<String> values = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            values.add(matcher.group(1));
        }

        return values;
    }

    private Set<Object> findAll(int index){
        Set<Object> result = new HashSet<>();

        if (index == 0){
            Set<String> uniqueIPs = getUniqueIPs(new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
            for (String temp : uniqueIPs){
                result.add(temp);
            }
        }else if (index == 1){
            Set<String> allUsers = getAllUsers();
            for (String temp : allUsers){
                result.add(temp);
            }
        }else if (index == 2){
            for (Path temp : files){
                try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                    String len;
                    while ((len = buff.readLine()) != null) {
                        String[] split = len.split("\t");
                        Date real = formatter.parse(split[2]);
                        result.add(real);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }else if (index == 3){
            for (Path temp : files){
                try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                    String len;
                    while ((len = buff.readLine()) != null) {
                        String[] split = len.split("\t");
                        Event event = Event.valueOf(split[3].split(" ")[0]);
                        result.add(event);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else if (index == 4){
            for (Path temp : files){
                try(BufferedReader buff = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                    String len;
                    while ((len = buff.readLine()) != null) {
                        String[] split = len.split("\t");
                        Status status = Status.valueOf(split[4]);
                        result.add(status);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        return result;
    }

    public Set<Object> getElements(int id1, int id2, String value) {
        Set<Object> result = new HashSet<>();

        for (Path temp : files){
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))){
                String len;
                while((len = reader.readLine()) != null){
                    String[] split = len.split("\t");

                    if (id2 == 3){
                       if (split[id2].split(" ")[0].equals(value)){
                           if (id1 == 0){
                               String ip = split[0];
                               result.add(ip);
                           }else if (id1 == 1) {
                               String user = split[1];
                               result.add(user);
                           }else if (id1 == 2) {
                               Date parse = formatter.parse(split[2]);
                               result.add(parse);
                           }else if (id1 == 3) {
                               Event event = Event.valueOf(split[3].split(" ")[0]);
                               result.add(event);
                           }else if (id1 == 4) {
                               Status status = Status.valueOf(split[4]);
                               result.add(status);
                           }
                       }
                    }else if (split[id2].equals(value)){
                        if (id1 == 0){
                            String ip = split[0];
                            result.add(ip);
                        }else if (id1 == 1) {
                            String user = split[1];
                            result.add(user);
                        }else if (id1 == 2) {
                            Date parse = formatter.parse(split[2]);
                            result.add(parse);
                        }else if (id1 == 3) {
                            Event event = Event.valueOf(split[3].split(" ")[0]);
                            result.add(event);
                        }else if (id1 == 4) {
                            Status status = Status.valueOf(split[4]);
                            result.add(status);
                        }
                    }


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public Set<Object> getElementsForDate(int id1, int id2, String value, String date_1, String date_2) {
        Set<Object> result = new HashSet<>();

        for (Path temp : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(temp)))) {
                String len;
                while ((len = reader.readLine()) != null) {
                    String[] split = len.split("\t");

                    Date real = formatter.parse(split[2]);
                    Date after = formatter.parse(date_1);
                    Date before = formatter.parse(date_2);

                    if (real.after(after) && real.before(before)) {
                        if (id2 == 3) {
                            if (split[id2].split(" ")[0].equals(value)) {
                                if (id1 == 0) {
                                    String ip = split[0];
                                    result.add(ip);
                                } else if (id1 == 1) {
                                    String user = split[1];
                                    result.add(user);
                                } else if (id1 == 2) {
                                    Date parse = formatter.parse(split[2]);
                                    result.add(parse);
                                } else if (id1 == 3) {
                                    Event event = Event.valueOf(split[3].split(" ")[0]);
                                    result.add(event);
                                } else if (id1 == 4) {
                                    Status status = Status.valueOf(split[4]);
                                    result.add(status);
                                }
                            }
                        } else if (split[id2].equals(value)) {
                            if (id1 == 0) {
                                String ip = split[0];
                                result.add(ip);
                            } else if (id1 == 1) {
                                String user = split[1];
                                result.add(user);
                            } else if (id1 == 2) {
                                Date parse = formatter.parse(split[2]);
                                result.add(parse);
                            } else if (id1 == 3) {
                                Event event = Event.valueOf(split[3].split(" ")[0]);
                                result.add(event);
                            } else if (id1 == 4) {
                                Status status = Status.valueOf(split[4]);
                                result.add(status);
                            }
                        }
                    }

                


                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


        }
        return result;
    }

    private  int indexFor(String id) {
        Map<String, Integer> queryMap = new HashMap<>();
        queryMap.put("ip" , 0);
        queryMap.put("user" , 1);
        queryMap.put("date" , 2);
        queryMap.put("event" , 3);
        queryMap.put("status" , 4);

        return queryMap.get(id);
    }

    private boolean dateCheck(Date real, Date after, Date before) {
        if (after == null && before == null) {
            return true;
        }

        if (after != null && real.before(after)) {
            return false;
        }

        if (before != null && real.after(before)) {
            return false;
        }

        return true;
    }


}
