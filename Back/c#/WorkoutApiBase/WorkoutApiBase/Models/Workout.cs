namespace WorkoutApiBase.Models;

public class Workout
{
    public string Id { get; set; } = "";
    public string Name { get; set; } = "";
    public string Color { get; set; } = "";
    public List<string> Exercises { get; set; } = new();
}