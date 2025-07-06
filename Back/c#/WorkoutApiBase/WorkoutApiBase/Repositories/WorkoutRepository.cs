using WorkoutApiBase.Models;

namespace WorkoutApiBase.Repositories;

public class WorkoutRepository
{
    public IEnumerable<Workout> GetWorkoutsForMonth(int year, int month)
    {
        // Simulazione dati statici
        return new List<Workout>
        {
            new Workout { Id = "1", Name = "Allenamento braccia", Color = "#FF0000" },
            new Workout { Id = "2", Name = "Cardio", Color = "#00FF00" },
        };
    }
}
