using MinimalApi.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
builder.Services.AddOpenApi();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
}

#region inserisco parte back
List<WorkoutDay> workoutDays = new()
{
    new WorkoutDay()
    {
        Date = DateTime.Now.Date, // Solo la data, senza l'ora
        Workouts = new List<Workout>()
        {
            new Workout()
            {
                Id = "A1",
                Name = "Petto & Tricipiti",
                Color = "Blu",
                Exercises = new List<string>()
                {
                    "Panca piana con bilanciere",
                    "Croci ai cavi",
                    "Spinte manubri su inclinata",
                    "Estensioni tricipiti con manubrio",
                    "Pushdown ai cavi"
                }
            }
        }
    },
    new WorkoutDay()
    {
        Date = DateTime.Now.Date.AddDays(1), // Il giorno successivo
        Workouts = new List<Workout>()
        {
            new Workout()
            {
                Id = "B1",
                Name = "Dorso & Bicipiti",
                Color = "Verde",
                Exercises = new List<string>()
                {
                    "Lat machine",
                    "Rematore con bilanciere",
                    "Pulldown braccia tese",
                    "Curl con bilanciere",
                    "Curl a martello con manubri"
                }
            }
        }
    },
    new WorkoutDay()
    {
        Date = DateTime.Now.Date.AddDays(1), // Il giorno successivo
        Workouts = new List<Workout>()
        {
            new Workout()
            {
                Id = "C1",
                Name = "Dormire",
                Color = "Verde",
                Exercises = new List<string>()
                {
                    "Letto di fianco",
                    "Letto a pancia in su"
              
                }
            },
             new Workout()
            {
                Id = "C2",
                Name = "Dormire 2",
                Color = "Verde",
                Exercises = new List<string>()
                {
                    "A Tavola",
                    "Sul divano",
                    "In Spiaggia"
                }
            }
        }
    },
};

//rendo tutti i giorni
app.MapGet("/workoutdays", () => workoutDays)
    .WithName("GetWorkoutDays")
    .WithOpenApi()
    .Produces<List<WorkoutDay>>(StatusCodes.Status200OK);

//rendo il giorno senza controllo
app.MapGet("/workoutdays/{date:datetime}", (DateTime date) => workoutDays.FirstOrDefault(wd => wd.Date.Date == date.Date))
    .WithName("GetWorkoutDayByDate")
    .WithOpenApi()
    .Produces<WorkoutDay>(StatusCodes.Status200OK)
    .Produces(StatusCodes.Status404NotFound);

//controllo se il giorno esiste
app.MapGet("/workoutdays2/{date:datetime}", (DateTime date) =>
{
    var workoutDay = workoutDays.FirstOrDefault(wd => wd.Date.Date == date.Date);
    if (workoutDay == null)
    {
        return Results.NotFound();
    }
    return Results.Ok(workoutDay);
});


app.MapPost("/addworkoutdays", (WorkoutDay workoutDay) =>
{
    // Aggiungo il nuovo WorkoutDay alla lista
    workoutDays.Add(workoutDay);
    return Results.Created($"/workoutdays/{workoutDay.Date:yyyy-MM-dd}", workoutDay);
});
#endregion

app.Run();
