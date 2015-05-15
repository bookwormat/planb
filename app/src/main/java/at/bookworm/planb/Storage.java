package at.bookworm.planb;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;

import java.util.List;

import at.bookworm.planb.model.Task;

/**
 *
 */
public class Storage {

    private final Gson gson;

    public Storage() {
        gson = createGsonInstance();
    }

    public List<Task> loadTasks(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("prefs", 0);
        String json = prefs.getString("tasks", "[]");
        List<Task> tasks = gson.fromJson(json, new TypeToken<List<Task>>() {
        }.getType());
        Task task = new Task();
        task.setCreatedAt(DateTime.now());
        task.setCurrentStreak(0);
        task.setLongestStreak(0);
        task.setName("Work on planB");
        task.setDescription("I want to work on planB and ship it");
        tasks.add(task);
        return tasks;
    }

    private Gson createGsonInstance() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
        return builder.create();
    }

    public void storeTasks(Context context, List<Task> tasks) {
        SharedPreferences prefs = context.getSharedPreferences("prefs", 0);
        prefs.edit().putString("tasks", gson.toJson(tasks)).commit();
    }

}
