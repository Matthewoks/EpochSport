namespace MinimalApi.Models
{
    public class WorkoutDay
    {
        public DateTime Date { get; set; }
        public List<Workout> Workouts { get; set; } = new();
    }
}
