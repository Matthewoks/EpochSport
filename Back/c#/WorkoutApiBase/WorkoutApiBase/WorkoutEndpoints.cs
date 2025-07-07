using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.OpenApi;
namespace WorkoutApiBase;

public static class WorkoutEndpoints
{
    public static void MapWorkoutEndpoints (this IEndpointRouteBuilder routes)
    {
        var group = routes.MapGroup("/api/Workout").WithTags(nameof(Workout));

        group.MapGet("/", async (WorkoutDbContext db) =>
        {
            return await db.Workout.ToListAsync();
        })
        .WithName("GetAllWorkouts")
        .WithOpenApi();

        group.MapGet("/{id}", async Task<Results<Ok<Workout>, NotFound>> (string id, WorkoutDbContext db) =>
        {
            return await db.Workout.AsNoTracking()
                .FirstOrDefaultAsync(model => model.Id == id)
                is Workout model
                    ? TypedResults.Ok(model)
                    : TypedResults.NotFound();
        })
        .WithName("GetWorkoutById")
        .WithOpenApi();

        group.MapPut("/{id}", async Task<Results<Ok, NotFound>> (string id, Workout workout, WorkoutDbContext db) =>
        {
            var affected = await db.Workout
                .Where(model => model.Id == id)
                .ExecuteUpdateAsync(setters => setters
                    .SetProperty(m => m.Id, workout.Id)
                    .SetProperty(m => m.Name, workout.Name)
                    .SetProperty(m => m.Color, workout.Color)
                    .SetProperty(m => m.Exercises, workout.Exercises)
                    );
            return affected == 1 ? TypedResults.Ok() : TypedResults.NotFound();
        })
        .WithName("UpdateWorkout")
        .WithOpenApi();

        group.MapPost("/", async (Workout workout, WorkoutDbContext db) =>
        {
            db.Workout.Add(workout);
            await db.SaveChangesAsync();
            return TypedResults.Created($"/api/Workout/{workout.Id}",workout);
        })
        .WithName("CreateWorkout")
        .WithOpenApi();

        group.MapDelete("/{id}", async Task<Results<Ok, NotFound>> (string id, WorkoutDbContext db) =>
        {
            var affected = await db.Workout
                .Where(model => model.Id == id)
                .ExecuteDeleteAsync();
            return affected == 1 ? TypedResults.Ok() : TypedResults.NotFound();
        })
        .WithName("DeleteWorkout")
        .WithOpenApi();
    }
}
