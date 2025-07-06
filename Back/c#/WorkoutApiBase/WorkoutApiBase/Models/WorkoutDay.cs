using WorkoutApiBase.Models;

public class WorkoutDay
{
    public string Date { get; set; } = "";
    public List<Workout> Workouts { get; set; } = new();
}