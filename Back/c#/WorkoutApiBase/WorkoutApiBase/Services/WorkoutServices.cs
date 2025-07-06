using WorkoutApiBase.Models;
using WorkoutApiBase.Repositories;

namespace WorkoutApiBase.Services;

public class WorkoutService
{
    private readonly WorkoutRepository _repo;

    public WorkoutService(WorkoutRepository repo)
    {
        _repo = repo;
    }

    public IEnumerable<Workout> GetWorkouts(DateTime month)
    {
        return _repo.GetWorkoutsForMonth(month.Year, month.Month);
    }
}
